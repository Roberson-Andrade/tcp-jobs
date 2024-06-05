package jobs.domain.job.dto;

import org.json.JSONObject;

import jobs.errors.ApplicationException;

public class AddJobInDTO {
  private String name;
  private String field;
  private String salaryRange;
  private String experience;
  private String description;

  public AddJobInDTO(JSONObject input) throws ApplicationException {
    this.name = input.optString("nome");
    this.field = input.optString("ramo");
    this.salaryRange = input.optString("faixaSalarial");
    this.experience = input.optString("competencias");
    this.description = input.optString("descricao");

    if (name == null || field == null || salaryRange == null || experience == null || description == null) {
      throw new ApplicationException("Parametros inválidos", 422);
    }
  }

  public String getName() {
    return name;
  }

  public String getField() {
    return field;
  }

  public String getSalaryRange() {
    return salaryRange;
  }

  public String getExperience() {
    return experience;
  }

  public String getDescription() {
    return description;
  }
}
