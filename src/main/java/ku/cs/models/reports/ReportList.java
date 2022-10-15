package ku.cs.models.reports;

import ku.cs.models.account.Account;
import ku.cs.services.Filterer;
import ku.cs.services.IllegalConditionException;

import java.util.ArrayList;
import java.util.Collections;

public class ReportList {
    private ArrayList<Report> reports;

    public ReportList() {
        reports = new ArrayList<Report>();
    }

    public ArrayList<Report> getaAllReport() {
        return reports;
    }

    public void addReport(Report report) {
        reports.add(report);
    }

    public ReportList filter(Filterer<Report> filterer) {
        ReportList filtered = new ReportList();
        for (Report report : reports) {
            if (filterer.filter(report)) {
                filtered.addReport(report);
            }
        }
        return filtered;
    }


    public ReportList findMyReport(String users) {
        ReportList reportList = new ReportList();
        for (Report report : reports) {
            if ((report.getUserReport()).equals(users)) {
                reportList.addReport(report);
            }
        }
        return reportList;
    }

}







