package jobs.domain.job.dto;

import codegen.jooq.tables.records.JobCompetenceRecord;
import codegen.jooq.tables.records.JobRecord;
import jobs.utils.dto.BaseOutDTO;
import org.json.JSONArray;

import java.util.List;

public class GetJobByIdOutDTO extends BaseOutDTO {
    private Double faixaSalarial;
    private String descricao;
    private String estado;
    private JSONArray competencias;

    public GetJobByIdOutDTO(Integer status, JobRecord job, List<JobCompetenceRecord> competences) {
        super(status);

        this.faixaSalarial = job.getSalaryRange();
        this.descricao = job.getDescription();
        this.estado = job.getState();

        this.competencias = new JSONArray();
        for (var competence : competences) {
            this.competencias.put(competence.getCompetenceId());
        }
    }

    public Double getFaixaSalarial() {
        return faixaSalarial;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getEstado() {
        return estado;
    }

    public JSONArray getCompetencias() {
        return competencias;
    }
}
