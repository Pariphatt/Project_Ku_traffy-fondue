package ku.cs.services;

import ku.cs.models.account.StaffAccount;
import ku.cs.models.reports.Report;
import ku.cs.models.reports.ReportList;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportFIleDataSource implements DataSource<ReportList> {
    private String directoryName;
    private String fileName;

    public ReportFIleDataSource(String directoryName, String fileName){
        this.directoryName = directoryName;
        this.fileName = fileName;
        checkFileIsExisted();
    }

    public ReportFIleDataSource() {
        directoryName = "assets";
        fileName = "reports.csv";
    }

    private void checkFileIsExisted(){
        File file = new File(directoryName);
        if ( ! file.exists()){
            file.mkdirs();
        }
        String filePath = directoryName + File.separator + fileName;
        file = new File(filePath);
        if ( ! file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    public ReportList readData() {
        ReportList reportList = new ReportList();
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileReader reader = null;
        BufferedReader buffer = null;
        try{
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);

            String line = "";
            while((line = buffer.readLine()) != null){
                String[] data = line.split(",");
                Report report = null;
                if (data.length == 10){
                    Date currentDate = new Date();
                    report = new Report(data[0].trim(),data[1].trim(),data[2].trim()
                            ,data[3].trim(),Integer.parseInt(data[4].trim()),
                            data[5].trim(),data[6],data[7],data[8],data[9]);
                }
                reportList.addReport(report);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                buffer.close();
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        return reportList;
    }
    @Override
    public void writeData(ReportList reportList){
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileWriter writer = null;
        BufferedWriter buffer = null;
        try{
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);

            for (Report report : reportList.getaAllReport()){
                String line = report.getTopic().trim() + ","
                        + report.getDetail().trim() + ","
                        + report.getUserReport().trim()+","
                        +report.getType().trim()+","
                        +report.getVote()+","
                        +report.getSolution()+ ","
                        +report.getStatus()+ ","
                        +report.getAgency()+ ","
                        +report.getStaffReport()+","
                        +report.getReportTime();


                buffer.append(line);
                buffer.newLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                buffer.close();
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
