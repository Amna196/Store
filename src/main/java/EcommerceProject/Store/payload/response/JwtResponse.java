package EcommerceProject.Store.payload.response;

public class JwtResponse {

    private final String jwt;
    private final String jwt_expiryDate;
    private final String jwt_refresh;

    public JwtResponse(String jwt, String jwt_expiryDate, String jwt_refresh) {
        this.jwt = jwt;
        this.jwt_expiryDate = jwt_expiryDate;
        this.jwt_refresh = jwt_refresh;
    }

    public String getJwt() {
        return jwt;
    }

    public String getJwt_expiryDate() {
        return jwt_expiryDate;
    }

    public String getJwt_refresh() {
        return jwt_refresh;
    }
}
