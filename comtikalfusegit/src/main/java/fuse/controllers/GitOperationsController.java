package fuse.controllers;

import com.google.gson.Gson;
import fuse.dtos.ClientResponseDTO;
import fuse.dtos.PullRequestDTO;
import fuse.enums.JenkinsStatus;
import fuse.messages.ForkResponse;
import fuse.messages.JenkinsMessage;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;

/**
 * Created by zeev on 7/10/17.
 */
@RestController
@RequestMapping("fusegit")
public class GitOperationsController {

    private static final Logger logger = LoggerFactory.getLogger(GitOperationsController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Gson gson;

    private String jenkinsApi = "http://jenkins/api/build"; // TODO take from config


    @RequestMapping("/fork")
    public ForkResponse fork(@RequestParam String token) {//REST Endpoint.

        String masterBranch;
        String developerBranch;

        // get token and repo link from payload

        // connect to github

        //Basic authentication
//        String username = "";
//        String password = "";

        ForkResponse msg = null;

        GitHubClient client = new GitHubClient();
        //client.setCredentials(username, password);

        logger.info("Token: " + token);
        client.setOAuth2Token(token);


        // fork new repo(master) and dev branches
        RepositoryService service = new RepositoryService();
        //service.getClient().setCredentials(username, password);
        RepositoryId toBeForked = RepositoryId.createFromUrl("https://github.com/tikalk/RM_GitFuze");

        Repository forkedRepository = null;
        try {
            forkedRepository = service.forkRepository(toBeForked);
            logger.info(forkedRepository.getHtmlUrl() + " is created successully");
        } catch (IOException e) {
            msg = new ForkResponse("Failed to fork repo");
            logger.error("FAILED to fork repo" + e);
            return msg;
        }

        // create and send response with link to dev branch
        msg = new ForkResponse(forkedRepository.getHtmlUrl());
        return msg;
    }

    @RequestMapping(value = "/pull-request", method = RequestMethod.POST)
    @ResponseBody
    public ClientResponseDTO createPullRequest(@Valid @RequestBody PullRequestDTO pullRequestDTO, @NotNull @RequestHeader String token) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("token", token);
        JenkinsMessage body = new JenkinsMessage();
        body.setGitUrl(pullRequestDTO.getGitUrl());
        body.setCommand(pullRequestDTO.getCommand());
        HttpEntity<String> request = new HttpEntity<>(gson.toJson(body), headers);
        String response = restTemplate.postForObject(jenkinsApi, request, String.class);
        return new ClientResponseDTO(response, "");
    }


    @ExceptionHandler(Exception.class)
    public ClientResponseDTO handle(Exception e) {
        return new ClientResponseDTO(JenkinsStatus.FAIL.name(), "unexpected error from jenkins server");
    }

}
