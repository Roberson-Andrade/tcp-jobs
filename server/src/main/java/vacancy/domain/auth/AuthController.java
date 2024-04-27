package vacancy.domain.auth;

import codegen.jooq.tables.records.ApplicantRecord;
import org.json.JSONObject;
import vacancy.domain.auth.dto.*;
import vacancy.errors.ApplicationException;
import vacancy.utils.controller.BaseController;

public class AuthController extends BaseController {
    final ApplicantRepository applicantRepository;

    public AuthController(ApplicantRepository applicantRepository) {
        this.applicantRepository = applicantRepository;
    }

    public JSONObject login(JSONObject input) throws ApplicationException {
        LoginInDTO data = new LoginInDTO(input);

        ApplicantRecord applicantRecord = this.applicantRepository.findByEmail(data.getEmail());

        if (applicantRecord == null) {
            throw new ApplicationException("Login ou senha incorretos", 401);
        }

        return this.json(new LoginOutDTO("token", 200));
    }

    public JSONObject signIn(JSONObject input) throws ApplicationException {
        SignInInDTO data = new SignInInDTO(input);

        ApplicantRecord existingApplicant = this.applicantRepository.findByEmail(data.getEmail());

        if (existingApplicant != null) {
            throw new ApplicationException("Email já cadastrado", 422);
        }

        ApplicantRecord applicantRecord = this.applicantRepository.create(new ApplicantDTO(data.getEmail(), data.getPassword(), data.getName()));

        if (applicantRecord == null) {
            throw new ApplicationException("Erro ao criar candidato", 500);
        }

        return this.json(new SignInOutDTO("token", 201));
    }
}
