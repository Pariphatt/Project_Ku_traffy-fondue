package ku.cs.models.reports;

public class Vote {
    private String topic;
    private String userReport;
    private int vote;

    public Vote(String topic, String userReport, int vote) {
        this.topic = topic;
        this.userReport = userReport;
        this.vote = vote;
    }

    public String getTopic() {
        return topic;
    }
    public String getUserReport() {
        return userReport;
    }
    public int getVote() {return vote;}



}
