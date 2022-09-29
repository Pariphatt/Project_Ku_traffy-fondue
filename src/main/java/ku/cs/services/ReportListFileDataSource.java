package ku.cs.services;

import ku.cs.models.reports.Report;
import ku.cs.models.reports.TopicReport;

import java.io.*;

public class ReportListFileDataSource {
    private String directoryName;
    private String fileName;

    public ReportListFileDataSource(String directoryName, String fileName){
        this.directoryName = directoryName;
        this.fileName = fileName;
        checkFileIsExisted();
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
    public Report readData() {
        Report reports = new Report();
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileReader reader = null;
        BufferedReader buffer = null;
        try {
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);

            String line = "";
            while ((line = buffer.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 2) {
                    reports.addReport(
                            data[0].trim(),
                            data[1].trim()
                    );
                }// add topic
                if (data.length >= 3) {
                    reports.defineDetailTopic(
                            data[0].trim(),
                            data[2].trim()
                    ); // add detail
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try{
                buffer.close();
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return reports;
    }
    public void writeData(Report report){
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileWriter writer = null;
        BufferedWriter buffer = null;
        try{
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);

            for (String topic : report.getaAllReport()){
                TopicReport topicNow = report.find(topic);// find word
                String line = topicNow.getTopic().trim() + "," + topicNow.getDetail().trim() + "," ; // add vocab,part,meaning
                buffer.append(line);
                buffer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                buffer.close();
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
