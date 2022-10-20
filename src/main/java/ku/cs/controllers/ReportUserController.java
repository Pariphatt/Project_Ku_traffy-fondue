package ku.cs.controllers;

import animatefx.animation.FadeIn;
import com.github.saacsos.FXRouter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import ku.cs.models.Ban.Banned;
import ku.cs.models.Ban.BannedList;
import ku.cs.models.Mode;
import ku.cs.models.account.Account;
import ku.cs.models.account.AccountList;
import ku.cs.models.account.UserAccount;
import ku.cs.models.issue.UserIssue;
import ku.cs.models.issue.UserListIssue;
import ku.cs.services.AccountListDataSource;
import ku.cs.services.DataSource;
import ku.cs.services.BannedUserListFileDataSource;

import javafx.scene.control.*;
import ku.cs.services.UserListIssueDataSource;

import java.io.IOException;

public class ReportUserController {
    @FXML private Label userNameLabel;
    @FXML private Label loginAttemptsLabel;
    @FXML private Label banLabel;
    @FXML private Label reasonLabel;
    @FXML private Label requestUnbanLabel;
    @FXML private ListView userIssueListView;
//    @FXML ImageView imageView;


    private Account accounts;
    private UserAccount selectedAccount;
    private UserListIssue userListIssue;
    private UserIssue userIssue;
    private DataSource dataSource;
    private AccountList accountList;
    private AccountListDataSource userListDataSource;
    private UserListIssueDataSource userListIssueDataSource;
    private BannedUserListFileDataSource bannedUserListFileDataSource;
    private BannedList bannedList;
    @FXML private AnchorPane pane;
    @FXML
    public void initialize() {
//        userListIssueDataSource = new UserListIssueDataSource();
//        userListIssue = (UserListIssue) userListIssueDataSource.readData();
        userListIssueDataSource = new UserListIssueDataSource("assets","userIssues.csv");
        userListIssue = userListIssueDataSource.readData();
//        UserIssue userban = userListIssue.searchUser("pedpap");
//        System.out.println(userban.getName());
        accounts = new Account();
        userListDataSource = new AccountListDataSource("assets", "accounts.csv");
        accountList = userListDataSource.readData();

        bannedUserListFileDataSource = new BannedUserListFileDataSource();
        bannedList = bannedUserListFileDataSource.readData();

        accounts = accountList.findUser((String) FXRouter.getData());
        showListView();
        clearSelectedUser();
        handleSelectedListView();
        Mode.setMode(pane);
        new FadeIn(pane).play();
    }

    @FXML
    void handleBackButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("admin");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า admin ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    @FXML
    void handleGoToBannedUserButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("banned_user");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า admin ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    private void showListView(){
        userIssueListView.getItems().clear();
        userListIssue = userListIssueDataSource.readData();
        bannedList = bannedUserListFileDataSource.readData();
        userIssueListView.getItems().addAll(userListIssue.getAllUserIssue());
        userIssueListView.refresh();
    }

    private void clearSelectedUser(){
        loginAttemptsLabel.setText("");
        banLabel.setText("");
        reasonLabel.setText("");
        userNameLabel.setText("");
    }
    private void handleSelectedListView(){
        userIssueListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<UserIssue>() {
            @Override
            public void changed(ObservableValue observableValue, UserIssue oldValue, UserIssue newValue) {
                if (newValue != null){userIssue = newValue;
                    selectedAccount = (UserAccount) accountList.findUser(newValue.getName());
                    showSelectedUser(newValue);}
            }
        });
    }
    private void showSelectedUser(UserIssue userIssue){
        loginAttemptsLabel.setText(String.valueOf(userIssue.getLoginAttempt()));
        userNameLabel.setText(userIssue.getName());
        banLabel.setText(String.valueOf(userIssue.isBan()));
        reasonLabel.setText(userIssue.getReason());
    }

    @FXML
    void handleLogoutButton(javafx.event.ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า admin ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    @FXML
    void handleBanButton(javafx.event.ActionEvent actionEvent) {
        selectedAccount.setBan(!userIssue.isBan());
        userListIssue = userListIssue.deletedBan(userIssue.getName());
        userListIssueDataSource.writeData(userListIssue);
        userListDataSource.writeData(accountList);
        bannedList.addBannedUser(new Banned(userIssue.getName(), 0,null));
        bannedUserListFileDataSource.writeData(bannedList);
        showListView();
        clearSelectedUser();
    }


}
