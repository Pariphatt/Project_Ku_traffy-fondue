package ku.cs.services;

import ku.cs.models.reports.Report;
import ku.cs.models.reports.ReportList;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReportFIleDataSource implements DataSource<ReportList> {
    private String directoryName;
    private String fileName;
    private  ArrayList<String[]> removePost;

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
    public List<Report> readReportPost(){
        List<Report> reports = new ArrayList<>();
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
                if (data.length == 4){
                    report = new Report(data[0].strip(),data[1].strip(),data[2].strip(),data[3].strip());
                    reports.add(report);
                }

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
      return reports;
    }
    public void reportPost(Report report,String reasons){
        String topic = report.getTopic();
        String type = report.getType();
        String detail = report.getDetail();
        String temp = topic+","+type+","+detail+","+reasons;
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileWriter writer = null;
        BufferedWriter buffer = null;
        try{
            writer = new FileWriter(file,true);
            buffer = new BufferedWriter(writer);
                buffer.append(temp);
                buffer.newLine();


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

    public ArrayList<String[]> getRemovePost() {
        return removePost;
    }

    public void removeReportPost(Report report){
        removePost = new ArrayList<>();
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileReader reader = null;
        BufferedReader buffer = null;
        try{
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);

            String line = "";
            while((line = buffer.readLine()) != null) {
                String[] data = line.split(",");
                removePost.add(data);

            }
            int i = 0;
            if (removePost.size() != 0){
                for(String[] temp : removePost){

                    if (temp[0].equals(report.getTopic())){
                        removePost.remove(i);
                    }
                    i++;
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {

                buffer.close();
                reader.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }
    public void writeFileDynamic(ArrayList<String[]> removePost){
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter writer = null;
        BufferedWriter buffer1 = null;
        try{
            writer = new FileWriter(file);
            buffer1 = new BufferedWriter(writer);

            for(String[] temp : removePost){
                String temp1 = temp[0]+","+temp[1]+","+temp[2]+","+temp[3];
                buffer1.append(temp1);
                buffer1.newLine();

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                writer.flush();
                buffer1.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
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
    public ReportList readFileDelete(){
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


                 Report report = new Report(data[0].trim(),data[1].trim(),data[2].trim(),data[3].trim());
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

    public void writeFileDelete(ReportList reportList){
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter writer = null;
        BufferedWriter buffer1 = null;
        try{
            writer = new FileWriter(file);
            buffer1 = new BufferedWriter(writer);
            String line = "";

            for(Report report : reportList.getaAllReport()){
                line = report.getTopic() + "," +
                        report.getType() + "," +
                        report.getDetail() + "," +
                        report.getReasonsPost();
                buffer1.append(line);
                buffer1.newLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                writer.flush();
                buffer1.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
