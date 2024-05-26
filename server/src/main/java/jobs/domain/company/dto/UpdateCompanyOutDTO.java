package jobs.domain.company.dto;

import jobs.utils.dto.BaseOutDTO;

public class UpdateCompanyOutDTO extends BaseOutDTO {
    public UpdateCompanyOutDTO(int status, String message) {
        super(status, message);
    }

    public UpdateCompanyOutDTO(int status) {
        super(status);
    }
}
