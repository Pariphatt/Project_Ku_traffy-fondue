package ku.cs.models.reports;

public class TopicReport {
    private String topic;
    private String detail;
    private String agency;
    private String vote;
    private String status;

    public TopicReport(String topic, String detail) {
        this.topic = topic;
        this.detail = detail;
        status = "ยังไม่ดำเนินการ";
    }
    public void defineDetail(String detail){
        this.detail = detail;
    }

    public String getTopic() {
        return topic;
    }

    public String getDetail() {
        return detail;
    }

    public String getAgency() {
        return agency;
    }

    public String getVote() {
        return vote;
    }

    public String getStatus() {
        return status;
    }
}
