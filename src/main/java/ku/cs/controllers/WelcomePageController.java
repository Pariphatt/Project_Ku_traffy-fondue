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
import ku.cs.models.reports.Vote;
import ku.cs.models.reports.VoteList;
import ku.cs.services.AccountListDataSource;
import ku.cs.services.DataSource;
import ku.cs.services.ReportFIleDataSource;
import ku.cs.services.VoteDataSource;
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
    @FXML private Label dateLabel;
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

    private Vote selectedVote;
    private VoteList voteList;
    private DataSource<VoteList> voteListDataSource;

    private ReportList filterReportList;

    private ArrayList<Report> sortReportList;

    @FXML private TextField maxTextField;

    @FXML private TextField minTextField;

    public void initialize(){
        File imagePic = new File("imagesAvatar/profile-user.png");
        userShow.setImage(new Image(imagePic.toURI().toString()));
        userListDataSource = new AccountListDataSource("assets", "accounts.csv");
        userList = userListDataSource.readData();
        account = userList.findUser((String) FXRouter.getData());
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
        showSortByChoiceBox();

        detailTextArea.setDisable(true);

        voteListDataSource = new VoteDataSource("assets","votes.csv");
        voteList = voteListDataSource.readData();
        alert = new Alert(Alert.AlertType.NONE);
    }
    private void sortListView(){
        ArrayList<Report> reports = reportList.getaAllReport();
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
        reportListView.getItems().addAll(reportList.getaAllReport());
        reportListView.refresh();
    }

    private void clearSelectedReport(){
        statusLabel.setText("");
        detailTextArea.setText("");
        topicLabel.setText("");
        voteLabel.setText("");
        agencyLabel.setText("");
        typeLabel.setText("");
        dateLabel.setText("");
    }

    public void showSelectedReport(Report report){
        topicLabel.setText(report.getTopic());
        detailTextArea.setText(report.getDetail());
        statusLabel.setText(report.getStatus());
        voteLabel.setText(String.valueOf(report.getVote()));
        agencyLabel.setText(report.getAgency());
        typeLabel.setText(report.getType());
        dateLabel.setText(report.getReportTime());
    }

    public void showStatusChoiceBox(){
        ArrayList<String> status = new ArrayList<>();
        status.add("ทั้งหมด");
        status.add("ยังไม่ดำเนินการ");
        status.add("กำลังดำเนินการ");
        status.add("เสร็จสิ้น");
        statusChoiceBox.getItems().addAll(status);
        statusChoiceBox.getSelectionModel().selectFirst();
        statusChoiceBox.setOnAction(this::handleSearchStatusChoiceBox);
    }

    private void handleSearchStatusChoiceBox(Event event) {
        handleListView();
    }

    public void showTypeChoiceBox(){
        ArrayList<String> type = new ArrayList<>();
        type.add("หน่วยงานทั้งหมด");
        type.add("ยานพาหนะ");
        type.add("อาคารสถานที่และความปลอดภัย");
        type.add("IT หรือ ปัญหาด้านคอมพิวเตอร์");
        type.add("กิจกรรมนิสิต");
        type.add("ทรัพย์สินในมหาวิทยาลัย");
        type.add("อื่นๆ");
        typeChoiceBox.getItems().addAll(type);
        typeChoiceBox.getSelectionModel().selectFirst();
        typeChoiceBox.setOnAction(this::handleSearchTypeChoiceBox);
    }

    private void handleSearchTypeChoiceBox(Event event) {
        handleListView();
    }
    public void showSortByChoiceBox(){
        ArrayList<String> sortBys = new ArrayList<>();
        sortBys.add("เวลาที่เเจ้งล่าสุด");
        sortBys.add("เวลาที่เเจ้งเก่าสุด");
        sortBys.add("คะเเนนโหวตมากที่สุด");
        sortBys.add("คะเเนนโหวตน้อยที่สุด");

        sortByChoiceBox.getItems().addAll(sortBys);
        sortByChoiceBox.getSelectionModel().selectFirst();
        sortByChoiceBox.setOnAction(this::handleSearchSortBYChoiceBox);
    }
    private void handleSearchSortBYChoiceBox(Event event){
        handleListView();

    }
    private void handleListView(){
        filterReportList = reportList.filter(new Filterer<Report>() {
            @Override
            public boolean filter(Report report) {
                if (statusChoiceBox.getValue().equals("ทั้งหมด"))
                    return true;
                return statusChoiceBox.getValue().equals(report.getStatus());
            }
        });
        filterReportList = filterReportList.filter(new Filterer<Report>() {
            @Override
            public boolean filter(Report report) {
                if (typeChoiceBox.getValue().equals("หน่วยงานทั้งหมด"))
                    return true;
                return typeChoiceBox.getValue().equals(report.getType());
            }
        });


        if(!(maxTextField.getText().isEmpty())) {
            filterReportList = filterReportList.filter(new Filterer<Report>() {
                @Override
                public boolean filter(Report report) {
                    int min = Integer.parseInt(minTextField.getText());
                    int max = Integer.parseInt(maxTextField.getText());
                    return report.getVote() >= min && report.getVote() <= max;
                }

            });

        }

        ArrayList<Report> sortReportList = filterReportList.getaAllReport();

        if(sortByChoiceBox.getValue().equals("คะเเนนโหวตน้อยที่สุด")) {
            sortReportList.sort(new Comparator<Report>() {
                @Override
                public int compare(Report o1, Report o2) {

                    return Integer.compare(o1.getVote(), o2.getVote());
                }
            });
        }
        if(sortByChoiceBox.getValue().equals("คะเเนนโหวตมากที่สุด")) {
            sortReportList.sort(new Comparator<Report>() {
                @Override
                public int compare(Report o1, Report o2) {
                    return -Integer.compare(o1.getVote(), o2.getVote());

                }
            });
        }

        if(sortByChoiceBox.getValue().equals("เวลาที่เเจ้งล่าสุด")) {
            sortReportList.sort(new Comparator<Report>() {
                @Override
                public int compare(Report o1, Report o2) {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    LocalDateTime dt1 = LocalDateTime.parse(o1.getReportTime(), dtf);
                    LocalDateTime dt2 = LocalDateTime.parse(o2.getReportTime(), dtf);
                    return -dt1.compareTo(dt2);
                }

            });
        }
        if(sortByChoiceBox.getValue().equals("เวลาที่เเจ้งเก่าสุด")) {
            sortReportList.sort(new Comparator<Report>() {
                @Override
                public int compare(Report o1, Report o2) {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    LocalDateTime dt1 = LocalDateTime.parse(o1.getReportTime(), dtf);
                    LocalDateTime dt2 = LocalDateTime.parse(o2.getReportTime(), dtf);
                    return dt1.compareTo(dt2);

                }
            });

        }

        reportListView.getItems().clear();
        reportListView.getItems().addAll(sortReportList);
        reportListView.refresh();
    }

    @FXML
    private void handleSearchVoteButton() {
        handleListView();
    }

    private void handleSelectedListView(){
        reportListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Report>() {
            @Override
            public void changed(ObservableValue<? extends Report> observable, Report oldValue, Report newValue) {
                System.out.println("Selected item: " + newValue);
                if(newValue != null){
                    showSelectedReport(newValue);
                    selectedReport = newValue;
                }
            }
        });
    }

    public void handleVoteButton(ActionEvent actionEvent){
        if (voteList.isExistEverVote(selectedReport.getTopic()+account.getUsername())
                || (selectedReport.getTopic()+account.getUsername() == null)){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("คุณโหวตไปแล้ว");
            alert.show();
        } else {
            selectedReport.plusVote();
            reportListView.refresh();
            showSelectedReport(selectedReport);
            dataSource.writeData(reportList);
            Vote vote = new Vote(selectedReport.getTopic() + account.getUsername());
            voteList.addVote(vote);
            voteListDataSource.writeData(voteList);
        }
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
            reasonsUserTextArea.clear();
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("ส่งการรายงานสำเร็จ");
            alert.show();
            reasonsUserTextArea.clear();
            System.out.println("090909090");
        }
    }

}
