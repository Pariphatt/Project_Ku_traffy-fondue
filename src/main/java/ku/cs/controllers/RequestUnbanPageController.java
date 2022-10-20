package ku.cs.controllers;

import com.github.saacsos.FXRouter;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import ku.cs.models.bans.Banned;
import ku.cs.models.bans.BannedList;
import ku.cs.models.Mode;
import ku.cs.models.accounts.Account;
import ku.cs.models.accounts.AccountList;
import ku.cs.models.issues.UserIssue;
import ku.cs.models.issues.UserIssueList;
import ku.cs.services.AccountListDataSource;
import ku.cs.services.BannedUserListFileDataSource;
import ku.cs.services.UserListIssueDataSource;

import java.io.IOException;



public class RequestUnbanPageController {
    @FXML
    TextField reasonTextField;
    private BannedList bannedList;
    private BannedUserListFileDataSource bannedUserListFileDataSource;

    private Account account;
    private UserListIssueDataSource userListIssueDataSource;
    private UserIssueList userIssueList;
    private UserIssue userIssue;
    private Account accounts;
    private AccountListDataSource userListDataSource;
    private AccountList accountList;
    @FXML private AnchorPane pane;

    public void initialize(){
        bannedUserListFileDataSource = new BannedUserListFileDataSource();
        bannedList = bannedUserListFileDataSource.readData();
        userListIssueDataSource = new UserListIssueDataSource();
        userIssueList = (UserIssueList) userListIssueDataSource.readData();
        accounts = new Account();
        userListDataSource = new AccountListDataSource("assets", "accounts.csv");
        accountList = userListDataSource.readData();
        accounts = accountList.findUser((String) FXRouter.getData());
        Mode.setMode(pane);
    }

    @FXML
    void handleSubmitButton(javafx.event.ActionEvent actionEvent) throws IOException {
        String reason;
        reason = reasonTextField.getText();
        Banned find = bannedList.searchUser(accounts.getUsername());
        if (find == null){
            Banned banned = new Banned(accounts.getUsername(),reason);
            bannedList.addBannedUser(banned);
        }
        else {
            find.setReason(reason);
        }
        bannedUserListFileDataSource.writeData(bannedList);
        reasonTextField.clear();
        com.github.saacsos.FXRouter.goTo("home_page");

    }
    @FXML
    void handleBackButton(javafx.event.ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("home_page");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
