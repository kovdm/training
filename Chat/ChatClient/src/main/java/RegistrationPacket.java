import java.io.Serializable;

public class RegistrationPacket implements Serializable {

    private String name;
    private String phoneNumber;
    private String login;
    private String password;

    public RegistrationPacket(String name, String phoneNumber, String login, String password) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.login = login;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
