package ku.cs.controllers;

import com.github.saacsos.FXRouter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import ku.cs.models.Mode;
import ku.cs.models.account.Account;
import ku.cs.models.account.AccountList;
import ku.cs.models.account.StaffAccount;
import ku.cs.models.reports.Report;
import ku.cs.models.reports.ReportList;
import ku.cs.services.AccountListDataSource;
import ku.cs.services.DataSource;
import ku.cs.services.Filterer;
import ku.cs.services.ReportFIleDataSource;

import javafx.scene.control.*;

import java.io.IOException;

public class StaffHomepageController {

    private DataSource<ReportList> dataSource;
    private ReportList reportList;
    private StaffAccount staff;
    private AccountListDataSource userListDataSource;
    private AccountList userList;
    @FXML private AnchorPane pane;



    public void initialize(){
        dataSource = new ReportFIleDataSource("assets","reports.csv");
        reportList = dataSource.readData();
        userListDataSource = new AccountListDataSource("assets", "accounts.csv");
        userList = userListDataSource.readData();

        staff = (StaffAccount) userList.findUser((String) FXRouter.getData());
        Mode.setMode(pane);
    }


    private String[] agency = {"กองยานพาหนะ", "อาคารและสถานที่" , "สำนักบริการคอมพิวเตอร์", "กองกิจการนิสิต", "สำนักการกีฬา", "สำนักงานทรัพย์สิน"};


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
    void handleChangeAccountButton(javafx.event.ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("change_password",staff.getUsername());
        } catch (IOException e) {
            System.err.println("ไปที่หน้า change_password ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    void handleManageButton(javafx.event.ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("manage_reports",staff);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("ไปที่หน้า manage_reports ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

}
