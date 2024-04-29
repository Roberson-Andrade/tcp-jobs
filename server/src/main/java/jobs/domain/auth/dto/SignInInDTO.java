package jobs.domain.auth.dto;

import org.json.JSONObject;

public class SignInInDTO {
    String email;
    String password;
    String name;

    public SignInInDTO(JSONObject input) {
        this.email = input.getString("email");
        this.name = input.getString("nome");

        try {
            this.password = input.getString("senha");
        } catch (Exception e) {
            this.password = Integer.toString(input.getInt("senha"));
        }
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
}
