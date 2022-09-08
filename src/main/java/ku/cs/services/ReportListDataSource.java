package ku.cs.services;

import ku.cs.models.reports.Report;
import ku.cs.models.reports.ReportList;

import java.io.*;

public class ReportListDataSource implements DataSource<ReportList>{
    private String directoryName;
    private String fileName;

    public ReportListDataSource(String directoryName,String fileName){
        this.directoryName = directoryName;
        this.fileName = fileName;
        checkFileIsExited();
    }

    private void checkFileIsExited(){
        File file = new File(directoryName);
        if(!file.exists()){
            file.mkdirs();
        }

        String filePath = directoryName + File.separator + fileName;
        file = new File(filePath);
        if(!file.exists()){
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
        try {
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);

            String line = "";
            while ((line= buffer.readLine())!= null){
                String[] data =line.split(",");
                Report report = new Report(
                        data[0].trim(),
                        data[1].trim()
                );
                reportList.addReport(report);

            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
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
    public void writeData(ReportList reportList) {
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter writer = null;
        BufferedWriter buffer = null;

        try {
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);
            for (Report report : reportList.getAllReports()) {
                String line = report.getTopic() + ","
                        + report.getDetail();
                buffer.append(line);
                buffer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                buffer.close();
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        }
    }