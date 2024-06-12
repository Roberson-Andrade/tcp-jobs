package jobs.domain.job.dto;

import jobs.utils.dto.BaseOutDTO;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class FilterJobsOutDTO extends BaseOutDTO {
    JSONArray vagas;

    public FilterJobsOutDTO(List<JobWithCompetencesDTO> jobs) {
        super(201);

        this.vagas = new JSONArray();

        for (JobWithCompetencesDTO job : jobs) {
            JSONObject jobDTO = new JSONObject();
            JSONArray competences = new JSONArray();

            for (var competence : job.getCompetences()) {
                competences.put(competence.getCompetenceId());
            }

            jobDTO.put("idVaga", job.getJob().getId());
            jobDTO.put("nome", job.getJob().getName());
            jobDTO.put("faixaSalarial", job.getJob().getSalaryRange());
            jobDTO.put("descricao", job.getJob().getDescription());
            jobDTO.put("estado", job.getJob().getState());
            jobDTO.put("competencias", competences);

            this.vagas.put(jobDTO);
        }
    }

    public JSONArray getVagas() {
        return vagas;
    }
}
