package jobs.domain.job.dto;

import jobs.utils.dto.BaseOutDTO;

public class AddJobOutDTO extends BaseOutDTO {

  public AddJobOutDTO(Integer status, String message) {
    super(status, message);
  }
}
