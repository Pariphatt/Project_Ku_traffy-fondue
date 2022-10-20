package ku.cs.controllers;

import animatefx.animation.FadeIn;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import ku.cs.models.bans.Banned;
import ku.cs.models.bans.BannedList;
import ku.cs.models.Mode;
import ku.cs.models.accounts.AccountList;
import ku.cs.models.accounts.UserAccount;
import ku.cs.services.AccountListDataSource;
import ku.cs.services.BannedUserListFileDataSource;

import java.io.IOException;

public class BannedUserPageController {
    @FXML
    private Label appealLabel;
    @FXML private Label nameLabel;

    @FXML
    private ListView<Banned> bannedUserListView;

    @FXML
    private Label loginAttemptLabel;
    @FXML private AnchorPane pane;
    private UserAccount selectedAccount;
    private AccountListDataSource accountListDataSource;
    private AccountList accountList;
    private BannedUserListFileDataSource bannedUserListFileDataSource;
    private BannedList bannedList;
    private Banned selectedUser;


    public void initialize(){
        Mode.setMode(pane);
        new FadeIn(pane).play();
        bannedUserListFileDataSource = new BannedUserListFileDataSource();
        bannedList = bannedUserListFileDataSource.readData();

        accountListDataSource = new AccountListDataSource("assets", "accounts.csv");
        accountList = accountListDataSource.readData();

        showListView();
        clearSelectedUser();
        handleSelectedUser();
        Mode.setMode(pane);
    }

    private void showListView(){
        bannedUserListView.getItems().clear();
        bannedList = bannedUserListFileDataSource.readData();
        bannedUserListView.getItems().addAll(bannedList.getBannedUsers());
        bannedUserListView.refresh();
    }
    private void clearSelectedUser(){
        nameLabel.setText("");
        loginAttemptLabel.setText("");
        appealLabel.setText("");
    }
    private void handleSelectedUser(){
        bannedUserListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Banned>() {
            @Override
            public void changed(ObservableValue<? extends Banned> observableValue, Banned oldValue, Banned newValue) {
                if (newValue != null){
                    selectedAccount = (UserAccount) accountList.findUser(newValue.getUserName());
                    selectedUser = bannedList.searchUser(newValue.getUserName());
                    showSelectedUser(newValue);
                }
            }
        });
    }
    private void showSelectedUser(Banned banned){
        loginAttemptLabel.setText(String.valueOf(banned.getLoginAttempt()));
        appealLabel.setText(banned.getReason());
        nameLabel.setText(banned.getUserName());
    }
    @FXML
    void handleUnbanButton(javafx.event.ActionEvent actionEvent) {
        selectedAccount.setBan(false);
        accountListDataSource.writeData(accountList);
        bannedList.removeBannedUser(selectedUser.getUserName());
        bannedUserListFileDataSource.writeData(bannedList);
        showListView();
        clearSelectedUser();
    }
    @FXML
    void handleBackButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("report_user_page");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า admin ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
