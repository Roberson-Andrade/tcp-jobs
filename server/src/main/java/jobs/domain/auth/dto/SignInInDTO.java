package jobs.domain.auth.dto;

import org.json.JSONObject;

public class SignInInDTO {
    String email;
    String password;
    String name;

    public SignInInDTO(JSONObject input) {
        this.email = input.getString("email");
        this.password = input.getString("senha");
        ;
        this.name = input.getString("nome");
        ;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
