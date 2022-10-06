package ku.cs.controllers;

import com.github.saacsos.FXRouter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import ku.cs.models.account.Account;
import ku.cs.models.account.AccountList;
import ku.cs.models.reports.Report;
import ku.cs.models.reports.ReportList;
import ku.cs.services.AccountListDataSource;
import ku.cs.services.DataSource;
import ku.cs.services.Filterer;
import ku.cs.services.ReportFIleDataSource;

import javafx.scene.control.*;

import java.io.IOException;

public class StaffHomepageController {
    @FXML private TextField searchReportTextField;
    @FXML private ListView reportListView;
    @FXML private ChoiceBox agencyChoiceBox;
    @FXML private Label topicLabel;
    @FXML private Label statusLabel;
    @FXML private TextArea detailTextArea;

    private DataSource<ReportList> dataSource;
    private ReportList reportList;
    private DataSource<AccountList> userListDataSource;
    private AccountList accountList;
    private Account account;

    public void initialize(){
        dataSource = new ReportFIleDataSource("assets","reports.csv");
        reportList = dataSource.readData();
        showListView();
        //showChoiceBox();
        clearSelectedReport();
        handleSelectedListView();
        agencyChoiceBox.getItems().addAll(agency);

        userListDataSource = new AccountListDataSource("assets", "accounts.csv");
        accountList = userListDataSource.readData();
        account = accountList.findUser((String) FXRouter.getData());
    }

//    private void showChoiceBox(){
//        Collection<String>
//        agencyChoiceBox.getItems().addAll()
//    }
    private String[] agency = {"กองยานพาหนะ", "อาคารและสถานท" , "สำนักบริการคอมพิวเตอร์", "กองกิจการนิสิต", "สำนักการกีฬา", "สำนักงานทรัพย์สิน"};

    private void showListView(){
        reportListView.getItems().addAll(reportList.getaAllReport());
        reportListView.refresh();
    }
    private void clearSelectedReport(){
        statusLabel.setText("");
        detailTextArea.setText("");
        topicLabel.setText("");
    }
    private void handleSelectedListView(){
        reportListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Report>() {
            @Override
            public void changed(ObservableValue<? extends Report> observable, Report oldValue, Report newValue) {
                System.out.println("Selected item: " + newValue);
                showSelectedReport(newValue);
            }
        });
    }
    public void showSelectedReport(Report report){
        topicLabel.setText(report.getTopic());
        detailTextArea.setText(report.getDetail());
        statusLabel.setText(report.getStatus());
    }

     @FXML
     public void handleSearchReportButton(javafx.event.ActionEvent actionEvent){
        String input = searchReportTextField.getText();

        reportList = reportList.filter(new Filterer<Report>() {
            @Override
            public boolean filter(Report report) {
                return report.getTopic().contains(input);
            }
        });
        if (input == ""){
            reportList = dataSource.readData();
        }
        reportListView.getItems().clear();
        showListView();
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
    void handleChangeAccountButton(javafx.event.ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("change_password",account.getUsername());
        } catch (IOException e) {
            System.err.println("ไปที่หน้า change_password ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    void handleManageButton(javafx.event.ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("manage_reports");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า admin ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

}
