package ku.cs.models.issue;

public class UserIssue {
    private String name;
    private int loginAttempt;
    private boolean ban;
    private String reason;

    public UserIssue(String name, int loginAttempt, boolean ban, String reason) {
        this.name = name;
        this.loginAttempt = loginAttempt;
        this.ban = ban;
        this.reason = reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public UserIssue(String name, String reason, String status) {
//        if (status.equals("Ban")){
//            this.name = name;
//            this.loginAttempt = 0;
//            this.ban = false;
//            this.reason = reason;
//        } else if (status.equals("Unban")) {
//            this.name = name;
//            this.loginAttempt = 0;
//            this.ban = true;
//            this.reason = reason;
//        }

    }

    public void setLoginAttempt() {
        this.loginAttempt += 1;
    }

    public String getName() {
        return name;
    }

    public int getLoginAttempt() {
        return loginAttempt;
    }

    public boolean isBan() {
        return ban;
    }

    public String getReason() {
        return reason;
    }


    @Override
    public String toString() {
        return "name='" + name ;
    }
}
