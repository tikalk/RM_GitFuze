package fuse.controllers;

import org.eclipse.egit.github.core.PullRequest;
import org.eclipse.egit.github.core.PullRequestMarker;
import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.service.PullRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zeev on 7/10/17.
 */
@RestController
@RequestMapping("fusegit")
public class GitOperationsController {

    private static final Logger logger = LoggerFactory.getLogger(GitOperationsController.class);

 /*   @Autowired
    private GitHubClient gitHubClient;*/


    @RequestMapping(value = "/pull-request", method = RequestMethod.GET)
    @ResponseBody
    public String gitClone() {
        try {


            PullRequestService service = new PullRequestService();
            service.getClient().setCredentials("pnielab@gmail.com", "pnini007");
            RepositoryId repo = RepositoryId.createFromUrl("https://github.com/tikalk/RM_GitFuze.git");
            PullRequest pullRequest = new PullRequest();
            pullRequest.setTitle("pull request");

            PullRequestMarker base = new PullRequestMarker();
            base.setLabel("master");
            pullRequest.setBase(base);

            PullRequestMarker head = new PullRequestMarker();
            head.setLabel("dev");
            pullRequest.setHead(head);

            PullRequest request = service.createPullRequest(repo, pullRequest);
            request = null;
            int i = 0;

         /*

            GitHubClient client = new GitHubClient();
            client.setCredentials("pnielab@gmail.com", "pnini007");
            client.

            GitHub gitHub = GitHub.connect("pnielab@gmail.com","pnini07");
            gitHub.
            GHRepository repository = gitHub.getRepository("https://github.com/tikalk/RM_GitFuze.git");*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
