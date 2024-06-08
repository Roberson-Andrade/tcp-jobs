package jobs.domain.job.dto;

import codegen.jooq.tables.records.JobRecord;
import jobs.utils.dto.BaseOutDTO;

public class GetJobByIdOutDTO extends BaseOutDTO {
  private String vagaSalarial;
  private String descricao;

  public GetJobByIdOutDTO(Integer status, JobRecord job) {
    super(status);

    this.vagaSalarial = job.getSalaryRange();
    this.descricao = job.getDescription();
  }

  public String getVagaSalarial() {
    return vagaSalarial;
  }

  public String getDescricao() {
    return descricao;
  }
}
