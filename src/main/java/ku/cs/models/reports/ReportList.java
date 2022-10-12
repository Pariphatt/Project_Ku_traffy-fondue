package ku.cs.models.reports;

import ku.cs.services.Filterer;

import java.util.ArrayList;

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
    public ReportList findStatus(String status){
        ReportList reportList = new ReportList();
        for (Report report:reports){
            if (status.equals("ทั้งหมด")){
                reportList.addReport(report);
            }
            if ((report.getStatus()).equals(status)){
                reportList.addReport(report);
            }
        }
        return reportList;
    }

    public ReportList findType(String type){
        ReportList reportList = new ReportList();
        for (Report report:reports){
            if (type.equals("ทั้งหมด")){
                reportList.addReport(report);
            }
            if ((report.getType()).equals(type)){
                reportList.addReport(report);
            }
        }
        return reportList;
    }

}
