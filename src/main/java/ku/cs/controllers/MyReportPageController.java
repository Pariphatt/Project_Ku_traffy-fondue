package ku.cs.controllers;

import animatefx.animation.FadeIn;
import com.github.saacsos.FXRouter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import ku.cs.models.Mode;
import ku.cs.models.accounts.Account;
import ku.cs.models.accounts.AccountList;
import ku.cs.models.reports.Report;
import ku.cs.models.reports.ReportList;
import ku.cs.services.AccountListDataSource;
import ku.cs.services.DataSource;
import ku.cs.services.ReportFIleDataSource;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;

public class MyReportPageController {
    @FXML private Label userLabel;
    @FXML private ImageView userShow;
    @FXML private Label typeLabel;
    @FXML private Label agencyLabel;
    @FXML private Label dateLabel;
    @FXML private TextArea topicTextArea;
    @FXML private TextArea detailTextArea;
    @FXML private Label statusLabel;
    @FXML private Label voteLabel;
    @FXML private ListView reportListView;
    @FXML private TextArea solutionTextArea;
    private Account account;
    private AccountListDataSource userListDataSource;
    private AccountList userList;
    private ReportList reportList;
    private Report selectedReport;
    private DataSource<ReportList> dataSource;
    @FXML private AnchorPane pane;

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

        detailTextArea.setEditable(false);
        topicTextArea.setEditable(false);
        solutionTextArea.setEditable(false);

        sortListView();
        handleSelectedListView();
        showListView();
        clearSelectedReport();

        showMyReport();
        Mode.setMode(pane);
        new FadeIn(pane).play();
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

    private void showListView(){
        reportListView.getItems().clear();
        reportListView.getItems().addAll(reportList.getaAllReport());
        reportListView.refresh();
    }

    private void clearSelectedReport(){
        typeLabel.setText("");
        agencyLabel.setText("");
        topicTextArea.setText("");
        detailTextArea.setText("");
        statusLabel.setText("");
        voteLabel.setText("");
        dateLabel.setText("");
    }

    public void showSelectedReport(Report report){
        typeLabel.setText(report.getType());
        agencyLabel.setText(report.getAgency());
        topicTextArea.setText(report.getTopic());
        detailTextArea.setText(report.getDetail());
        statusLabel.setText(report.getStatus());
        voteLabel.setText(String.valueOf(report.getVote()));
        solutionTextArea.setText(report.getSolution());
        dateLabel.setText(report.getReportTime());
    }

    public void showMyReport(){
        String user = (String) account.getUsername();
        dataSource = new ReportFIleDataSource();
        reportList = dataSource.readData();
        reportList = reportList.findMyReport(user);
        showListView();
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
            com.github.saacsos.FXRouter.goTo("add_report_page", account.getUsername());
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    @FXML
    public void handleMyReportButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("my_report_page", account.getUsername());
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    @FXML
    public void handleLogOutButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("login_page");
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    @FXML
    public void handleSettingButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("change_password_page",account.getUsername());
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
