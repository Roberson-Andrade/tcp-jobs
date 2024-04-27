package vacancy.errors;

public class ApplicationException extends Exception {
    Integer status;

    public ApplicationException(String message, Integer status) {
        super(message);
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
