package ku.cs.services;

import ku.cs.models.account.AccountList;
import ku.cs.models.account.Account;
import ku.cs.models.account.StaffAccount;
import ku.cs.models.account.UserAccount;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);

            String line = "";
            while ((line = buffer.readLine()) != null) {
                if (line.startsWith("#")) continue;
                String[] data = line.split(",");
                String role = data[0];
                Account account = null;
                if (role.equals("admin")) {
                    account = new Account("admin",data[2], data[1], data[3],data[4]);
                } else if (role.equals("staff")) {
                    account = new StaffAccount("staff",data[2], data[1], data[3],data[4],data[5],data[6]);
                    ((StaffAccount) account).setAgency(data[5]);
                    ((StaffAccount) account).setCategory(data[6]);
                } else if (role.equals("User")) {
                    account = new UserAccount("User",data[2], data[1], data[3],data[4]);
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
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);
            for (Account account : accountList.getAllUsers()) {
                if ("staff".equals(account.getRole())) {
                    String line = "staff" + "," + account.getUsername() + "," + account.getName() + ","
                            + account.getPassword() + ","
                            + account.getPicPath() + ","
                            + (((StaffAccount) account).getAgency())+","+
                            (((StaffAccount) account).getCategory());
                    buffer.write(line);
                    buffer.newLine();
                    buffer.flush();

                } else if ("User".equals(account.getRole())) {
                    String line = "User" + "," + account.getUsername() + "," + account.getName() + ","
                            + account.getPassword() + ","
                            + account.getPicPath() + ","
                            + (((UserAccount) account).isBan());
                    buffer.write(line);
                    buffer.newLine();
                    buffer.flush();
                }
                else if ("admin".equals(account.getRole())){
                    String line = "admin" + "," + account.getUsername() + "," + account.getName() + ","
                            + account.getPassword() + ","
                            + account.getPicPath() + ","
                            + "null";
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
    public void writeDataLog(String log){
        String filePath = directoryName + File.separator + "log.csv";
        File file = new File(filePath);
        FileWriter writer = null;
        BufferedWriter buffer = null;


        try {
            writer = new FileWriter(file, true);
            buffer = new BufferedWriter(writer);
            buffer.write(log);
            buffer.newLine();
            buffer.flush();

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

    public void log(T t) {
        if (t instanceof StaffAccount) {
            String temp = "";
            StaffAccount staffAccount = (StaffAccount) t;
            //#role,name,username,agency,date,picPath
            Date currentDate = new Date();
            SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            temp += staffAccount.getRole()+ "," + staffAccount.getName()+ "," +
                    staffAccount.getUsername()+","+
                    staffAccount.getAgency()+","+
                    dateFormat.format(currentDate)+" "+timeFormat.format(currentDate)+","+
                    staffAccount.getPicPath();
                    writeDataLog(temp);
        }
        else if (t instanceof UserAccount){
            String temp = "";
            UserAccount userAccount = (UserAccount) t;
            //#role,name,username,agency,date,picPath
            Date currentDate = new Date();
            SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            temp += userAccount.getRole()+ "," + userAccount.getName()+ "," +
                    userAccount.getUsername()+","+
                    "ไม่มี"+","+
                    dateFormat.format(currentDate)+" "+timeFormat.format(currentDate)+","+
                    userAccount.getPicPath();
            writeDataLog(temp);
        }
    }

}
