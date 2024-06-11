package jobs.domain.job.dto;

import org.json.JSONObject;

import jobs.errors.ApplicationException;

public class DeleteJobInDTO {

  private String email;
  private Integer id;

  public DeleteJobInDTO(JSONObject input) throws ApplicationException {
    this.email = input.optString("email", null);
    this.id = input.optInt("idVaga", -1);

    if (email == null || id == -1) {
      throw new ApplicationException("Parametros inválidos", 422);
    }
  }

  public String getEmail() {
    return email;
  }

  public Integer getId() {
    return id;
  }
}
