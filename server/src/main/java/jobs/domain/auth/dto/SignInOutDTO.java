package jobs.domain.auth.dto;

import jobs.utils.dto.BaseOutDTO;

public class SignInOutDTO extends BaseOutDTO {
    String token;

    public SignInOutDTO(String token, Integer status) {
        super(status);
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
