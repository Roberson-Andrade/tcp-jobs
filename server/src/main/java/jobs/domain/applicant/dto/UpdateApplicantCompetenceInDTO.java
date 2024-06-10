package jobs.domain.applicant.dto;

import jobs.errors.ApplicationException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class UpdateApplicantCompetenceInDTO {
    private final String email;
    ArrayList<ApplicantCompetenceRecordIn> applicantCompetence;

    public UpdateApplicantCompetenceInDTO(JSONObject input) throws ApplicationException {
        this.email = input.optString("email", null);
        this.applicantCompetence = new ArrayList<ApplicantCompetenceRecordIn>();

        JSONArray array = input.optJSONArray("competenciaExperiencia", null);

        if (array == null || email == null) {
            throw new ApplicationException("Parametros inválidos", 422);
        }

        for (int i = 0; i < array.length(); i++) {
            this.applicantCompetence
                    .add(new ApplicantCompetenceRecordIn(array.getJSONObject(i).optString("competencia"),
                            array.getJSONObject(i).optInt("experiencia")));
        }
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<ApplicantCompetenceRecordIn> getApplicantCompetence() {
        return applicantCompetence;
    }
}
