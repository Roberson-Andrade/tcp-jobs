package jobs.domain.applicant.dto;

import codegen.jooq.tables.records.ApplicantCompetenceRecord;
import jobs.utils.dto.BaseOutDTO;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class FindApplicantCompetencesOutDTO extends BaseOutDTO {
    private JSONArray competenciaExperiencia = new JSONArray();

    public FindApplicantCompetencesOutDTO(List<ApplicantCompetenceRecord> competences) {
        super(201);
        for (ApplicantCompetenceRecord competence : competences) {
            competenciaExperiencia.put(new JSONObject()
                    .put("competencia", competence.getCompetenceId())
                    .put("experiencia", competence.getExperience()));
        }
    }

    public JSONArray getCompetenciaExperiencia() {
        return this.competenciaExperiencia;
    }
}
