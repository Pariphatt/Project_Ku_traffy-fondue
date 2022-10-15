package ku.cs.models.Ban;

import ku.cs.models.issue.UserIssue;

import java.util.ArrayList;

public class BannedList {
    private ArrayList<Banned> bannedUsers;
    public BannedList(){bannedUsers = new ArrayList<Banned>();}
    public ArrayList<Banned> getBannedUsers(){return bannedUsers;}

    public void addBannedUser(Banned userBanned){bannedUsers.add(userBanned);}

    public Banned searchUser(String userName){
        for (Banned bannedUser:bannedUsers){
            if (bannedUser.getUserName().equals(userName)){
                return bannedUser;
            }
        };
        return null;
    }
    public void removeBannedUser(String name){
        Banned find = searchUser(name);
        bannedUsers.remove(find);
    }
}
