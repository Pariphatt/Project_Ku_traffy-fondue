package ku.cs.models;

import java.io.*;

public class Login {
    private String directoryName = "assets";
    private String directoryName1 = "assets";
    private String directoryName2 = "assets";

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
    public  boolean adminLogin(String userName,String passWord){
        String filePath1 = directoryName1 + File.separator + "users.csv";
        File file = new File(filePath1);

        FileReader reader = null;
        BufferedReader buffer = null;
        try {
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);

            String line ="";
            while ((line=buffer.readLine()) != null){
                String[] data = line.split(",");
                if (data[1].equals(userName) && data[2].equals(passWord) && data[3].equals("true")){
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
    public  boolean staffLogin(String userName,String passWord){
        String filePath2 = directoryName2 + File.separator + "staff.csv";
        File file = new File(filePath2);

        FileReader reader = null;
        BufferedReader buffer = null;
        try {
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);

            String line ="";
            while ((line=buffer.readLine()) != null){
                String[] data = line.split(",");
                if (data[1].equals(userName) && data[2].equals(passWord) && data[3].equals("false") && data[4].equals("true")){
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
