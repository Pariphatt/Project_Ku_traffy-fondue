package ku.cs.models.reports;

public class Vote {
    private String topic;
    private String userReport;

    public Vote() {
        this.topic = null;
        this.userReport = null;
    }

    public Vote(String topic, String userReport) {
        this.topic = topic;
        this.userReport = userReport;
    }

    public String getTopic() {
        return topic;
    }
    public String getUserReport() {return userReport;}
}
