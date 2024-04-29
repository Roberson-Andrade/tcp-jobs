package jobs.domain.applicant;

import codegen.jooq.tables.records.ApplicantRecord;
import jobs.domain.applicant.dto.*;
import jobs.utils.controller.BaseController;
import org.json.JSONObject;

public class ApplicantController extends BaseController {
    final ApplicantRepository applicantRepository;

    public ApplicantController(ApplicantRepository applicantRepository) {
        this.applicantRepository = applicantRepository;
    }

    public JSONObject find(JSONObject input) {
        FindApplicantInDTO data = new FindApplicantInDTO(input);

        ApplicantRecord applicant = this.applicantRepository.findByEmail(data.getEmail());

        if (applicant == null) {
            return this.json(new FindApplicantOutDTO(404, "E-mail não encontrado"));
        }

        return this.json(new FindApplicantOutDTO(201, applicant.getName(), applicant.getPassword()));
    }

    public JSONObject update(JSONObject input) {
        UpdateApplicantInDTO data = new UpdateApplicantInDTO(input);

        if (this.applicantRepository.findByEmail(data.getEmail()) == null) {
            return this.json(new UpdateApplicantOutDTO(404, "E-mail não encontrado"));
        }

        this.applicantRepository.update(data.getName(), data.getEmail(), data.getPassword());

        return this.json(new UpdateApplicantOutDTO(201));
    }

    public JSONObject delete(JSONObject input) {
        DeleteApplicantInDTO data = new DeleteApplicantInDTO(input);

        this.applicantRepository.delete(data.getEmail());

        return this.json(new DeleteApplicantOutDTO(201));
    }
}
