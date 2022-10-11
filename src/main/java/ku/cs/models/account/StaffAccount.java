package ku.cs.models.account;

public class StaffAccount extends Account{
    private String agency;
    private String category;



    public StaffAccount(String role, String name, String username, String password, String picPath,String agency,String lastLogin) {
        super(role,name, username, password, picPath,lastLogin);
        this.agency=agency;
    }
    public boolean isAgengy(String agen){
        if(agen.equals(agency)) return true;
        return false;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAgency() {
        return agency;
    }
}
