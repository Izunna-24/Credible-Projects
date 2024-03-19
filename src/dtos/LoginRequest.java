package dtos;

public class LoginRequest {
    private String username;
    private String  password;
    private String confirmPassword;
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public String getReEnterPassword() {
        return confirmPassword;
    }

    public void setReEnterPassword(String reEnterPassword) {
        this.confirmPassword = reEnterPassword;
    }


}
