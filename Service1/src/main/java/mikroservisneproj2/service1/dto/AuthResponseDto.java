package mikroservisneproj2.service1.dto;

public class AuthResponseDto {

    private String jwt;

    private String message;

    public AuthResponseDto() {
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
