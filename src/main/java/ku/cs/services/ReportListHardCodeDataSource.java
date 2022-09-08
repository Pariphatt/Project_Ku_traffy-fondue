package ku.cs.services;

import ku.cs.models.reports.Report;
import ku.cs.models.reports.ReportList;

public class ReportListHardCodeDataSource implements DataSource<ReportList>{
    private ReportList reportList;
    public ReportListHardCodeDataSource(){reportList= new ReportList();}


    @Override
    public ReportList readData() {
        return null;
    }

    @Override //Plymorphism
    public void writeData(ReportList report) {reportList= report;}

    public ReportList getReportList(){return reportList;}
}
