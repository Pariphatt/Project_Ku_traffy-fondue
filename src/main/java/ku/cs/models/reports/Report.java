package ku.cs.models.reports;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Report {

    private Map<String, TopicReport> topicReportMap;
    public Report(){topicReportMap = new TreeMap<>();}
    public void addReport(String topic, String detail){topicReportMap.put(topic, new TopicReport(topic, detail));
    }
    public void addReport(String topic, TopicReport topicReport){topicReportMap.put(topic, topicReport);
    }

    public TopicReport find(String vocab){
        TopicReport found = topicReportMap.get(vocab);
        if (found == null){
            throw new RuntimeException(vocab + " not found in Report");
        }
        return found;
    }
    public void defineDetailTopic(String topic, String detail){
        TopicReport found = find(topic);
        found.defineDetail(detail);
    }

    public Set<String> getaAllReport(){
        return topicReportMap.keySet();
    }
    public Report filterBy(Filterer<TopicReport> filterer){
        Report filtered = new Report();
        for (String topic: topicReportMap.keySet()){
            TopicReport found = find(topic);
            if(filterer.filter(found)){
                filtered.addReport(topic, found);
            }
        }

        return filtered;

    }


}
