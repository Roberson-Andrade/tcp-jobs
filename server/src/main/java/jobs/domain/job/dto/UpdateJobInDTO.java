package jobs.domain.job.dto;

import jobs.errors.ApplicationException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UpdateJobInDTO {
    private Integer id;
    private String name;
    private Double salaryRange;
    private ArrayList<String> competences = new ArrayList<>();
    private String description;
    private String state;

    public UpdateJobInDTO(JSONObject input) throws ApplicationException {
        this.id = input.optInt("idVaga", -1);
        this.name = input.optString("nome", null);
        this.salaryRange = input.optDouble("faixaSalarial", -1);
        this.description = input.optString("descricao", null);
        this.state = input.optString("estado", null);
        var competencesArr = input.optJSONArray("competencias", null);

        if (name == null || state == null || salaryRange == -1 || competencesArr == null || description == null
                || this.id == -1) {
            throw new ApplicationException("Parametros inválidos", 422);
        }
    }

    public Integer getId() {
        return id;
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
}
