package jobs.domain.auth.dto;

public record CompanyDTO(String email,
    String password,
    String businessName,
    String cnpj,
    String description,
    String sector) {
}
