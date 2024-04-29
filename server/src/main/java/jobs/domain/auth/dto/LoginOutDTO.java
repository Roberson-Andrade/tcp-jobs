package jobs.domain.auth.dto;

import jobs.utils.dto.BaseOutDTO;

public class LoginOutDTO extends BaseOutDTO {
    String token;

    public LoginOutDTO(String token, Integer status) {
        super(status);
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
