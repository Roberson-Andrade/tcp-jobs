package jobs.domain.applicant.dto;

import java.util.List;

import codegen.jooq.tables.records.ApplicantCompetenceRecord;

public class FindApplicantCompetencesOutDTO {
  private List<ApplicantCompetenceRecord> competenciaExperiencia;

  public FindApplicantCompetencesOutDTO(List<ApplicantCompetenceRecord> competences) {
    this.competenciaExperiencia = competences;
  }

  public List<ApplicantCompetenceRecord> getCompetenciaExperiencia() {
    return this.competenciaExperiencia;
  }
}
