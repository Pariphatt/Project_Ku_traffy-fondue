package ku.cs.models.account;

public class StaffAccount extends Account{
    private String agency;

    public StaffAccount(String name, String username, String password) {
        super(name, username, password);
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getAgency() {
        return agency;
    }
}
