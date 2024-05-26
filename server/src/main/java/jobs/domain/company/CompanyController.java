package jobs.domain.company;

import codegen.jooq.tables.records.CompanyRecord;
import jobs.domain.company.dto.*;
import jobs.utils.controller.BaseController;
import org.json.JSONObject;

public class CompanyController extends BaseController {
    final CompanyRepository companyRepository;

    public CompanyController(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public JSONObject find(JSONObject input) {
        FindCompanyInDTO data = new FindCompanyInDTO(input);

        CompanyRecord company = this.companyRepository.findByEmail(data.getEmail());

        if (company == null) {
            return this.json(new FindCompanyOutDTO(404, "E-mail não encontrado"));
        }

        return this.json(new FindCompanyOutDTO(201, company));
    }

    public JSONObject update(JSONObject input) {
        UpdateCompanyInDTO data = new UpdateCompanyInDTO(input);

        if (this.companyRepository.findByEmail(data.getEmail()) == null) {
            return this.json(new UpdateCompanyOutDTO(404, "E-mail não encontrado"));
        }

        this.companyRepository.update(data.getBusinessName(), data.getEmail(), data.getPassword(), data.getSector(),
                data.getCnpj(), data.getDescription());

        return this.json(new UpdateCompanyOutDTO(201));
    }

    public JSONObject delete(JSONObject input) {
        DeleteCompanyInDTO data = new DeleteCompanyInDTO(input);

        this.companyRepository.delete(data.getEmail());

        return this.json(new DeleteCompanyOutDTO(201));
    }
}
