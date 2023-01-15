package mikroservisneproj2.service1.dto.internal;

public class OperationResultDto {

    private String message;

    private boolean success;

    public OperationResultDto() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
