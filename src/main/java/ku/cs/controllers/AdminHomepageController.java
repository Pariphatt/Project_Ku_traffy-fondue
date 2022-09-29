package ku.cs.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import ku.cs.models.Register;
import ku.cs.models.User;
import ku.cs.models.account.AccountList;
import ku.cs.services.Info;
import ku.cs.services.ListDataSource;

import java.io.IOException;


public class AdminHomepageController {

    private Info info;

    private AccountList accountsList;

    @FXML StackPane pane;
    @FXML private ListDataSource<Register> dataSource;
    @FXML private Label adminNameLabel;
    @FXML private Label NameLabel;
    @FXML private Label UsernameLabel;
    @FXML private Label AgencyLabel;
    @FXML private Label TimeLabel;
    @FXML private ListView listViewUser;
    private Register register;
    @FXML
    public void initialize() {
        register = new Register();
//        register = dataSource.readData();
//        listViewUser.getItems().addAll(register.getAccount());
        handleSelectedListView();
    }

    private void showListView() {
        listViewUser.getItems().addAll(register.getAccount());
        listViewUser.refresh();
}

    private void handleSelectedListView() {
        listViewUser.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<User>() {
                    @Override
                    public void changed(ObservableValue<? extends User> observable,
                                        User oldValue, User newValue) {
                        showSelectedUser(newValue);
                    }
                });
    }

    private void showSelectedUser(User user) {
        NameLabel.setText(user.getName());
        UsernameLabel.setText(user.getUsername());
        AgencyLabel.setText(user.getAgency());
    }

    private void clearSelectedCard() {
        NameLabel.setText("");
        UsernameLabel.setText("");
        AgencyLabel.setText("");
    }
    @FXML void handleGoToChangePasswordButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("change_password");
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
