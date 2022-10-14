package ku.cs.controllers;

import com.github.saacsos.FXRouter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.models.account.Account;
import ku.cs.models.account.AccountList;
import ku.cs.models.account.UserAccount;
import ku.cs.models.issue.UserIssue;
import ku.cs.models.issue.UserListIssue;
import ku.cs.models.reports.Report;
import ku.cs.models.reports.ReportList;
import ku.cs.services.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;

public class WelcomePageController {
    @FXML private ImageView userShow;
    @FXML private Label userLabel;
    @FXML private Label topicLabel;
    @FXML private Label typeLabel;
    @FXML private Label agencyLabel;
    @FXML private Label statusLabel;
    @FXML private Label voteLabel;
    @FXML private TextArea detailTextArea;
    @FXML private ListView reportListView;
    @FXML private ChoiceBox typeChoiceBox;
    @FXML private ChoiceBox statusChoiceBox;
    @FXML private ChoiceBox sortByChoiceBox;
    @FXML private TextArea reasonsTextArea;
    @FXML private TextArea reasonsUserTextArea;
    private Account account;
    private AccountListDataSource userListDataSource;
    private AccountList userList;
    private DataSource<ReportList> dataSource;
    private ReportList reportList;
    private Report selectedReport;
    private UserAccount selectedAccount;
    private DataSource<AccountList> accountListDataSource;
    private Alert alert;
    private AccountList accountList;
    private UserListIssueDataSource userListIssueDataSource;
    private UserListIssue userListIssue;

