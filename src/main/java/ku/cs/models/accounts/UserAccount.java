package ku.cs.models.accounts;

public class UserAccount extends Account{

    private boolean isBan;


    public UserAccount(String role,String name, String username, String password, String picPath,String lastLogin) {
        super(role, name, username, password, picPath,lastLogin);
    }

    public void setBan(boolean ban) {
        isBan = ban;
    }

    public boolean isBan() {
        return isBan;
    }
}
