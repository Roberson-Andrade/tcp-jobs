package jobs.domain.applicant.dto;

import jobs.utils.dto.BaseOutDTO;

public class FindApplicantOutDTO extends BaseOutDTO {
    String nome;
    String senha;

    public FindApplicantOutDTO(int status, String message) {
        super(status, message);
    }

    public FindApplicantOutDTO(int status, String name, String password) {
        super(status);
        this.nome = name;
        this.senha = password;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }
}
