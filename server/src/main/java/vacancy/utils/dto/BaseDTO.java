package vacancy.utils.dto;

import org.json.JSONObject;

public abstract class BaseDTO<T> {
    abstract T parseIn(JSONObject input);
    abstract JSONObject parseOut(T input);
}
