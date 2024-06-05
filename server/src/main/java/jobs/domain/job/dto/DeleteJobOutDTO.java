package jobs.domain.job.dto;

import jobs.utils.dto.BaseOutDTO;

public class DeleteJobOutDTO extends BaseOutDTO {
  public DeleteJobOutDTO(Integer status, String message) {
    super(status, message);
  }
}
