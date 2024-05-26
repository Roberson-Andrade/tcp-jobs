package jobs.domain.auth;

import codegen.jooq.tables.records.ApplicantRecord;
import codegen.jooq.tables.records.CompanyRecord;
import codegen.jooq.tables.records.TokenRecord;
import jobs.domain.applicant.ApplicantRepository;
import jobs.domain.auth.dto.*;
import jobs.domain.company.CompanyRepository;
import jobs.errors.ApplicationException;
import jobs.utils.controller.BaseController;
import org.json.JSONObject;

public class AuthController extends BaseController {
    final ApplicantRepository applicantRepository;
    final CompanyRepository companyRepository;
    final TokenRepository tokenRepository;

    public AuthController(ApplicantRepository applicantRepository, TokenRepository tokenRepository,
            CompanyRepository companyRepository) {
        this.applicantRepository = applicantRepository;
        this.companyRepository = companyRepository;
        this.tokenRepository = tokenRepository;
    }

    public JSONObject login(JSONObject input, LoginType type) throws ApplicationException {
        LoginInDTO data = new LoginInDTO(input);
        String email;

        if (type == LoginType.APPLICANT) {
            ApplicantRecord applicantRecord = this.applicantRepository.findByEmail(data.getEmail());

            if (applicantRecord == null || !applicantRecord.getPassword().equals(data.getPassword())) {
                throw new ApplicationException("Login ou senha incorretos", 404);
            }

            email = applicantRecord.getEmail();
        } else {
            CompanyRecord companyRecord = this.companyRepository.findByEmail(data.getEmail());

            if (companyRecord == null || !companyRecord.getPassword().equals(data.getPassword())) {
                throw new ApplicationException("Login ou senha incorretos", 404);
            }

            email = companyRecord.getEmail();
        }

        TokenRecord token = this.tokenRepository.create(email);

        return this.json(new LoginOutDTO(token.getId(), 200));
    }

    public JSONObject signInApplicant(JSONObject input) throws ApplicationException {
        SignInApplicantInDTO data = new SignInApplicantInDTO(input);

        ApplicantRecord existingApplicant = this.applicantRepository.findByEmail(data.getEmail());

        if (existingApplicant != null) {
            throw new ApplicationException("Email já cadastrado", 422);
        }

        ApplicantRecord applicantRecord = this.applicantRepository
                .create(new ApplicantDTO(data.getEmail(), data.getPassword(), data.getName()));

        if (applicantRecord == null) {
            throw new ApplicationException("Erro ao criar candidato", 500);
        }

        TokenRecord token = this.tokenRepository.create(applicantRecord.getEmail());

        return this.json(new SignInOutDTO(token.getId(), 201));
    }

    public JSONObject signInCompany(JSONObject input) throws ApplicationException {
        SignInCompanyInDTO data = new SignInCompanyInDTO(input);

        CompanyRecord existingcompany = this.companyRepository.findByEmail(data.getEmail());

        if (existingcompany != null) {
            throw new ApplicationException("Email já cadastrado", 422);
        }

        CompanyRecord companyRecord = this.companyRepository
                .create(new CompanyDTO(data.getEmail(), data.getPassword(), data.getBusinessName(), data.getCnpj(),
                        data.getDescription(), data.getSector()));

        if (companyRecord == null) {
            throw new ApplicationException("Erro ao criar empresa", 500);
        }

        TokenRecord token = this.tokenRepository.create(companyRecord.getEmail());

        return this.json(new SignInOutDTO(token.getId(), 201));
    }

    public JSONObject logout(JSONObject input) {
        LogoutInDTO data = new LogoutInDTO(input);

        this.tokenRepository.deleteById(data.getToken());

        return this.json(new LogoutOutDTO(204));
    }
}
