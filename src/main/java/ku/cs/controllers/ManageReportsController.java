package ku.cs.controllers;

import com.github.saacsos.FXRouter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import ku.cs.models.Mode;
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

    @FXML private Button completeButton;
    @FXML private ListView reportListView;
    @FXML private Label topicLabel;
    @FXML private Label statusLabel;
    @FXML private TextArea detailTextArea;
    @FXML private TextField solutionTextField;
    @FXML private TextArea solutionTextArea;
    @FXML private Label staffLabel;

    @FXML private Label specificTopicLabel;

    private DataSource<ReportList> dataSource;
    private ReportList reportList;

    private Report selectedReport;
    private AccountListDataSource userListDataSource;
    private AccountList userList;
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
        clearSelectedReport();
        handleSelectedListView();
        Mode.setMode(pane);
        Alert alert = new Alert(Alert.AlertType.WARNING);
    }
    private void showListView(){
        ReportList reportListFiltered = reportList.filter(new Filterer<Report>() {
            @Override
            public boolean filter(Report report) {
                return report.goToManage(report,staff);
            }
        });
        reportListView.getItems().addAll(reportListFiltered.getaAllReport());
        reportListView.refresh();
    }
    private void clearSelectedReport(){
        statusLabel.setText("");
        detailTextArea.setText("");
        topicLabel.setText("");
        solutionTextArea.setText("");
        staffLabel.setText("");
        specificTopicLabel.setText("");
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
    public void showSelectedReport(Report report) {
        topicLabel.setText(report.getTopic());
        detailTextArea.setText(report.getDetail());
        statusLabel.setText(report.getStatus());
        solutionTextArea.setText(report.getSolution());
        staffLabel.setText(report.getStaffReport());
        specificTopicLabel.setText(report.getSpecificTopic());
        if(report.getStatus().equals("ยังไม่ดำเนินการ")){
            submitButton.setVisible(true);
            completeButton.setVisible(true);
            solutionTextField.setEditable(true);
        } else if(report.getStatus().equals("กำลังดำเนินการ")){
            submitButton.setVisible(false);
            solutionTextField.setEditable(false);
            completeButton.setVisible(true);
        } else {
            solutionTextField.setEditable(false);
            submitButton.setVisible(false);
            completeButton.setVisible(false);
        }
    }
    public void handleRefresh(){
        reportListView.refresh();
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
        if (input.equals("")){
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
        checkProcessComplete();
    }

    private void checkProcessComplete(){
        if (selectedReport == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("โปรดเลือกเรื่องร้องเรียนก่อน");
            alert.show();
        }
        else if (!selectedReport.getStatus().equals("กำลังดำเนินการ")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ยังไม่ได้ดำเนินการเรื่องร้องเรียน");
            alert.setContentText("โปรดดำเนินการเรื่องร้องเรียนก่อน");
            alert.show();
            selectedReport.getStatus().equals("ยังไม่ดำเนินการ");
            completeButton.setVisible(true);
            solutionTextField.setEditable(true);
        }else {
            String complete = "เสร็จสิ้น";
            selectedReport.setStatus(complete);
            reportListView.refresh();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("เรื่องร้องเรียน");
            alert.setContentText("จัดการเรื่องร้องเรียนเสร็จสิ้น");
            alert.show();
            solutionTextField.setEditable(false);
            completeButton.setVisible(false);
            showSelectedReport(selectedReport);
        }
    }
    @FXML
    void handleSubmitButton(ActionEvent actionEvent) {
        checkSolutionSubmit();
    }

    private void checkSolutionSubmit(){
        if(solutionTextField.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ข้อมูลไม่ครบถ้วน");
            alert.setContentText("โปรดกรอกข้อมูลในช่อง Solution");
            alert.show();
            selectedReport.getStatus().equals("ยังไม่ดำเนินการ");
            solutionTextField.setEditable(true);
            submitButton.setVisible(true);

        } else {
            String submit = "กำลังดำเนินการ";
            selectedReport.setStatus(submit);
            selectedReport.setSolution(solutionTextField.getText());
            selectedReport.setStaffReport(staff.getName());
            dataSource.writeData(reportList);
            solutionTextField.clear();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Solution");
            alert.setContentText("กำลังดำเนินการเรื่องร้องเรียน");
            alert.show();
            submitButton.setVisible(false);
            solutionTextField.setEditable(false);
            showSelectedReport(selectedReport);
        }
    }
    @FXML
    void handleRefreshButton(ActionEvent actionEvent) {
        searchReportTextField.clear();
        reportListView.getItems().clear();
        handleRefresh();
        reportList = dataSource.readData();
        showListView();
    }
}
