package ku.cs.services;

import ku.cs.models.bans.Banned;
import ku.cs.models.bans.BannedList;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class BannedUserListFileDataSource implements DataSource<BannedList>{
    private String directoryName;
    private String fileName;

    public BannedUserListFileDataSource(String directoryName, String fileName){
        this.directoryName = directoryName;
        this.fileName = fileName;
        checkFileIsExisted();
    }
    public BannedUserListFileDataSource(){
        directoryName = "assets";
        fileName = "bannedUsers.csv";
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
    public BannedList readData() {
        BannedList bannedList = new BannedList();
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
                Banned bannedUser = null;
                if (data.length == 3){
                    bannedUser = new Banned(data[0].trim(),Integer.parseInt(data[1]),data[2].trim());
                }
                bannedList.addBannedUser(bannedUser);
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
        return bannedList;
    }
    @Override
    public void writeData(BannedList bannedList) {
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileWriter writer = null;
        BufferedWriter buffer = null;
        try {
            writer = new FileWriter(file, StandardCharsets.UTF_8);
            buffer = new BufferedWriter(writer);

            for (Banned bannedUser: bannedList.getBannedUsers()){
                String line = bannedUser.getUserName().trim()+","
                        +bannedUser.getLoginAttempt()+","
                        +bannedUser.getReason();
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
