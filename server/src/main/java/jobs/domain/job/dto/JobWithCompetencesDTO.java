package jobs.domain.job.dto;

import codegen.jooq.tables.records.JobCompetenceRecord;
import codegen.jooq.tables.records.JobRecord;

import java.util.List;

public class JobWithCompetencesDTO {
  private JobRecord job;
  private List<JobCompetenceRecord> competences;

  public JobWithCompetencesDTO(JobRecord job, List<JobCompetenceRecord> competences) {
    this.job = job;
    this.competences = competences;
  }

  // Getters and setters
  public JobRecord getJob() {
    return job;
  }

  public void setJob(JobRecord job) {
    this.job = job;
  }

  public List<JobCompetenceRecord> getCompetences() {
    return competences;
  }

  public void setCompetences(List<JobCompetenceRecord> competences) {
    this.competences = competences;
  }
}