package jobs.domain.auth.dto;

import org.json.JSONObject;

public class SignInCompanyInDTO {
    String email;
    String password;
    String businessName;
    String cnpj;
    String description;
    String sector;

    public SignInCompanyInDTO(JSONObject input) {
        this.email = input.getString("email");
        this.businessName = input.getString("razaoSocial");
        this.cnpj = input.getString("cnpj");
        this.description = input.getString("descricao");
        this.sector = input.getString("ramo");

        try {
            this.password = input.getString("senha");
        } catch (Exception e) {
            this.password = Integer.toString(input.getInt("senha"));
        }
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getBusinessName() {
        return businessName;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getDescription() {
        return description;
    }

    public String getSector() {
        return sector;
    }
}
