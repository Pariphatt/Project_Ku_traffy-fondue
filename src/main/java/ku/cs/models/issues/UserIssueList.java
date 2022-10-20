package ku.cs.models.issues;

import java.util.ArrayList;

public class UserIssueList {
    private ArrayList<UserIssue> userIssues;
    public UserIssueList(){userIssues = new ArrayList<UserIssue>();}

    public ArrayList<UserIssue> getAllUserIssue(){return userIssues;}
    public void addUserIssue(UserIssue userIssue){userIssues.add(userIssue);}
    public void deleteUserIssue(UserIssue user){
        userIssues.remove(user);
    }
    public UserIssue searchUser(String userName){
        for (UserIssue user:userIssues){
            if (user.getName().equals(userName)){
                return user;
            }
        };
        return null;
    }
    public UserIssueList deletedBan(String nameCheck){
        UserIssueList data = new UserIssueList();
        for (UserIssue userIssue:userIssues){
            if (!userIssue.getName().equals(nameCheck)){
                data.addUserIssue(userIssue);
            }
        }
        return data;
    }
}
