package jobs.domain.auth;

import codegen.jooq.tables.records.ApplicantRecord;
import codegen.jooq.tables.records.TokenRecord;
import jobs.domain.applicant.ApplicantRepository;
import jobs.domain.auth.dto.*;
import jobs.errors.ApplicationException;
import jobs.utils.controller.BaseController;
import org.json.JSONObject;

public class AuthController extends BaseController {
    final ApplicantRepository applicantRepository;
    final TokenRepository tokenRepository;

    public AuthController(ApplicantRepository applicantRepository, TokenRepository tokenRepository) {
        this.applicantRepository = applicantRepository;
        this.tokenRepository = tokenRepository;
    }

    public JSONObject login(JSONObject input) throws ApplicationException {
        LoginInDTO data = new LoginInDTO(input);

        ApplicantRecord applicantRecord = this.applicantRepository.findByEmail(data.getEmail());

        if (applicantRecord == null || !applicantRecord.getPassword().equals(data.getPassword())) {
            throw new ApplicationException("Login ou senha incorretos", 401);
        }

        TokenRecord token = this.tokenRepository.create(applicantRecord.getEmail());

        return this.json(new LoginOutDTO(token.getId(), 200));
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

        TokenRecord token = this.tokenRepository.create(applicantRecord.getEmail());

        return this.json(new SignInOutDTO(token.getId(), 201));
    }

    public JSONObject logout(JSONObject input) {
        LogoutInDTO data = new LogoutInDTO(input);

        this.tokenRepository.deleteById(data.getToken());

        return this.json(new LogoutOutDTO(204));
    }
}
