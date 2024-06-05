package jobs.domain.job.dto;

import org.json.JSONObject;

import jobs.errors.ApplicationException;

public class UpdateJobInDTO {
  private Integer id;
  private String name;
  private String field;
  private String salaryRange;
  private String experience;
  private String description;

  public UpdateJobInDTO(JSONObject input) throws ApplicationException {
    this.id = input.optInt("idVaga");
    this.name = input.optString("nome");
    this.field = input.optString("ramo");
    this.salaryRange = input.optString("faixaSalarial");
    this.experience = input.optString("competencia");
    this.description = input.optString("descricao");

    if (name == null || field == null || salaryRange == null || experience == null || description == null) {
      throw new ApplicationException("Parametros inválidos", 422);
    }
  }

  public Integer getId() {
    return id;
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
