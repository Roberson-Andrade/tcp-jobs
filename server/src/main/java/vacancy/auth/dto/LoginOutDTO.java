package vacancy.auth.dto;

public class LoginOutDTO {
    String token;

    public LoginOutDTO(String _token) {
        this.token = _token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
