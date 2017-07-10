package fuse.dtos;

import java.io.Serializable;

/**
 * Created by zeev on 7/10/17.
 */
public class ClientResponseDTO implements Serializable {


    private static final long serialVersionUID = -8238201869149988382L;

    private String status;
    private String message;

    public ClientResponseDTO(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
