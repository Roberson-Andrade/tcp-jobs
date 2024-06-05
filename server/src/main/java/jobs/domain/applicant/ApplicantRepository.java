package jobs.domain.applicant;

import codegen.jooq.tables.Applicant;
import codegen.jooq.tables.ApplicantCompetence;
import codegen.jooq.tables.records.ApplicantCompetenceRecord;
import codegen.jooq.tables.records.ApplicantRecord;
import jobs.domain.applicant.dto.ApplicantCompetenceRecordIn;
import jobs.domain.auth.dto.ApplicantDTO;
import org.jooq.DSLContext;
import org.jooq.TableField;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ApplicantRepository {
    private final DSLContext ctx;

    public ApplicantRepository(DSLContext ctx) {
        this.ctx = ctx;
    }

    public ApplicantRecord findByEmail(String email) {
        return ctx.selectFrom(Applicant.APPLICANT).where(Applicant.APPLICANT.EMAIL.eq(email)).fetchOne();
    }

    public ApplicantRecord create(ApplicantDTO applicantDTO) {
        var applicantRecord = ctx.newRecord(Applicant.APPLICANT);

        applicantRecord.setEmail(applicantDTO.email());
        applicantRecord.setPassword(applicantDTO.password());
        applicantRecord.setName(applicantDTO.name());

        int stored = applicantRecord.store();

        if (stored == 1) {
            return applicantRecord;
        }

        return null;
    }

    public void update(String name, String email, String password) {
        Map<TableField<ApplicantRecord, String>, String> map = new LinkedHashMap<>();

        if (name != null) {
            map.put(Applicant.APPLICANT.NAME, name);
        }

        if (password != null) {
            map.put(Applicant.APPLICANT.PASSWORD, password);
        }

        ctx.update(Applicant.APPLICANT).set(map).where(Applicant.APPLICANT.EMAIL.eq(email)).execute();
    }

    public void delete(String email) {
        ctx.deleteFrom(Applicant.APPLICANT).where(Applicant.APPLICANT.EMAIL.eq(email)).execute();
    }

    public Integer addApplicantCompetences(String email,
            ArrayList<ApplicantCompetenceRecordIn> applicantCompetence) {
        int affectedRows = 0;

        for (var record : applicantCompetence) {
            var applicantRecord = ctx.newRecord(ApplicantCompetence.APPLICANT_COMPETENCE);

            applicantRecord.setApplicantEmail(email);
            applicantRecord.setId(record.competence());
            applicantRecord.setExperience(record.experience());

            int stored = applicantRecord.store();

            affectedRows += stored;
        }

        return affectedRows;
    }

    public List<ApplicantCompetenceRecord> findApplicantCompetences(String email) {
        return ctx.selectFrom(ApplicantCompetence.APPLICANT_COMPETENCE)
                .where(ApplicantCompetence.APPLICANT_COMPETENCE.APPLICANT_EMAIL.eq(email))
                .fetchInto(ApplicantCompetenceRecord.class);
    }
}
