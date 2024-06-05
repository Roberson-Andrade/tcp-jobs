package jobs.server;

import jobs.database.Configuration;
import jobs.domain.applicant.ApplicantController;
import jobs.domain.applicant.ApplicantRepository;
import jobs.domain.auth.AuthController;
import jobs.domain.auth.LoginType;
import jobs.domain.auth.TokenRepository;
import jobs.domain.company.CompanyController;
import jobs.domain.company.CompanyRepository;
import jobs.errors.ApplicationException;
import org.jooq.DSLContext;
import org.json.JSONObject;

import java.sql.SQLException;

public class Routes {
    private static Routes instance;
    AuthController authController;
    ApplicantController applicantController;
    CompanyController companyController;

    private Routes() throws SQLException {
        // configure pool connection
        Configuration config = new Configuration();
        DSLContext ctx = config.createDSLContext();

        // instantiate the repositories
        ApplicantRepository applicantRepository = new ApplicantRepository(ctx);
        CompanyRepository companyRepository = new CompanyRepository(ctx);
        TokenRepository tokenRepository = new TokenRepository(ctx);

        // instantiate the controllers
        this.authController = new AuthController(applicantRepository, tokenRepository, companyRepository);
        this.applicantController = new ApplicantController(applicantRepository);
        this.companyController = new CompanyController(companyRepository);
    }

    public static Routes getInstance() throws SQLException {
        if (instance == null) {
            instance = new Routes();
        }
        return instance;
    }

    public JSONObject route(String requestStr) {
        JSONObject response = null;
        JSONObject request = convertJsonRequest(requestStr);
        String type = request.getString("operacao");

        try {

            switch (type) {
                // applicant routes
                case "loginCandidato":
                    response = authController.login(request, LoginType.APPLICANT);
                    break;
                case "cadastrarCandidato":
                    response = authController.signInApplicant(request);
                    break;
                case "visualizarCandidato":
                    response = applicantController.find(request);
                    break;
                case "atualizarCandidato":
                    response = applicantController.update(request);
                    break;
                case "apagarCandidato":
                    response = applicantController.delete(request);
                    break;

                // company routes
                case "loginEmpresa":
                    response = authController.login(request, LoginType.COMPANY);
                    break;
                case "cadastrarEmpresa":
                    response = authController.signInCompany(request);
                    break;
                case "visualizarEmpresa":
                    response = companyController.find(request);
                    break;
                case "atualizarEmpresa":
                    response = companyController.update(request);
                    break;
                case "apagarEmpresa":
                    response = companyController.delete(request);
                    break;
                case "logout":
                    response = authController.logout(request);
                    break;

                // competence routes
                case "cadastrarCompetenciaExperiencia":
                    response = applicantController.addApplicantCompetences(request);
                case "visualizarCompetenciaExperiencia":
                    response = applicantController.findApplicantCompetences(request);

                default:
                    break;
            }
        } catch (ApplicationException error) {
            response = new JSONObject();
            response.put("mensagem", error.getMessage());
            response.put("status", error.getStatus());
        } finally {
            response.put("operacao", type);
        }

        return response;
    }

    private JSONObject convertJsonRequest(String requestStr) {
        return new JSONObject(requestStr);
    }
}