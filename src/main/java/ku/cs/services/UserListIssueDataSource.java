package ku.cs.services;

import ku.cs.models.issue.UserIssue;
import ku.cs.models.issue.UserListIssue;
import ku.cs.models.reports.Report;

import java.io.*;

public class UserListIssueDataSource implements DataSource<UserListIssue>{
    private String directoryName;
    private String fileName;

    public UserListIssueDataSource(String directoryName, String fileName){
        this.directoryName = directoryName;
        this.fileName = fileName;
        checkFileIsExisted();
    }
    public UserListIssueDataSource(){
        directoryName = "assets";
        fileName = "userIssues.csv";
    }

    private void checkFileIsExisted() {
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
    @Override
    public UserListIssue readData() {
        UserListIssue userListIssue = new UserListIssue();
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
                UserIssue userIssue = null;
                if (data.length == 4){
                    userIssue = new UserIssue(data[0].trim(),Integer.parseInt(data[1]),Boolean.parseBoolean(data[2]),data[3].trim());
                }
                userListIssue.addUserIssue(userIssue);
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
        return userListIssue;
    }
    @Override
    public void writeData(UserListIssue userListIssue) {
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileWriter writer = null;
        BufferedWriter buffer = null;
        try {
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);

            for (UserIssue userIssue: userListIssue.getAllUserIssue()){
                String line = userIssue.getName().trim()+","
                        +userIssue.getLoginAttempt()+","
                        +userIssue.isBan()+","
                        +userIssue.getReason();
                buffer.append(line);
                buffer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try{
                buffer.close();
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
