package ku.cs.models;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import ku.cs.models.UserList;

public class Register {
    private String directoryName = "assets";
    public void validateRegister(String Name,String Username,String password,String confirmPassword,String Path) {
        String filePath = directoryName + File.separator + "users.csv";
        File file = new File(filePath);
        FileWriter writer = null;
        BufferedWriter buffer = null;

        try {
            writer = new FileWriter(file,true);
            buffer = new BufferedWriter(writer);
            if (Path == null){
            String user = Name + "," + Username + "," + password+","+"false"+","+"false"+","+"src\\main\\resources\\ku\\cs\\images\\profile-user.png";
                buffer.append(user);
                buffer.newLine();
            }
            else {
                String user = Name + "," + Username + "," + password+","+"false"+","+"false"+","+Path;
                buffer.append(user);
                buffer.newLine();
            }




        }
        catch (IOException e) {
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
