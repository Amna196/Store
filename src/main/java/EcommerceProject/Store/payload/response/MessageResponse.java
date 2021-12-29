package EcommerceProject.Store.payload.response;

/*
 *  The purpose of the class is to display messages upon signup
 *  ex: Error: Username is already taken!
 */
public class MessageResponse {
    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
