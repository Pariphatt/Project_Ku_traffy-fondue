package ku.cs.models.reports;

public class Report {
    private String topic;
    private String agency;
    private String detail;

    public Report(String topic, String detail) {
        this.topic = topic;
        this.detail = detail;
    }

    public String getTopic() {
        return topic;
    }


    public String getDetail() {
        return detail;
    }

    @Override
    public String toString() {
        return "topic='" + topic + '\'' +
                ", detail='" + detail + '\'' ;
    }
    @Override
    public boolean equals(Object obj){
        if ( obj instanceof Report) {
            Report r = (Report) obj;
            if (r != null) {
                if (topic.equals(r.topic) && agency.equals(r.agency) && detail.equals(r.detail)) {
                    return true;
                }
            }
        }
        return false;
    }
}