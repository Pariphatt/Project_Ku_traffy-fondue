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
import ku.cs.models.reports.Report;
import ku.cs.models.reports.ReportList;
import ku.cs.services.AccountListDataSource;
import ku.cs.services.DataSource;
import ku.cs.services.Filterer;
import ku.cs.services.ReportFIleDataSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AllComplaintController {
    @FXML private Label topicLabel;
    @FXML private Label statusLabel;
    @FXML private Label voteLabel;

    @FXML private Label agencyLabel;

    @FXML private Label dateLabel;
    @FXML private TextField searchReportTextField;
    @FXML private TextArea detailTextArea;
    @FXML private ListView reportListView;

    @FXML private ChoiceBox typeChoiceBox;

    @FXML private ChoiceBox agencyChoiceBox;
    @FXML private ChoiceBox statusChoiceBox;
    @FXML private ChoiceBox categoryChoiceBox;
    @FXML private ChoiceBox sortByChoiceBox;


    private DataSource<ReportList> dataSource;
    private ReportList reportList;

    private int vote;
    private String user;

    @FXML private ImageView userShow;
    private Account account;
    private AccountListDataSource userListDataSource;
    private AccountList userList;
    private AccountList accountList;

    private Report selectedReport;
    private DataSource<AccountList> accountListDataSource;

    public void initialize(){
        dataSource = new ReportFIleDataSource("assets","reports.csv");
        reportList = dataSource.readData();
//        typeChoiceBox.getItems().addAll(type);
        showListView();
        clearSelectedReport();
        handleSelectedListView();

        detailTextArea.setDisable(true);

        accountListDataSource = new AccountListDataSource("assets","accountsVote.csv");
        accountList = accountListDataSource.readData();

        File imagePic = new File("imagesAvatar/profile-user.png");
        userShow.setImage(new Image(imagePic.toURI().toString()));
        userListDataSource = new AccountListDataSource("assets", "accounts.csv");
        userList = userListDataSource.readData();
        account = userList.findUser((String) FXRouter.getData());
        userShow.setImage(new Image(new File("imagesAvatar/" + account.getPicPath()).toURI().toString()));

        showStatusChoiceBox();

    }

    private String[] type =
            {"ยานพาหนะ", "อาคารสถานที่และความปลอดภัย" , "IT หรือ ปัญหาด้านคอมพิวเตอร์", "กิจกรรมนิสิต", "ทรัพย์สินในมหาวิทยาลัย", "อื่นๆ"};

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

        dateLabel.setText("");
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

    public void showSelectedReport(Report report){
        topicLabel.setText(report.getTopic());
        detailTextArea.setText(report.getDetail());
        statusLabel.setText(report.getStatus());
        voteLabel.setText(report.getVote());
//        dateLabel.setText(report.getPicPath());
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
    public void handleVoteButton(ActionEvent actionEvent){
        accountListDataSource = new AccountListDataSource("assets","accountsVote.csv");
        user = account.getUsername();
        if (accountList.isExistUsername(user)){
            vote -= 1;
        } else {
            vote += 1;
        }
        selectedReport.setVote(vote);
        reportListView.refresh();
        showSelectedReport(selectedReport);
        dataSource.writeData(reportList);

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
    public void handleAllComplaintButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("allComplaint_page", account.getUsername());
        } catch (IOException e) {
            System.err.println(e);
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
            com.github.saacsos.FXRouter.goTo("change_password",account.getUsername());
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
