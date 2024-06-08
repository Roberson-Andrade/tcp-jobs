package jobs.domain.job;

import java.util.List;

import org.jooq.DSLContext;

import codegen.jooq.tables.Job;
import codegen.jooq.tables.records.JobRecord;
import jobs.domain.job.dto.AddJobInDTO;
import jobs.domain.job.dto.JobDTO;

public class JobRepository {
  private final DSLContext ctx;

  public JobRepository(DSLContext ctx) {
    this.ctx = ctx;
  }

  public JobRecord create(AddJobInDTO jobDTO) {
    var jobRecord = ctx.newRecord(Job.JOB);

    jobRecord.setName(jobDTO.getName());
    jobRecord.setField(jobDTO.getField());
    jobRecord.setSalaryRange(jobDTO.getSalaryRange());
    jobRecord.setExperience(jobDTO.getExperience());
    jobRecord.setDescription(jobDTO.getDescription());

    int stored = jobRecord.store();

    if (stored == 1) {
      return jobRecord;
    }

    return null;
  }

  public List<JobRecord> findAll() {
    return ctx.selectFrom(Job.JOB).fetchInto(JobRecord.class);
  }

  public List<JobRecord> findAllByEmail(String email) {
    return ctx.selectFrom(Job.JOB).where(Job.JOB.COMPANY_EMAIL.eq(email)).fetchInto(JobRecord.class);
  }

  public JobRecord find(Integer id) {
    return ctx.selectFrom(Job.JOB).where(Job.JOB.ID.eq(id)).fetchOneInto(JobRecord.class);
  }

  public void delete(String email, Integer id) {
    ctx.deleteFrom(Job.JOB).where(Job.JOB.ID.eq(id)).and(Job.JOB.COMPANY_EMAIL.eq(email)).execute();
  }

  public void update(Integer id, JobDTO jobDTO) {
    ctx.update(Job.JOB)
        .set(Job.JOB.FIELD, jobDTO.field())
        .set(Job.JOB.SALARY_RANGE, jobDTO.salaryRange())
        .set(Job.JOB.EXPERIENCE, jobDTO.experience())
        .set(Job.JOB.DESCRIPTION, jobDTO.description())
        .where(Job.JOB.ID.eq(id))
        .execute();
  }
}
