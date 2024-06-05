package jobs.domain.job.dto;

import org.json.JSONObject;

import jobs.errors.ApplicationException;

public class DeleteJobInDTO {

  private String email;
  private Integer id;

  public DeleteJobInDTO(JSONObject input) throws ApplicationException {
    this.email = input.optString("email");
    this.id = input.optInt("id");

    if (email == null || id == null) {
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
