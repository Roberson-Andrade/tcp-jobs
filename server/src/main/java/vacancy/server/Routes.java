package vacancy.server;

import org.jooq.DSLContext;
import org.json.JSONObject;
import vacancy.auth.ApplicantRepository;
import vacancy.auth.AuthController;
import vacancy.database.Configuration;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Routes {
    private static Routes instance;

    private final Map<String, Function<JSONObject, JSONObject>> routes;

    private Routes() throws SQLException {
        Configuration config = new Configuration();
        DSLContext ctx = config.createDSLContext();

        routes = new HashMap<>();
        AuthController auth = new AuthController(new ApplicantRepository(ctx));
        routes.put("loginCandidato", auth::login);
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
        Function<JSONObject, JSONObject> requestFn = routes.get(type);

        if (requestFn != null) {
            return requestFn.apply(request);
        } else {
            // Handle unknown type
            return null;
        }
    }

    private JSONObject convertJsonRequest(String requestStr) {
        return new JSONObject(requestStr);
    }
}