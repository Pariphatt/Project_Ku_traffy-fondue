package ku.cs.models.reports;

import java.util.ArrayList;

public class ReportList {
    private ArrayList<Report> reports;
    public ReportList() {
        // ใช้ new เพื'อสร้าง instance ของ ArrayList
        reports = new ArrayList<>();
    }
    public void addReport(Report report) {
        // เรียก method add จาก ArrayList เพื'อเพิ'มข้อมูล
        reports.add(report);
    }
    public ArrayList<Report> getAllReports() {
        return reports;
    }
}
