package jobs.domain.job.dto;

import jobs.utils.dto.BaseOutDTO;

public class UpdateJobOutDTO extends BaseOutDTO {
  public UpdateJobOutDTO(Integer status, String message) {
    super(status, message);
  }
}
