package ku.cs.services;

import ku.cs.models.accounts.AccountList;
import ku.cs.models.accounts.Account;
import ku.cs.models.accounts.StaffAccount;
import ku.cs.models.accounts.UserAccount;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class AccountListDataSource<T> implements DataSource<AccountList> {
    private String directoryName;
    private String fileName;
    private AccountList accountList;

    public AccountListDataSource(String directoryName, String fileName) {
        this.directoryName = directoryName;
        this.fileName = fileName;
        accountList = new AccountList();
        checkFileIsExisted();
    }


    private void checkFileIsExisted() {
        File file = new File(directoryName);
        if (!file.exists()) {
            file.mkdirs();
        }

        String filePath = directoryName + File.separator + fileName;
        file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public AccountList readData() {
        AccountList userList = new AccountList();
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader reader = null;
        BufferedReader buffer = null;
        try {
            reader = new FileReader(file, StandardCharsets.UTF_8);
            buffer = new BufferedReader(reader);

            String line = "";
            while ((line = buffer.readLine()) != null) {
                if (line.startsWith("#")) continue;
                String[] data = line.split(",");
                String role = data[0];
                Account account = null;
                if (role.equals("admin")) {
                    account = new Account("admin",data[2], data[1], data[3],data[4],data[6]);

                } else if (role.equals("staff")) {
                    account = new StaffAccount("staff",data[2], data[1], data[3],data[4],data[5],data[6]);
                    ((StaffAccount) account).setAgency(data[5]);
                } else if (role.equals("User")) {
                    account = new UserAccount("User",data[2], data[1], data[3],data[4],data[6]);
                    ((UserAccount) account).setBan(Boolean.parseBoolean(data[5]));
                }
                if (account != null){
                    String picPath = data[4];
                    account.setPicPath(picPath);
                    userList.addUser(account);
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
        return userList;

    }

    @Override
    public void writeData(AccountList accountList) {
        String filePath = directoryName + File.separator + "accounts.csv";
        File file = new File(filePath);
        FileWriter writer = null;
        BufferedWriter buffer = null;


        try {
            writer = new FileWriter(file, StandardCharsets.UTF_8);
            buffer = new BufferedWriter(writer);
            for (Account account : accountList.getAllUsers()) {
                if ("staff".equals(account.getRole())) {
                    String line = "staff" + "," + account.getUsername() + "," + account.getName() + ","
                            + account.getPassword() + ","
                            + account.getPicPath() + ","
                            + (((StaffAccount) account).getAgency())+","
                            + account.getLastLogin();
                    buffer.write(line);
                    buffer.newLine();
                    buffer.flush();

                } else if ("User".equals(account.getRole())) {
                    String line = "User" + "," + account.getUsername() + "," + account.getName() + ","
                            + account.getPassword() + ","
                            + account.getPicPath() + ","
                            + (((UserAccount) account).isBan()) +","
                            + account.getLastLogin();
                    buffer.write(line);
                    buffer.newLine();
                    buffer.flush();
                }
                else if ("admin".equals(account.getRole())){
                    String line = "admin" + "," + account.getUsername() + "," + account.getName() + ","
                            + account.getPassword() + ","
                            + account.getPicPath() + ","
                            + "null"+","
                            +account.getLastLogin();
                    buffer.write(line);
                    buffer.newLine();
                    buffer.flush();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
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
