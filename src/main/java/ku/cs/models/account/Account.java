package ku.cs.models.account;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Account {
    private String name;
    private String username;
    private String password;
    private String picPath;
    private String lastLogin;
    private int loginAttempts;
    private String role;




    public Account(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }
    public Account(){}

    public Account(String role, String name, String username, String password,String picPath,String lastLogin) {
        this.role =role;
        this.name=name;
        this.username=username;
        this.password=password;
        this.picPath=picPath;
        this.lastLogin = lastLogin;
    }



    public String getRole() {
        return role;
    }


    public void loginFailed() {
        increaseLoginAttempts();
    }

    public void loginPass() {
        clearLoginAttempts();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        setLastLogin(dtf.format(now));
    }

    private void increaseLoginAttempts() {
        loginAttempts++;
    }

    private void clearLoginAttempts() {
        loginAttempts = 0;
    }


    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getPicPath() {
        return picPath;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastLogin() {

        return lastLogin;
    }


    public boolean isPassword(String password) {
        return this.password.equals(password);
    }

    public boolean validPassword(String password) {
        return ((password.matches("^[a-zA-Z0-9]{6,20}$")));
    }

    public boolean validUsername(String username) {
        System.out.println( (username.matches("^[a-zA-Z]+(.+){3,20}$")));
        return  ( (username.matches("^[a-zA-Z]+(.+){3,20}$")));
    }

    @Override
    public String toString() {
        return
                "name: " + name + '\'' +
                ", username: " + username + '\'' +
                ", วันที่เข้าใช้งานล่าสุด: "+lastLogin+'\'' +
                ", role: " + role + '\'';
    }

}
