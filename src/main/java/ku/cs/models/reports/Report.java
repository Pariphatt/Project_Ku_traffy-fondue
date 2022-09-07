package ku.cs.models.reports;

public class Report {
    private String topic;
    private String agency;
    private String detail;
    private String status;
    public Report(String topic, String agency, String detail) {
        this.topic = topic;
        this.agency = agency;
        this.detail = detail;
    }

    @Override
    public String toString() {
        return topic + "    "+agency
                ;
    }

    public String getTopic() {
        return topic;
    }

    public String getAgency() {
        return agency;
    }

    public String getDetail() {
        return detail;
    }

    public String getStatus() {
        return status;
    }
}
