package jobs.domain.company.dto;

import org.json.JSONObject;

public class DeleteCompanyInDTO {
    String email;

    public DeleteCompanyInDTO(JSONObject input) {
        this.email = input.getString("email");
    }

    public String getEmail() {
        return email;
    }
}
