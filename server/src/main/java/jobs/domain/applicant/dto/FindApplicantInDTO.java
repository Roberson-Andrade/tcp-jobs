package jobs.domain.applicant.dto;

import org.json.JSONObject;

public class FindApplicantInDTO {
    String email;

    public FindApplicantInDTO(JSONObject input) {
        this.email = input.getString("email");
    }

    public String getEmail() {
        return email;
    }
}
