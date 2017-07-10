package fuse.messages;

import java.io.Serializable;

/**
 * Created by zeev on 7/10/17.
 */
public class ClientMessage implements Serializable {


    private static final long serialVersionUID = -4676927530414989602L;

    private String message;
    private String errorCode;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
