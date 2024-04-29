package jobs.domain.auth.dto;

import org.json.JSONObject;

public class LogoutInDTO {
    String token;

    public LogoutInDTO(JSONObject data) {
        this.token = data.getString("token");
    }

    public String getToken() {
        return token;
    }
}
