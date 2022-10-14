package ku.cs.models.issue;

import java.util.ArrayList;

public class UserListIssue {
    private ArrayList<UserIssue> userIssues;
    public UserListIssue(){userIssues = new ArrayList<UserIssue>();}

    public ArrayList<UserIssue> getAllUserIssue(){return userIssues;}
    public void addUserIssue(UserIssue userIssue){userIssues.add(userIssue);}
    public void deleteUserIssue(UserIssue user){
        userIssues.remove(user);
    }
    public UserIssue searchUser(String userName){
        for (UserIssue user:userIssues){
            System.out.println(user.getName());
            if (user.getName().equals(userName)){
                return user;
            }
        };
        return null;
    }
    public UserListIssue deletedBan(String nameCheck){
        UserListIssue data = new UserListIssue();
        for (UserIssue userIssue:userIssues){
            if (!userIssue.getName().equals(nameCheck)){
                data.addUserIssue(userIssue);
            }
        }
        return data;
    }
}
