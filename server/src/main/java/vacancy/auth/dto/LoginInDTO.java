package vacancy.auth.dto;

import org.json.JSONObject;

public class LoginInDTO {
    String email;
    String password;

    public LoginInDTO(JSONObject data) {
        this.email = data.getString("email");
        this.password = data.getString("senha");
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
