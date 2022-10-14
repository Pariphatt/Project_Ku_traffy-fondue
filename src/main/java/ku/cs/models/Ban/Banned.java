package ku.cs.models.Ban;

public class Banned {
    String userName;
    int loginAttempt;
    String reason;

    public Banned(String userName, int loginAttempt, String reason) {
        this.userName = userName;
        this.loginAttempt = loginAttempt;
        this.reason = reason;
    }

    public Banned(String userName, String reason) {
        this.userName = userName;
        this.loginAttempt = 0;
        this.reason = reason;
    }

    public String getUserName() {
        return userName;
    }

    public int getLoginAttempt() {
        return loginAttempt;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setLoginAttempt() {
        this.loginAttempt +=1;
    }

    @Override
    public String toString() {
        return userName ;
    }

}
