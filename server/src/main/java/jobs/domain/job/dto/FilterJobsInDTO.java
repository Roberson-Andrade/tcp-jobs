package jobs.domain.job.dto;

import jobs.errors.ApplicationException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FilterJobsInDTO {
    String email;
    String type;
    ArrayList<String> competences = new ArrayList<>();

    public FilterJobsInDTO(JSONObject data) throws ApplicationException {
        var filters = data.optJSONObject("filtros", null);

        if (filters == null) {
            throw new ApplicationException("Parametros inválidos", 422);
        }

        this.email = data.optString("email", null);
        this.type = filters.optString("tipo", null);
        var competencesArr = filters.optJSONArray("competencias", null);

        if (type == null || competencesArr == null || email == null ) {
            throw new ApplicationException("Parametros inválidos", 422);
        }

        for (int i = 0; i < competencesArr.length(); i++) {
            this.competences.add(competencesArr.getString(i));
        }
    }

    public String getType() {
        return type;
    }

    public ArrayList<String> getCompetences() {
        return competences;
    }

    public String getEmail() {
        return email;
    }
}
