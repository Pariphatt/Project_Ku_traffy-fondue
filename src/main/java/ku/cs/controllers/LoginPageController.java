package ku.cs.controllers;
import animatefx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import ku.cs.models.bans.Banned;
import ku.cs.models.bans.BannedList;
import ku.cs.models.Mode;
import ku.cs.models.accounts.Account;
import ku.cs.models.accounts.AccountList;
import ku.cs.models.accounts.StaffAccount;
import ku.cs.models.accounts.UserAccount;
import ku.cs.models.issues.UserIssue;
import ku.cs.models.issues.UserIssueList;
import ku.cs.services.AccountListDataSource;
import ku.cs.services.DataSource;
import ku.cs.services.BannedUserListFileDataSource;
import ku.cs.services.UserListIssueDataSource;

import java.io.IOException;

public class LoginPageController {
    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private Label loginMessageLabel;
    private Alert alert;
    private DataSource<AccountList> dataSource;
    private AccountList accountList;
    @FXML private AnchorPane pane;
    private UserListIssueDataSource userListIssueDataSource;
    private UserIssueList userIssueList;
    private BannedUserListFileDataSource bannedUserListFileDataSource;
    private BannedList bannedList;


    @FXML
    public void initialize() {
        alert = new Alert(Alert.AlertType.NONE);
        dataSource = new AccountListDataSource("assets", "accounts.csv");
        accountList = dataSource.readData();
        Mode.setMode(pane);
        new FadeIn(pane).play();

    }
    public void handleLoginButton(ActionEvent actionEvent){

       if (username.getText().isEmpty() &&
               password.getText().isEmpty()){
           alert.setAlertType(Alert.AlertType.WARNING);
           alert.setContentText("Please enter username and password");
           alert.show();
       }

       else {
            Account account = accountList.findUser(username.getText());
            if (account == null) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("username is incorrect.");
                alert.show();
            } else {
                if (!account.isPassword(password.getText())) {
                    account.loginFailed();
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setContentText("password is incorrect.");
                    alert.show();
                }

                else {
                    account.loginPass();
                    dataSource.writeData(accountList);
                    if (account instanceof StaffAccount) {
                        try {
                            com.github.saacsos.FXRouter.goTo("staff_page", account.getUsername());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    } else if (account instanceof UserAccount) {
                        try {
                            if (((UserAccount) account).isBan() == false){
                                com.github.saacsos.FXRouter.goTo("welcome_page", account.getUsername());
                            }
                            else {
                                userListIssueDataSource = new UserListIssueDataSource();
                                userIssueList = (UserIssueList) userListIssueDataSource.readData();
                                UserIssue userIssue = userIssueList.searchUser(account.getUsername());
                                userListIssueDataSource.writeData(userIssueList);
                                bannedUserListFileDataSource= new BannedUserListFileDataSource();
                                bannedList = bannedUserListFileDataSource.readData();
                                Banned banned = bannedList.searchUser(account.getUsername());
                                banned.setLoginAttempt();
                                bannedUserListFileDataSource.writeData(bannedList);
                                alert.setAlertType(Alert.AlertType.ERROR);
                                alert.setContentText("คุณถูกระงับการใช้งาน");
                                alert.show();
                                com.github.saacsos.FXRouter.goTo("request_unban_page", account.getUsername());
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        try {
                            com.github.saacsos.FXRouter.goTo("admin_page", account.getUsername());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }

                }
            }
       }
    }


    public void handleBackButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("home_page");
        }catch (IOException e) {
        }
    }
}
