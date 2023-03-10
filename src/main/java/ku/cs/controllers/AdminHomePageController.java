package ku.cs.controllers;

import com.github.saacsos.FXRouter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import ku.cs.models.Mode;
import ku.cs.models.accounts.Account;
import ku.cs.models.accounts.AccountList;
import ku.cs.models.issues.UserIssueList;
import ku.cs.services.AccountListDataSource;
import ku.cs.services.DataSource;
import ku.cs.services.UserListIssueDataSource;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;


public class AdminHomePageController {

    private DataSource<AccountList> userListDataSource;
    private Account account;
    private AccountList userList;
    private UserListIssueDataSource userListIssueDataSource;
    private UserIssueList userIssueList;


    @FXML private Label adminNameLabel;
    @FXML private Label nameLabel;
    @FXML private ListView listViewUser;
    @FXML private ImageView imageView;
    @FXML private AnchorPane pane1;

    @FXML
    public void initialize() {
        userListDataSource = new AccountListDataSource("assets", "accounts.csv");
        userList = userListDataSource.readData();
        userListIssueDataSource = new UserListIssueDataSource("assets","userIssues.csv");
        userIssueList = userListIssueDataSource.readData();
        account = userList.findUser((String) FXRouter.getData());
        adminNameLabel.setText(account.getUsername());
        Mode.setMode(pane1);
        showListView();
        clearSelectedAccount();
        handleSelectedListView();
    }
    private void showListView(){
        ArrayList<Account> users = userList.getAllUsers();
        users.sort(new Comparator<Account>() {
            @Override
            public int compare(Account o1, Account o2) {
                if (o1.getLastLogin().equals("never")){
                    return 1;
                }
                if (o2.getLastLogin().equals("never"))return -1;
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dt1 = LocalDateTime.parse(o1.getLastLogin(),dtf);
                LocalDateTime dt2 = LocalDateTime.parse(o2.getLastLogin(),dtf);
                return -dt1.compareTo(dt2);
            }
        });
        listViewUser.getItems().addAll(users);
       listViewUser.refresh();
    }

    private void clearSelectedAccount(){
        nameLabel.setText("");
    }

    private void handleSelectedListView(){
        listViewUser.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Account>() {
            @Override
            public void changed(ObservableValue<? extends Account> observable, Account oldValue, Account newValue) {
                imageView.setImage(new Image(new File("imagesAvatar/" + account.getPicPath()).toURI().toString()));
                showSelectedAccount(newValue);
            }
        });
    }

    public void showSelectedAccount(Account account){
        nameLabel.setText(account.getName());
        imageView.setImage(new Image(new File("imagesAvatar/" + account.getPicPath()).toURI().toString()));
    }



    @FXML void handleGoToChangePasswordButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("change_password_page",account.getUsername());
        } catch (IOException e) {
            System.err.println("??????????????????????????? change_password ??????????????????");
            System.err.println("?????????????????????????????????????????????????????? route");
        }
    }

    @FXML void handleGoToReportUserButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("report_user_page",account.getUsername());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML void handleGoToReportComplainButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("report_complaint_page",account.getUsername());
        } catch (IOException e) {
            System.err.println("??????????????????????????? report_complain ??????????????????");
            System.err.println("?????????????????????????????????????????????????????? route");
        }
    }
    @FXML
    void handleLogoutButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("login_page");
        } catch (IOException e) {
            System.err.println("??????????????????????????? login ??????????????????");
            System.err.println("?????????????????????????????????????????????????????? route");
        }
    }
    @FXML
    void handleAddStaffButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("register_staff_page");
        } catch (IOException e) {
            System.err.println("??????????????????????????? Register_staff ??????????????????");
            System.err.println("?????????????????????????????????????????????????????? route");
        }
    }

}
