package ku.cs.models.reports;

public class Report {
    private String topic;
    private String detail;
    private String userReport;
    private String status;
    private int vote;
    private String agency;
    private String solution;
    private String category;

    public Report(String topic, String detail){
        this.topic = topic;
        this.detail = detail;
        this.userReport = "b";
        this.category = "A";
        this.status = "a";
        this.vote = 0;
        this.solution = "G";
    }


    public Report(String topic, String detail, String userReport, String category, int vote, String solution, String status){

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
        this.category = category;
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

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return topic + "("+vote+")";
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }
}
