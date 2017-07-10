package fuse.messages;

import java.io.Serializable;

/**
 * Created by zeev on 7/10/17.
 */
public class Message implements Serializable {


    private static final long serialVersionUID = -3738639785491618700L;
    private String githubUrl;
    private String token;
    private String jenkinsUrl;
    private String status;

    public String getGithubUrl() {
        return githubUrl;
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getJenkinsUrl() {
        return jenkinsUrl;
    }

    public void setJenkinsUrl(String jenkinsUrl) {
        this.jenkinsUrl = jenkinsUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
