package dtos;

public class LoginRequest {
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String  password;
    private String username;
    private String reEnterPassword;
}
