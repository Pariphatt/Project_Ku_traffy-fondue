package ku.cs.controllers;

import com.github.saacsos.FXRouter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ku.cs.models.account.AccountList;
import ku.cs.models.account.StaffAccount;
import ku.cs.models.reports.Report;
import ku.cs.models.reports.ReportList;
import ku.cs.services.AccountListDataSource;
import ku.cs.services.DataSource;
import ku.cs.services.Filterer;
import ku.cs.services.ReportFIleDataSource;

import java.io.IOException;

public class ManageReportsController {

    @FXML private TextField searchReportTextField;
    @FXML private ListView reportListView;
    @FXML private Label topicLabel;
    @FXML private Label statusLabel;
    @FXML private TextArea detailTextArea;
    @FXML private TextField solutionTextField;
    @FXML private TextArea solutionTextArea;

    private DataSource<ReportList> dataSource;
    private ReportList reportList;

    private Report selectedReport;
    private StaffAccount staff;
    private AccountListDataSource userListDataSource;
    private AccountList userList;

    public void initialize(){
        dataSource = new ReportFIleDataSource("assets","reports.csv");
        reportList = dataSource.readData();
        showListView();
        userListDataSource = new AccountListDataSource("assets", "accounts.csv");
        userList = userListDataSource.readData();
        staff = (StaffAccount) userList.findUser((String) FXRouter.getData());
        detailTextArea.setDisable(true);

        //showChoiceBox();
        clearSelectedReport();
        handleSelectedListView();
    }

    //    private void showChoiceBox(){
//        Collection<String>
//        agencyChoiceBox.getItems().addAll()
//    }


    private void showListView(){
        ReportList reportListFiltered = reportList.filter(new Filterer<Report>() {
            @Override
            public boolean filter(Report report) {

                return report.getCategory().equals(staff.getCategory());
            }
        });
        reportListView.getItems().addAll(reportListFiltered.getaAllReport());
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
                selectedReport = newValue;
            }
        });
    }
    public void showSelectedReport(Report report){
        topicLabel.setText(report.getTopic());
        detailTextArea.setText(report.getDetail());
        statusLabel.setText(report.getStatus());
        solutionTextArea.setText(report.getSolution());
    }

    private String[] agency = {"กองยานพาหนะ", "อาคารและสถานท" +
            "" +
            "ี่", "สำนักบริการคอมพิวเตอร์", "กองกิจการนิสิต", "สำนักการกีฬา", "สำนักงานทรัพย์สิน"};


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
            com.github.saacsos.FXRouter.goTo("my_account");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า admin ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    void handleBackButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("staff_homepage");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า admin ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    @FXML
    void handleProcessButton(ActionEvent actionEvent) {
        String process = "กำลังดำเนินก่าร";
        selectedReport.setStatus(process);
        reportListView.refresh();
        showSelectedReport(selectedReport);
        dataSource.writeData(reportList);
    }

    @FXML
    void handleCompleteButton(ActionEvent actionEvent) {
        String complete = "เสร็จสิ้น";
        selectedReport.setStatus(complete);
        reportListView.refresh();
        showSelectedReport(selectedReport);
        dataSource.writeData(reportList);
    }

    @FXML
    void handleSubmitButton(ActionEvent actionEvent) {
        selectedReport.setSolution(solutionTextField.getText());
        dataSource.writeData(reportList);
        solutionTextField.clear();
        showSelectedReport(selectedReport);
    }
}
