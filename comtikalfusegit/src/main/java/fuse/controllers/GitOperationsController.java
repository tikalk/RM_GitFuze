package fuse.controllers;

import com.google.gson.Gson;
import fuse.configuration.RibbonConfig;
import fuse.dtos.ClientResponseDTO;
import fuse.dtos.PullRequestDTO;
import fuse.enums.JenkinsStatus;
import fuse.messages.JenkinsMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
