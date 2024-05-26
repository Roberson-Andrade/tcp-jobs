package jobs.domain.company.dto;

import codegen.jooq.tables.records.CompanyRecord;
import jobs.utils.dto.BaseOutDTO;

public class FindCompanyOutDTO extends BaseOutDTO {
    String senha;
    String razaoSocial;
    String cnpj;
    String descricao;
    String ramo;

    public FindCompanyOutDTO(int status, String message) {
        super(status, message);
    }

    public FindCompanyOutDTO(int status, CompanyRecord company) {
        super(status);
        this.razaoSocial = company.getBusinessName();
        this.cnpj = company.getCnpj();
        this.descricao = company.getDescription();
        this.ramo = company.getSector();
        this.senha = company.getPassword();
    }

    public String getSenha() {
        return senha;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getRamo() {
        return ramo;
    }
}
