package ku.cs.models.reports;

public class Report {
    private String topic;
    private String detail;
    private String userReport;
    private String status;
    private int vote = 0;
    private String v;
    private String category;
    private String solution;

    public Report(String topic, String detail, String userReport, String category, int vote, String status, String solution) {
        this.topic = topic;
        this.detail = detail;
        this.userReport = userReport;
        this.category = category;
        this.vote = vote;
        this.status = status;
        this.solution = solution;
    }

    public Report(String topic, String detail, int vote, String category) {
        this.topic = topic;
        this.detail = detail;
        this.vote = vote;
        this.category = category;
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

    public String getCategory() {
        return category;
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
