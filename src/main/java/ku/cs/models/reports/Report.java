package ku.cs.models.reports;

public class Report {
    private String topic;
    private String detail;
    private String userReport;
    private String status;
    private int vote = 0;
    private String v;
    private String agency;
    private String solution;

    public Report(String topic, String detail){
        this.topic = topic;
        this.detail = detail;
        this.userReport = "b";
        this.agency = "A";
        this.status = "a";
        this.vote = 0;
        this.solution = "G";
    }

    public Report(String topic, String detail, String userReport, String agency, int vote, String status,String solution) {
        this.topic = topic;
        this.detail = detail;
        this.userReport = userReport;
        this.agency = agency;
        this.vote = vote;
        this.status = status;
        this.solution = solution;
    }

    public Report(String topic, String detail, int vote, String agency) {
        this.topic = topic;
        this.detail = detail;
        this.vote = vote;
        this.agency = agency;
        this.status = "ยังไม่ดำเนินการ";
        this.solution = " ";
    }


    public Report(int vote) {
        this.vote = vote;
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
        return v = Integer.toString(vote);
    }

    public String getAgency() {
        return agency;
    }

    @Override
    public String toString() {
        return topic + "("+vote+")";
    }

    public void addVote() {

    }

    public void setStatus(String status){
        this.status = status;

    }

    public void setSolution(String solution) {
        this.solution = solution;
    }
}
