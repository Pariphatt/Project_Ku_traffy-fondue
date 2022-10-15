package ku.cs.controllers;

import com.github.saacsos.FXRouter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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

import java.io.IOException;

public class ManageReportsController {

    @FXML private TextField searchReportTextField;
    @FXML private Button submitButton;
    @FXML private ListView reportListView;
    @FXML private Label topicLabel;
    @FXML private Label statusLabel;
    @FXML private TextArea detailTextArea;
    @FXML private TextField solutionTextField;
    @FXML private TextArea solutionTextArea;

    private DataSource<ReportList> dataSource;
    private ReportList reportList;

    private Report selectedReport;
    private AccountListDataSource userListDataSource;
    private AccountList userList;
    private Account account;

    private StaffAccount staff = (StaffAccount)  FXRouter.getData();
    @FXML private AnchorPane pane;
    public void initialize(){
        dataSource = new ReportFIleDataSource("assets","reports.csv");
        reportList = dataSource.readData();
        showListView();
        userListDataSource = new AccountListDataSource("assets", "accounts.csv");
        userList = userListDataSource.readData();
        detailTextArea.setEditable(false);
        solutionTextArea.setEditable(false);
        //showChoiceBox();
        clearSelectedReport();
        handleSelectedListView();
        Mode.setMode(pane);

    }

//        private void showChoiceBox(){
//        Collection<String>
//        agencyChoiceBox.getItems().addAll()
//    }


    private void showListView(){
        ReportList reportListFiltered = reportList.filter(new Filterer<Report>() {
            @Override
            public boolean filter(Report report) {
                return report.goToManage(report,staff);
            }
        });
        reportListView.getItems().addAll(reportList.getaAllReport());
        reportListView.refresh();
    }
    //     @FXML
//     public void handleSearchReportButton(javafx.event.ActionEvent actionEvent){
//        String input = searchReportTextField.getText();
//
//        reportListFiltered = reportList.filter(new Filterer<Report>() {
//            @Override
//            public boolean filter(Report report) {
//                return report.getTopic().contains(input);
//            }
//        });
//        if (input == ""){
//            reportListFiltered = dataSource.readData();
//        }
//        reportListView.getItems().clear();
//        showListView(reportListFiltered);
//    }

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
        if (report.getSolution().isEmpty()) {
            submitButton.setVisible(true);
        } else {
            submitButton.setVisible(false);
        }
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
    void handleBackButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("staff_homepage");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า admin ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    void handleCompleteButton(ActionEvent actionEvent) {
        String complete = "เสร็จสิ้น";
        selectedReport.setStatus(complete);
        reportListView.refresh();
        showSelectedReport(selectedReport);
        dataSource.writeData(reportList);
//        solutionTextField.setDisable(true); //ไปทำ method check
//        setCompleteButton();
//        showSelectedReport(selectedReport);
    }
//    private void setCompleteButton(){completeButton.setVisible(false);}
    private void setSubmitButton(){
        submitButton.setVisible(false);
    }

    @FXML
    void handleSubmitButton(ActionEvent actionEvent) {
        String process = "กำลังดำเนินการ";
        selectedReport.setStatus(process);
        selectedReport.setSolution(solutionTextField.getText());
        selectedReport.setStaffReport(staff.getName());
        dataSource.writeData(reportList);
        solutionTextField.clear();
        solutionTextField.setDisable(true); //ไปทำ method check
        setSubmitButton();
        showSelectedReport(selectedReport);

    }
    @FXML
    void handleRefreshButton(ActionEvent actionEvent) {
        searchReportTextField.clear();
        reportListView.getItems().clear();
        dataSource = new ReportFIleDataSource("assets","reports.csv");
        reportList = dataSource.readData();
        showListView();

    }
}
