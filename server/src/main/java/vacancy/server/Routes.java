package vacancy.server;

import org.jooq.DSLContext;
import org.json.JSONObject;
import vacancy.database.Configuration;
import vacancy.domain.auth.ApplicantRepository;
import vacancy.domain.auth.AuthController;
import vacancy.errors.ApplicationException;

import java.sql.SQLException;

public class Routes {
    private static Routes instance;
    AuthController auth;

    private Routes() throws SQLException {
        Configuration config = new Configuration();
        DSLContext ctx = config.createDSLContext();
        this.auth = new AuthController(new ApplicantRepository(ctx));
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

        try {
            return switch (type) {
                case "loginCandidato" -> auth.login(request);
                case "cadastrarCandidato" -> auth.signIn(request);
                default -> null;
            };
        } catch (ApplicationException error) {
            JSONObject response = new JSONObject();

            response.put("mensagem", error.getMessage());
            response.put("status", error.getStatus());
            response.put("operacao", type);

            return response;
        }
    }

    private JSONObject convertJsonRequest(String requestStr) {
        return new JSONObject(requestStr);
    }
}