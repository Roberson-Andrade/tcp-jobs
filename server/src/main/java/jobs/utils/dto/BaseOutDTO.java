package jobs.utils.dto;

public abstract class BaseOutDTO {
    int status;
    String mensagem;

    public BaseOutDTO(int status) {
        this.status = status;
    }

    public BaseOutDTO(int status, String message) {
        this.status = status;
        this.mensagem = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMensagem() {
        return mensagem;
    }
}
