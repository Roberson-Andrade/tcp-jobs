package vacancy.auth;

import org.json.JSONObject;
import vacancy.auth.dto.LoginOutDTO;
import vacancy.utils.dto.BaseController;

public class AuthController extends BaseController {
    public JSONObject login(JSONObject data) {
        // do login

        return this.json(new LoginOutDTO("token"));
    }
}
