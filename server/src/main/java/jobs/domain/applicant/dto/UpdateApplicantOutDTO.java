package jobs.domain.applicant.dto;

import jobs.utils.dto.BaseOutDTO;

public class UpdateApplicantOutDTO extends BaseOutDTO {
    public UpdateApplicantOutDTO(int status, String message) {
        super(status, message);
    }

    public UpdateApplicantOutDTO(int status) {
        super(status);
    }
}
