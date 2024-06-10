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
    this.id = input.optInt("idVaga", -1);
    this.name = input.optString("nome", null);
    this.field = input.optString("ramo", null);
    this.salaryRange = input.optString("faixaSalarial", null);
    this.experience = input.optString("competencia", null);
    this.description = input.optString("descricao", null);

    if (name == null || field == null || salaryRange == null || experience == null || description == null
        || this.id == -1) {
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
