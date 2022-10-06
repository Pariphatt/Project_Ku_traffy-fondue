package ku.cs.models.account;

public class StaffAccount extends Account{
    private String agency;



    public StaffAccount(String role, String name, String username, String password, String picPath,String agency) {
        super(role,name, username, password, picPath);
        this.agency=agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getAgency() {
        return agency;
    }
}
