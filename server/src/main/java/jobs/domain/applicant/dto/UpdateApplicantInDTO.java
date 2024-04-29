package jobs.domain.applicant.dto;

import org.json.JSONObject;

public class UpdateApplicantInDTO {
    String name;
    String email;
    String password;

    public UpdateApplicantInDTO(JSONObject input) {
        this.name = input.getString("nome");
        this.email = input.getString("email");
        this.password = input.getString("senha");
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
