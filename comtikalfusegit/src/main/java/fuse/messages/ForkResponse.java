package fuse.messages;

import java.util.UUID;

/**
 * Created by dmitriym on 10/07/2017.
 */
public class ForkResponse {

    private final UUID id;
    private final String brachUrl;
    //private final String status;

    public ForkResponse(String branchName/*, String status*/) {
        this.id = UUID.randomUUID();
        this.brachUrl = branchName;
      //  this.status = status;
    }

    public String getBrachUrl() {
        return brachUrl;
    }

//    public String getStatus() {
//        return status;
//    }
}
