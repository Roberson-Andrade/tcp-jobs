package jobs.domain.job.dto;

import jobs.errors.ApplicationException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AddJobInDTO {
    private String name;
    private Double salaryRange;
    private ArrayList<String> competences = new ArrayList<>();
    private String description;
    private String state;
    private String email;

    public AddJobInDTO(JSONObject input) throws ApplicationException {
        this.email = input.optString("email", null);
        this.name = input.optString("nome", null);
        this.salaryRange = input.optDouble("faixaSalarial", -1);
        this.description = input.optString("descricao", null);
        this.state = input.optString("estado", null);
        var competenceArr = input.optJSONArray("competencias", null);

        if (name == null || email == null || state == null || salaryRange == -1 || competenceArr == null
                || description == null) {
            throw new ApplicationException("Parametros inválidos", 422);
        }

        for (int i = 0; i < competenceArr.length(); i++) {
            this.competences.add(competenceArr.getString(i));
        }
    }

    public String getName() {
        return name;
    }

    public Double getSalaryRange() {
        return salaryRange;
    }

    public ArrayList<String> getCompetences() {
        return competences;
    }

    public String getDescription() {
        return description;
    }

    public String getState() {
        return state;
    }

    public String getEmail() {
        return email;
    }
}
