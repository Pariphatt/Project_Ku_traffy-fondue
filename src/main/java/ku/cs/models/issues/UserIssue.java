package ku.cs.models.issues;

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
