package ku.cs.models.reports;

import ku.cs.services.Filterer;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collector;

public class ReportList {
    private ArrayList<Report> reports;
    public ReportList(){reports = new ArrayList<Report>();}

    public ArrayList<Report> getaAllReport(){return reports;}
    public void addReport(Report report){
        reports.add(report);
    }
    public ReportList filter(Filterer<Report> filterer){
        ReportList filtered = new ReportList();
        for(Report report:reports){
            if(filterer.filter(report)){
                filtered.addReport(report);
            }
        }
        return filtered;
    }
}
