package ku.cs.services;

import ku.cs.models.account.AccountList;
import ku.cs.models.account.Account;
import ku.cs.models.account.StaffAccount;
import ku.cs.models.account.UserAccount;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
                Account account;
                if (role.equals("admin")) {
                    account = new Account(data[2], data[1], data[3]);
                } else if (role.equals("staff")) {
                    account = new StaffAccount("staff",data[2], data[1], data[3],data[4],data[5] );
                    ((StaffAccount) account).setAgency(data[5]);
                } else if (role.equals("User")) {
                    account = new UserAccount("User",data[2], data[1], data[3],data[4]);
                    ((UserAccount) account).setBan(Boolean.parseBoolean(data[5]));
                } else {
                    account = new Account(data[2], data[1], data[3]);
                }
                String picPath = data[4];
                account.setPicPath(picPath);
                userList.addUser(account);
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
            writer = new FileWriter(file, true);
            buffer = new BufferedWriter(writer);
            for (Account account : accountList.getAllUsers()) {
                if ("staff".equals(account.getRole())) {
                    String line = "staff" + "," + account.getUsername() + "," + account.getName() + ","
                            + account.getPassword() + ","
                            + account.getPicPath() + ","
                            + (((StaffAccount) account).getAgency());
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
                    "-"+","+
                    dateFormat.format(currentDate)+" "+timeFormat.format(currentDate)+","+
                    userAccount.getPicPath();
            writeDataLog(temp);
        }
    }


    public boolean ChangePassword(String userName,String currentPassword,String newPassword){
        ArrayList<String[]> accounts = new ArrayList<>();
        String headFileAccount = "#role,username,name,password,picPath,(user:isBan|staff:agency|admin:null),date,Attempts";
        String filePath = directoryName + File.separator + fileName;
        String value = "";
        File file = new File(filePath);
        FileReader reader = null;
        BufferedReader buffer = null;
        FileWriter writer = null;
        BufferedWriter bufferedWriter = null;
        boolean checkProcess = false;

        try {
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);


            String line = "";
            while ((line = buffer.readLine()) != null) {
                System.out.println(line);
                if (line.startsWith("#")) {
                    continue;
                }

                String[] data = line.split(",");
                if ( data[3].equals(currentPassword) && data[1].equals(userName)){
                    checkProcess=true;
                    data[3]= newPassword;
                }
                accounts.add(data);

            }
             writer = new FileWriter(file);
             bufferedWriter = new BufferedWriter(writer);
             bufferedWriter.write(headFileAccount);
            for (String[] temp : accounts){

                for (int i = 0 ; i< temp.length; i++){
                    if (i==0){
                        value=temp[i];
                    }
                    else {
                        value = value+ "," + temp[i];
                    }
                }
                bufferedWriter.write("\n"+value);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                buffer.close();
                reader.close();
                bufferedWriter.close();
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return checkProcess;
    }
}
