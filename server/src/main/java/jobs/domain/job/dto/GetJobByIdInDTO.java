package jobs.domain.job.dto;

import org.json.JSONObject;

import jobs.errors.ApplicationException;

public class GetJobByIdInDTO {
  private String email;
  private Integer id;

  public GetJobByIdInDTO(JSONObject input) throws ApplicationException {
    this.email = input.optString("email");
    this.id = input.optInt("idVaga");

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
