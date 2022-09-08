package ku.cs.models.account;

public class UserAccount extends Account{
    private String lastLogin;
    private String userStatus;
    private int loginAttempts;
    private String userImage;

    public UserAccount(String name, String username, String password, String userStatus, int loginAttempts, String lastLogin) {
        super(name, username, password);
        this.lastLogin = lastLogin;
        this.userStatus= userStatus;
        this.loginAttempts = loginAttempts;
        this.userImage = "-";
    }

    public UserAccount(String role, String name, String username, String password, String userStatus, int loginAttempts, String lastLogin, String userImage) {
        super(name, username, password);
        this.lastLogin = lastLogin;
        this.userStatus= userStatus;
        this.loginAttempts = loginAttempts;
        this.userImage = userImage;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public int getLoginAttempts() {
        return loginAttempts;
    }

    public String getUserImage() {
        return userImage;
    }


}
