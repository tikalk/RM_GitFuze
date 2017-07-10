package fuse.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by zeev on 7/10/17.
 */
@RestController
public class GitOperationsController {

    private static final Logger logger = LoggerFactory.getLogger(GitOperationsController.class);

    @Autowired
    private RestTemplate restTemplate;


}
