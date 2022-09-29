package ku.cs.models;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import ku.cs.models.account.Account;

public class Register {
    private String directoryName = "assets";
    private Map<String, Account> accountMap;

    public void validateRegister(String Name,String Username,String password,String confirmPassword,String Path) {
        String filePath = directoryName + File.separator + "users.csv";
        File file = new File(filePath);
        FileWriter writer = null;
        BufferedWriter buffer = null;

        try {
            writer = new FileWriter(file,true);
            buffer = new BufferedWriter(writer);
            if (Path == null){
            String user = Name + "," + Username + "," + password+","+"false"+","+"false"+","+"profile-user.png";
                buffer.append(user);
                buffer.newLine();
            }
            else {
                String user = Name + "," + Username + "," + password+","+"false"+","+"false"+","+ Path;
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
    public Collection<Account> getAccount() {
        return accountMap.values();
    }
}
