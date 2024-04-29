package jobs.domain.applicant.dto;

import org.json.JSONObject;

public class DeleteApplicantInDTO {
    String email;

    public DeleteApplicantInDTO(JSONObject input) {
        this.email = input.getString("email");
    }

    public String getEmail() {
        return email;
    }
}
