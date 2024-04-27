package jobs.domain.auth.dto;

public class LoginOutDTO {
    Integer status;
    String token;

    public LoginOutDTO(String token, Integer status) {
        this.token = token;
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
