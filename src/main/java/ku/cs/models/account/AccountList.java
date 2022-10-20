package ku.cs.models.account;

import ku.cs.models.account.Account;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class AccountList {
    private ArrayList<Account> accountList;

    public AccountList() {
        accountList = new ArrayList<>();
    }

    public void addUser(Account user) {
        accountList.add(user);
    }

    public ArrayList<Account> getAllUsers() {
        return accountList;
    }

    public Account findUser(String username) {
        Account user = null;
        for (Account temp : accountList) {
            if (username.equals(temp.getUsername())) {
                user = temp;
            }
        }
        return user;
    }
    public Account changePassword(String username) {
        for (Account temp : accountList) {
            if (username.equals(temp.getUsername())) {
                return temp;
            }
        }
        return null;
    }
    public Account findPassword(String username, String password) {
        for (Account temp : accountList) {
            if (username.equals(temp.getUsername()) && password.equals(temp.getPassword())) {
                return temp;
            }
        }
        return null;
    }

    public Account changePicture(String username) {
        for (Account temp : accountList) {
            if (username.equals(temp.getUsername())) {
                return temp;
            }
        }
        return null;
    }

    public boolean isExistUsername(String username) {
        for(Account account: accountList) {
            System.out.println("test");
            if(account.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }




}


