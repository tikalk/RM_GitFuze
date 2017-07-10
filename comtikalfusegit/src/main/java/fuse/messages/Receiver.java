package fuse.messages;

import com.google.gson.Gson;
import fuse.enums.JenkinsStatus;
import org.eclipse.egit.github.core.PullRequest;
import org.eclipse.egit.github.core.PullRequestMarker;
import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.PullRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by zeev on 7/10/17.
 */
@Component
public class Receiver {


    private static final Logger logger = LoggerFactory.getLogger(Receiver.class);

    @Autowired
    private Gson gson;


    public void receiveMessage(String msg) {
        Message message = gson.fromJson(msg, Message.class);
        if (JenkinsStatus.SUCCESS.name().equals(message.getStatus())) {
            sendPullRequest(message);
        } else {
            ClientMessage clientMessage = new ClientMessage();
            clientMessage.setMessage("jenkins build failure");
            clientMessage.setErrorCode(JenkinsStatus.FAIL.name());
            sendMessage(clientMessage);
        }
    }

    private void sendMessage(ClientMessage clientMessage) {

    }

    private void sendPullRequest(Message message) {
        ClientMessage clientMessage = new ClientMessage();
        try {
            GitHubClient client = new GitHubClient();
            client.setOAuth2Token(message.getToken());
            PullRequestService pullRequestService = new PullRequestService(client);
            RepositoryId repo = RepositoryId.createFromUrl(message.getGithubUrl());
            PullRequest pullRequest = new PullRequest();
            pullRequest.setTitle("pull request");
            PullRequestMarker base = new PullRequestMarker();
            base.setLabel("master");
            pullRequest.setBase(base);
            PullRequestMarker head = new PullRequestMarker();
            head.setLabel("dev");
            pullRequest.setHead(head);
            PullRequest request = pullRequestService.createPullRequest(repo, pullRequest);
            clientMessage.setMessage("create pull request succeeded: " + message.getGithubUrl());
        } catch (IOException e) {
            logger.error("failure wending pull request: {}" + e.getMessage(), e);
            clientMessage.setMessage("create pull request failure: " + message.getGithubUrl());
            clientMessage.setErrorCode(JenkinsStatus.FAIL.name());
        }
        sendMessage(clientMessage);

    }
}
