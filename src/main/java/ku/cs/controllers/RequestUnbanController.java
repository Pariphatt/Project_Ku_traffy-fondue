package ku.cs.controllers;

import animatefx.animation.FadeIn;
import com.github.saacsos.FXRouter;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import ku.cs.models.Ban.Banned;
import ku.cs.models.Ban.BannedList;
import ku.cs.models.Mode;
import ku.cs.models.account.Account;
import ku.cs.models.account.AccountList;
import ku.cs.models.issue.UserIssue;
import ku.cs.models.issue.UserListIssue;
import ku.cs.services.AccountListDataSource;
import ku.cs.services.BannedUserListFileDataSource;
import ku.cs.services.UserListIssueDataSource;

import java.io.IOException;



public class RequestUnbanController {
    @FXML
    TextField reasonTextField;
    private BannedList bannedList;
    private BannedUserListFileDataSource bannedUserListFileDataSource;

    private Account account;
    private UserListIssueDataSource userListIssueDataSource;
    private UserListIssue userListIssue;
    private UserIssue userIssue;
    private Account accounts;
    private AccountListDataSource userListDataSource;
    private AccountList accountList;
    @FXML private AnchorPane pane;

    public void initialize(){
        bannedUserListFileDataSource = new BannedUserListFileDataSource();
        bannedList = bannedUserListFileDataSource.readData();
        userListIssueDataSource = new UserListIssueDataSource();
        userListIssue = (UserListIssue) userListIssueDataSource.readData();
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
        com.github.saacsos.FXRouter.goTo("home");

    }
    @FXML
    void handleBackButton(javafx.event.ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("home");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
