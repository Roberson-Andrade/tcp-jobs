package jobs.domain.job;

import codegen.jooq.tables.Job;
import codegen.jooq.tables.JobCompetence;
import codegen.jooq.tables.records.JobCompetenceRecord;
import codegen.jooq.tables.records.JobRecord;
import jobs.domain.job.dto.AddJobInDTO;
import jobs.domain.job.dto.JobDTO;
import org.jooq.DSLContext;

import java.util.List;

public class JobRepository {
    private final DSLContext ctx;

    public JobRepository(DSLContext ctx) {
        this.ctx = ctx;
    }

    public JobRecord create(AddJobInDTO jobDTO, String email) {
        var jobRecord = ctx.newRecord(Job.JOB);

        jobRecord.setName(jobDTO.getName());
        jobRecord.setCompanyEmail(email);
        jobRecord.setSalaryRange(jobDTO.getSalaryRange());
        jobRecord.setDescription(jobDTO.getDescription());
        jobRecord.setState(jobDTO.getState());

        int stored = jobRecord.store();

        for (var competence : jobDTO.getCompetences()) {
            var jobCompetenceRecord = ctx.newRecord(JobCompetence.JOB_COMPETENCE);

            jobCompetenceRecord.setCompetenceId(competence);
            jobCompetenceRecord.setJobId(jobRecord.getId());

            jobCompetenceRecord.store();
        }

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

    public List<JobCompetenceRecord> findCompetences(Integer id) {
        return ctx.selectFrom(JobCompetence.JOB_COMPETENCE).where(JobCompetence.JOB_COMPETENCE.JOB_ID.eq(id))
                .fetchInto(JobCompetenceRecord.class);
    }

    public void delete(String email, Integer id) {
        ctx.deleteFrom(Job.JOB).where(Job.JOB.ID.eq(id)).and(Job.JOB.COMPANY_EMAIL.eq(email)).execute();
    }

    public void update(Integer id, JobDTO jobDTO) {
        ctx.deleteFrom(JobCompetence.JOB_COMPETENCE).where(JobCompetence.JOB_COMPETENCE.JOB_ID.eq(id)).execute();

        for (var competence : jobDTO.competences()) {
            var jobCompetenceRecord = ctx.newRecord(JobCompetence.JOB_COMPETENCE);

            jobCompetenceRecord.setCompetenceId(competence);
            jobCompetenceRecord.setJobId(id);

            jobCompetenceRecord.store();
        }

        ctx.update(Job.JOB)
                .set(Job.JOB.NAME, jobDTO.name())
                .set(Job.JOB.SALARY_RANGE, jobDTO.salaryRange())
                .set(Job.JOB.DESCRIPTION, jobDTO.description())
                .set(Job.JOB.STATE, jobDTO.state())
                .where(Job.JOB.ID.eq(id))
                .execute();
    }
}
