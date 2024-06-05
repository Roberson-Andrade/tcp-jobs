package jobs.domain.applicant.dto;

import org.json.JSONObject;

import jobs.errors.ApplicationException;

public class FindApplicantCompetencesInDTO {

  private String email;

  public FindApplicantCompetencesInDTO(JSONObject input) throws ApplicationException {
    this.email = input.optString("email");

    if (email == null) {
      throw new ApplicationException("Parametros inválidos", 422);
    }
  }

  public String getEmail() {
    return email;
  }
}
