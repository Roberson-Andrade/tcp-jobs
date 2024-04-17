package vacancy.utils.dto;

import org.json.JSONObject;

public class BaseController {
    protected <T> JSONObject json(T input) {
        return new JSONObject(input);
    }
}
