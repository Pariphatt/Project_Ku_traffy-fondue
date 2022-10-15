package ku.cs.models.reports;

import ku.cs.models.account.Account;
import ku.cs.services.Filterer;
import ku.cs.services.IllegalConditionException;

import java.util.ArrayList;
import java.util.Collections;

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
    public ReportList findTypes(String types){
        ReportList reportList = new ReportList();
        for (Report report:reports){
            if (types.equals("หน่วยงานทั้งหมด")){
                reportList.addReport(report);
            }
            if ((report.getType()).equals(types)){
                reportList.addReport(report);
            }
        }
        return reportList;
    }
    public ReportList findSortBys(String sortBys){
        ReportList reportList = new ReportList();
        for (Report report:reports){
            if (sortBys.equals("เวลาที่เเจ้งเเละโหวตทั้งหมด")){
                reportList.addReport(report);
            }
            if ((report.getStatus()).equals(sortBys)){
                reportList.addReport(report);
            }
        }
        return reportList;
    }

    public ReportList findMyReport(String users){
        ReportList reportList = new ReportList();
        for (Report report:reports){
            if ((report.getUserReport()).equals(users)){
                reportList.addReport(report);
            }
        }
        return reportList;
    }

    public boolean isExistTopic(String topic) {
        for(Report report: reports) {
            if(report.getTopic().equals(topic)){
                return true;
            }
        }
        return false;
    }
}







