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
import ku.cs.services.ReportFIleDataSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
    private Account account;
    private AccountListDataSource userListDataSource;
    private AccountList userList;
    private DataSource<ReportList> dataSource;
    private ReportList reportList;
    private Report selectedReport;
    private DataSource<AccountList> accountListDataSource;
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

        showListView();
        clearSelectedReport();
        handleSelectedListView();

        showStatusChoiceBox();
        showTypeChoiceBox();

        detailTextArea.setDisable(true);
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
        reportList = reportList.findType(type);
        showListView();
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

}
