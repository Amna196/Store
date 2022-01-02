package EcommerceProject.Store.payload.response;

import java.util.UUID;

/*
 *  The purpose of the class is to display messages upon signup
 *  ex: Error: Username is already taken!
 */
public class MessageResponse {
    private String message;
    private UUID hashcode;

    public MessageResponse(String message) {
        this.message = message;
    }

    public MessageResponse(UUID hashcode, String message) {
        this.hashcode = hashcode;
        this.message = message;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UUID getHashcode() {
        return hashcode;
    }

    public void setHashcode(UUID hashcode) {
        this.hashcode = hashcode;
    }
}
