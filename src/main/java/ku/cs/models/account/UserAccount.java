package ku.cs.models.account;

public class UserAccount extends Account{

    private boolean isBan;

    public UserAccount(String role,String name, String username, String password, String picPath) {
        super(role, name, username, password, picPath);
    }

    public void setBan(boolean ban) {
        isBan = ban;
    }

    public boolean isBan() {
        return isBan;
    }
}