    public void initialize(){
        File imagePic = new File("imagesAvatar/profile-user.png");
        userShow.setImage(new Image(imagePic.toURI().toString()));
        userListDataSource = new AccountListDataSource("assets", "accounts.csv");
        userList = userListDataSource.readData();
        account = userList.findUser((String) FXRouter.getData());
        alert = new Alert(Alert.AlertType.NONE);
        userShow.setImage(new Image(new File("imagesAvatar/" + account.getPicPath()).toURI().toString()));
        userLabel.setText(account.getUsername());

        dataSource = new ReportFIleDataSource("assets","reports.csv");
        reportList = dataSource.readData();


        userListIssueDataSource = new UserListIssueDataSource("assets","userIssues.csv");
        userListIssue = userListIssueDataSource.readData();
        accountList = userListDataSource.readData();

        showListView();
        clearSelectedReport();
        handleSelectedListView();
        sortListView();

        showStatusChoiceBox();
        showTypeChoiceBox();


        detailTextArea.setDisable(true);
    }
    private void sortListView(){
        ArrayList<Report> reports = reportList.getAllReport();
        reports.sort(new Comparator<Report>() {
            @Override
            public int compare(Report o1, Report o2) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dt1 = LocalDateTime.parse(o1.getReportTime(),dtf);
                LocalDateTime dt2 = LocalDateTime.parse(o2.getReportTime(),dtf);
                return -dt1.compareTo(dt2);
            }
        });
        reportListView.getItems().clear();
        reportListView.getItems().addAll(reports);
        reportListView.refresh();
    }

    private void showListView(){
        reportListView.getItems().clear();
        reportListView.getItems().addAll(reportList.getAllReport());
        reportListView.refresh();
    }

    private void clearSelectedReport(){
        statusLabel.setText("");
        detailTextArea.setText("");
        topicLabel.setText("");
        voteLabel.setText("");
        agencyLabel.setText("");
        typeLabel.setText("");
    }

    public void showSelectedReport(Report report){
        topicLabel.setText(report.getTopic());
        detailTextArea.setText(report.getDetail());
        statusLabel.setText(report.getStatus());
        voteLabel.setText(report.getVote());
        agencyLabel.setText(report.getAgency());
        typeLabel.setText(report.getType());
    }

    public void showStatusChoiceBox(){
        ArrayList<String> status = new ArrayList<>();
        status.add("ทั้งหมด");
        status.add("ยังไม่ดำเนินการ");
        status.add("กำลังดำเนินการ");
        status.add("เสร็จสิ้น");
        statusChoiceBox.getItems().addAll(status);
        statusChoiceBox.setOnAction(this::handleSearchStatusChoiceBox);
    }

    private void handleSearchStatusChoiceBox(Event event) {
        String status = (String) statusChoiceBox.getValue();
        dataSource = new ReportFIleDataSource();
        reportList = dataSource.readData();
        reportList = reportList.findStatus(status);
        showListView();
    }

    public void showTypeChoiceBox(){
        ArrayList<String> type = new ArrayList<>();
        type.add("ยานพาหนะ");
        type.add("อาคารสถานที่และความปลอดภัย");
        type.add("IT หรือ ปัญหาด้านคอมพิวเตอร์");
        type.add("กิจกรรมนิสิต");
        type.add("ทรัพย์สินในมหาวิทยาลัย");
        type.add("อื่นๆ");
        typeChoiceBox.getItems().addAll(type);
        typeChoiceBox.setOnAction(this::handleSearchTypeChoiceBox);
    }

    private void handleSearchTypeChoiceBox(Event event) {
        String type = (String) typeChoiceBox.getValue();
        dataSource = new ReportFIleDataSource();
        reportList = dataSource.readData();
        reportList = reportList.findTypes(type);
        showListView();
    }

    private void handleSelectedListView(){
        reportListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Report>() {
            @Override
            public void changed(ObservableValue<? extends Report> observable, Report oldValue, Report newValue) {
                System.out.println("Selected item: " + newValue);
                showSelectedReport(newValue);
                selectedReport = newValue;
                System.out.println(newValue.getUserReport());
                selectedAccount = (UserAccount) accountList.findUser(newValue.getUserReport());
            }
        });
    }

    @FXML
    public void handleHomeButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("welcome_page", account.getUsername());
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    @FXML
    public void handleAddReportButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("addReport_page", account.getUsername());
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    @FXML
    public void handleMyReportButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("allComplaint_page", account.getUsername());
        } catch (IOException e) {
            throw new RuntimeException();
            //System.err.println(e);
        }
    }

    @FXML
    public void handleLogOutButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("login");
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    @FXML
    public void handleSettingButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("change_password", account.getUsername());
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    @FXML
    public void handleImpoliticButton(ActionEvent actionEvent){
            String reasons = reasonsTextArea.getText();
           if (selectedReport == null){
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setContentText("โปรดเลือกเรื่องในการร้องเรียน");
            alert.show();
            }
            else if (reasons.isEmpty()){
                alert.setAlertType(Alert.AlertType.WARNING);
                alert.setContentText("โปรดกรอกเหตุผลในการร้องเรียน");
                alert.show();
            }
            else {
                ReportFIleDataSource reportPost = new ReportFIleDataSource("assets","report_post.csv");
                reportPost.reportPost(selectedReport,reasons);
               reasonsTextArea.clear();
               alert.setAlertType(Alert.AlertType.INFORMATION);
               alert.setContentText("ส่งการรายงานสำเร็จ");
               alert.show();

            }
    }

    @FXML
    public void handleReportUserButton(ActionEvent actionEvent) {
        String reasons = reasonsUserTextArea.getText();
        if (selectedReport == null) {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setContentText("โปรดเลือกเรื่องในการร้องเรียน");
            alert.show();
        } else if (reasons.isEmpty()) {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setContentText("โปรดกรอกเหตุผลในการร้องเรียน");
            alert.show();
        } else {
            UserListIssueDataSource reportUser = new UserListIssueDataSource();
            userListIssue.addUserIssue(new UserIssue(selectedReport.getUserReport(), 0,false,reasons));
            reportUser.writeData(userListIssue);
            reasonsTextArea.clear();
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("ส่งการรายงานสำเร็จ");
            alert.show();
            reasonsUserTextArea.clear();
            System.out.println("090909090");
        }
    }

}
