package ku.cs.models.reports;

public class Vote {
    private String topic;
    private String userReport;
    private String topicAndUserReport;

    public Vote() {
        this.topic = null;
        this.userReport = null;
    }
    public Vote(String topicAndUserReport){
        this.topicAndUserReport = topicAndUserReport;
    }

    public Vote(String topic, String userReport) {
        this.topic = topic;
        this.userReport = userReport;
    }

    public String getTopic() {
        return topic;
    }
    public String getUserReport() {return userReport;}

    public String getTopicAndUserReport() {
        return topicAndUserReport;
    }
}
