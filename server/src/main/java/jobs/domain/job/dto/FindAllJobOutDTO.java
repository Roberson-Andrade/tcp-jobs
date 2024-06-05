package jobs.domain.job.dto;

import java.util.ArrayList;
import java.util.List;

import codegen.jooq.tables.records.JobRecord;
import jobs.utils.dto.BaseOutDTO;

public class FindAllJobOutDTO extends BaseOutDTO {
  private List<JobOutDTO> vagas;

  public FindAllJobOutDTO(Integer status, List<JobRecord> jobs) {
    super(status);

    vagas = new ArrayList<>();

    for (JobRecord job : jobs) {
      vagas.add(new JobOutDTO(job.getId(), job.getName()));
    }
  }

  public List<JobOutDTO> getVagas() {
    return vagas;
  }
}
