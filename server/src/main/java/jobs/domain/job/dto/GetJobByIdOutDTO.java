package jobs.domain.job.dto;

import jobs.utils.dto.BaseOutDTO;

public class GetJobByIdOutDTO extends BaseOutDTO {

  public GetJobByIdOutDTO(Integer status, JobOutDTO job) {
    super(status);

  }
}
