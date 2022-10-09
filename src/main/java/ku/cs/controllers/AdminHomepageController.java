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
import ku.cs.models.account.Account;
import ku.cs.models.account.AccountList;
import ku.cs.models.account.StaffAccount;
import ku.cs.services.AccountListDataSource;
import ku.cs.services.DataSource;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;


public class AdminHomepageController {

    private DataSource<AccountList> dataSource;
    private DataSource<AccountList> userListDataSource;
    private Account account;
    private AccountList userList;
    private AccountList accountsList;

    @FXML StackPane pane;

    @FXML private Label adminNameLabel;
    @FXML private Label nameLabel;
    @FXML private Label UsernameLabel;
    @FXML private Label agencyLabel;
    @FXML private Label timeLabel;
    @FXML private ListView listViewUser;
    @FXML private ImageView imageView;


    @FXML
    public void initialize() {
//        dataSource = new AccountListDataSource( "assets","log.csv");
//        accountsList = dataSource.readData();
        userListDataSource = new AccountListDataSource("assets", "accounts.csv");
        userList = userListDataSource.readData();
        account = userList.findUser((String) FXRouter.getData());
        adminNameLabel.setText(account.getUsername());
        //accountsList.sortDateAccount();
        showListView();
        clearSelectedAccount();
        handleSelectedListView();
    }
    private void showListView(){
        //เรียง จากเวลา
//        Collection.sort(sortaccountsList.getAllUsers() , new Comparator<Account>()){
//            @Override
//            public int compare(account){
//                return -(account.getLastLogin().compareTo(account.getLastLogin()));
//            }
//        };
        ArrayList<Account> users = userList.getAllUsers();
        Collections.sort(users ,new Comparator<Account>() {

            @Override
            public int compare(Account o1, Account o2) {
                if (o1.getLastLogin().equals("never")){
                    return 1;
                }
                if (o2.getLastLogin().equals("never"))return -1;
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dt1 = LocalDateTime.parse(o1.getLastLogin(),dtf);
                LocalDateTime dt2 = LocalDateTime.parse(o1.getLastLogin(),dtf);
                return -dt1.compareTo(dt2);
            }
        });
        listViewUser.getItems().addAll(users);
       listViewUser.refresh();
    }

    private void clearSelectedAccount(){
        nameLabel.setText("");
//        UsernameLabel.setText("");
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
//        UsernameLabel.setText(account.getUsername());
//        timeLabel.setText(account.getLastLogin());

    }



    @FXML void handleGoToChangePasswordButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("change_password",account.getUsername());
        } catch (IOException e) {
            System.err.println("ไปที่หน้า change_password ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML void handleGoToReportUserButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("report_user");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า report_user ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    @FXML
    void handleLogoutButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("login");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    @FXML
    void handleAddStaffButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("RegisterStaff");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า Register_staff ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

}
