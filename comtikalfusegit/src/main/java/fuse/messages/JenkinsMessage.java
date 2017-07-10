package fuse.messages;

import java.io.Serializable;

/**
 * Created by zeev on 7/10/17.
 */
public class JenkinsMessage implements Serializable {


    private static final long serialVersionUID = -700173564154221700L;

    private String gitUrl;
    private String command;

    public String getGitUrl() {
        return gitUrl;
    }

    public void setGitUrl(String gitUrl) {
        this.gitUrl = gitUrl;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
