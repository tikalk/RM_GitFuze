package fuse.dtos;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by zeev on 7/10/17.
 */
public class PullRequestDTO implements Serializable {

    private static final long serialVersionUID = -8605552585044620835L;

    @NotNull
    private String command;
    @NotNull
    private String gitUrl;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getGitUrl() {
        return gitUrl;
    }

    public void setGitUrl(String gitUrl) {
        this.gitUrl = gitUrl;
    }
}
