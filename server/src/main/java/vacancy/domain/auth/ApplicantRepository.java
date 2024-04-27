package vacancy.domain.auth;

import codegen.jooq.tables.Applicant;
import codegen.jooq.tables.records.ApplicantRecord;
import org.jooq.DSLContext;
import vacancy.domain.auth.dto.ApplicantDTO;

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

        if(stored == 1) {
            return applicantRecord;
        }

        return null;
    }
}
