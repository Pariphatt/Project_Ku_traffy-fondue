package ku.cs.models.reports;

public class Report {
    private String topic;
    private String detail;
    private String userReport;
    private String status;
    private int vote = 0;
    private String v;
    private String agency;

    public Report(String topic, String detail, String userReport, String agency, int vote, String status) {
        this.topic = topic;
        this.detail = detail;
        this.userReport = userReport;
        this.status = status;
        this.vote = vote;
        this.agency = agency;
    }

    public Report(String topic, String detail, String userReport, String agency){
        this.topic = topic;
        this.detail = detail;
        this.userReport = userReport;
        this.agency = agency;
        this.vote = 0;
        this.status = "ยังไม่ดำเนินการ";
    }
    public Report(String topic, String detail, String userReport, String agency, int vote){
        this.topic = topic;
        this.detail = detail;
        this.userReport = userReport;
        this.agency = agency;
        this.vote = vote;
        this.status = "ยังไม่ดำเนินการ";
    }

    public Report(int vote) {
        this.vote = vote;
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


}
