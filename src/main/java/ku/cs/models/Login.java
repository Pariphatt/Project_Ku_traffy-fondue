package ku.cs.models;

import java.io.*;

public class Login {
    private String directoryName = "assets";
    public  boolean validateLogin(String userName,String passWord){
        String filePath = directoryName + File.separator + "users.csv";
        File file = new File(filePath);

        FileReader reader = null;
        BufferedReader buffer = null;
        try {
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);

            String line ="";
            while ((line=buffer.readLine()) != null){
                String[] data = line.split(",");
                if (data[1].equals(userName) && data[2].equals(passWord) && data[3].equals("false")){
                    return true;
                }


            }
            return false;


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

    }
}
