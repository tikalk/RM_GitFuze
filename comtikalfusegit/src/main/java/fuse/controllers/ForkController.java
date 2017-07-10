package fuse.controllers;

import fuse.messages.ForkResponse;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by dmitriym on 10/07/2017.
 */
@RestController
public class ForkController {

    private static final Logger logger = LoggerFactory.getLogger(ForkController.class);

    @RequestMapping("/fork")
    public ForkResponse message(@RequestParam String token) {//REST Endpoint.

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
}
