package jobs.domain.company;

import codegen.jooq.tables.Company;
import codegen.jooq.tables.records.CompanyRecord;
import jobs.domain.auth.dto.CompanyDTO;
import org.jooq.DSLContext;
import org.jooq.TableField;

import java.util.LinkedHashMap;
import java.util.Map;

public class CompanyRepository {
    private final DSLContext ctx;

    public CompanyRepository(DSLContext ctx) {
        this.ctx = ctx;
    }

    public CompanyRecord findByEmail(String email) {
        return ctx.selectFrom(Company.COMPANY).where(Company.COMPANY.EMAIL.eq(email)).fetchOne();
    }

    public CompanyRecord create(CompanyDTO companyDTO) {
        var companyRecord = ctx.newRecord(Company.COMPANY);

        companyRecord.setEmail(companyDTO.email());
        companyRecord.setPassword(companyDTO.password());
        companyRecord.setBusinessName(companyDTO.businessName());
        companyRecord.setCnpj(companyDTO.cnpj());
        companyRecord.setDescription(companyDTO.description());
        companyRecord.setSector(companyDTO.sector());

        int stored = companyRecord.store();

        if (stored == 1) {
            return companyRecord;
        }

        return null;
    }

    public void update(String businessName, String email, String password, String sector, String cnpj, String description) {
        Map<TableField<CompanyRecord, String>, String> map = new LinkedHashMap<>();

        if (businessName != null) {
            map.put(Company.COMPANY.BUSINESS_NAME, businessName);
        }

        if (password != null) {
            map.put(Company.COMPANY.PASSWORD, password);
        }

        if (sector != null) {
            map.put(Company.COMPANY.SECTOR, sector);
        }

        if (cnpj != null) {
            map.put(Company.COMPANY.CNPJ, cnpj);
        }

        if (description != null) {
            map.put(Company.COMPANY.DESCRIPTION, description);
        }

        ctx.update(Company.COMPANY).set(map).where(Company.COMPANY.EMAIL.eq(email)).execute();
    }

    public void delete(String email) {
        ctx.deleteFrom(Company.COMPANY).where(Company.COMPANY.EMAIL.eq(email)).execute();
    }
}
