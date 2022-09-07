package ku.cs.services;

import ku.cs.models.reports.Report;
import ku.cs.models.reports.ReportList;

public class ReportListDataSource {
    private ReportList reportList;
    public ReportListDataSource() {
        reportList = new ReportList();
        readData();
    }
    public void readData() {
        Report topic1 = new Report("ไฟไหม้อาคาร", "อาคารและสถานที่","----------------------------------------------------------------------");
        Report topic2 = new Report("การจราจร", "กองยานพาหนะ", "00000000000000000000000000000000000000000000000000000000");

        reportList.addReport(topic1);
        reportList.addReport(topic2);
    }
    public ReportList getReportList() {
        return reportList;
    }
}
