package ku.cs.models;

import java.util.ArrayList;
import java.util.Collections;

public class UserList {
    private ArrayList<User> userList;

    public UserList() {
        userList = new ArrayList<>();
    }

    public void addUser(User user) {
        userList.add(user);
    }

    public ArrayList<User> getAllUsers() {
        return userList;
    }
}


