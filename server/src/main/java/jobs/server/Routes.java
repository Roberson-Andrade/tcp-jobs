package jobs.server;

import jobs.database.Configuration;
import jobs.domain.applicant.ApplicantController;
import jobs.domain.applicant.ApplicantRepository;
import jobs.domain.auth.AuthController;
import jobs.domain.auth.TokenRepository;
import jobs.errors.ApplicationException;
import org.jooq.DSLContext;
import org.json.JSONObject;

import java.sql.SQLException;

public class Routes {
    private static Routes instance;
    AuthController authController;
    ApplicantController applicantController;

    private Routes() throws SQLException {
        // configure pool connection
        Configuration config = new Configuration();
        DSLContext ctx = config.createDSLContext();

        // instantiate the repositories
        ApplicantRepository applicantRepository = new ApplicantRepository(ctx);
        TokenRepository tokenRepository = new TokenRepository(ctx);

        // instantiate the controllers
        this.authController = new AuthController(applicantRepository, tokenRepository);
        this.applicantController = new ApplicantController(applicantRepository);
    }

    public static Routes getInstance() throws SQLException {
        if (instance == null) {
            instance = new Routes();
        }
        return instance;
    }

    public JSONObject route(String requestStr) {
        JSONObject request = convertJsonRequest(requestStr);
        String type = request.getString("operacao");
        JSONObject response = null;

        try {
            switch (type) {
                case "loginCandidato":
                    response = authController.login(request);
                    break;
                case "cadastrarCandidato":
                    response = authController.signIn(request);
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
                case "logout":
                    response = authController.logout(request);
                    break;
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