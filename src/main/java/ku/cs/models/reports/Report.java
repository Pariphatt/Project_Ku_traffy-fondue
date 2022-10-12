package ku.cs.models.reports;

public class Report {
    private String topic;
    private String detail;
    private String userReport;
    private String status;
    private int vote;
    private String agency;
    private String solution;
    private String type;
    private String userName;
    private String staffUser;

    public Report(String topic, String detail,String account,String type,String agency,String userName){
        this.topic = topic;
        this.detail = detail;
        this.userReport = account;
        this.type = type;
        this.status = "ยังไม่ดำเนินการ";
        this.vote = 0;
        this.solution = "";
        this.agency = agency;
        this.userName = userName;
    }

    public String getAgency() {
        return agency;
    }

    public Report(String topic, String detail, String userReport, String type, int vote, String solution, String status,String agency,String staffUser){

        this.topic = topic;
        this.detail = detail;
        this.userReport = userReport;
        this.type = type;
        this.vote = vote;
        this.status = status;
        this.solution = solution;
        this.agency = agency;
        this.staffUser =staffUser;
    }


    public Report(String topic, String detail, int vote, String type) {
        this.topic = topic;
        this.detail = detail;
        this.type = type;
        this.vote = vote;
        this.status = "ยังไม่ดำเนินการ";
        this.solution = " ";
    }


    public String getSolution() {
        return solution;
    }

    public String getTopic() {
        return topic;
    }

    public String getDetail() {
        return detail;
    }

    public String getUserReport() {
        return userReport;
    }

    public String getStatus() {
        return status;
    }

    public String getVote() {
        String v = Integer.toString(vote);
        return v;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return topic + "("+vote+")"  ;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }
}
