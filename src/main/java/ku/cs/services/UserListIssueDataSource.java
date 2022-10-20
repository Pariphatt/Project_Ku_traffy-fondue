package ku.cs.services;

import ku.cs.models.issues.UserIssue;
import ku.cs.models.issues.UserIssueList;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class UserListIssueDataSource implements DataSource<UserIssueList>{
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
    @Override
    public UserIssueList readData() {
        UserIssueList userIssueList = new UserIssueList();
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileReader reader = null;
        BufferedReader buffer = null;
        try{
            reader = new FileReader(file, StandardCharsets.UTF_8);
            buffer = new BufferedReader(reader);
            String line = "";

            while((line = buffer.readLine()) != null){
                String[] data = line.split(",");
                UserIssue userIssue = null;
                if (data.length == 4){
                    userIssue = new UserIssue(data[0].trim(),Integer.parseInt(data[1]),Boolean.parseBoolean(data[2]),data[3].trim());
                }
                userIssueList.addUserIssue(userIssue);
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
        return userIssueList;
    }
    @Override
    public void writeData(UserIssueList userIssueList) {
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileWriter writer = null;
        BufferedWriter buffer = null;
        try {
            writer = new FileWriter(file, StandardCharsets.UTF_8);
            buffer = new BufferedWriter(writer);

            for (UserIssue userIssue: userIssueList.getAllUserIssue()){
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
