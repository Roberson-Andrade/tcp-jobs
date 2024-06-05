package jobs.domain.job.dto;

import org.json.JSONObject;

import jobs.errors.ApplicationException;

public class FindAllJobInDTO {
  private String email;

  public FindAllJobInDTO(JSONObject input) throws ApplicationException {
    this.email = input.optString("email");

    if (email == null) {
      throw new ApplicationException("Parametros inválidos", 422);
    }
  }

  public String getEmail() {
    return email;
  }
}
