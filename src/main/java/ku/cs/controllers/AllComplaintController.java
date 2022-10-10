package ku.cs.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ku.cs.models.reports.Report;
import ku.cs.models.reports.ReportList;
import ku.cs.services.DataSource;
import ku.cs.services.Filterer;
import ku.cs.services.ReportFIleDataSource;

import java.io.IOException;
import java.util.ArrayList;

public class AllComplaintController {
    @FXML private Label topicLabel;
    @FXML private Label statusLabel;
    @FXML private Label voteLabel;
    @FXML private TextField searchReportTextField;
    @FXML private TextArea detailTextArea;
    @FXML private ListView reportListView;
    @FXML private ChoiceBox agencyChoiceBox;
    @FXML private ChoiceBox statusChoiceBox;
    @FXML private ChoiceBox categoryChoiceBox;
    @FXML private ChoiceBox sortByChoiceBox;

    private DataSource<ReportList> dataSource;
    private ReportList reportList;
    private int v;
    public void initialize(){
        dataSource = new ReportFIleDataSource("assets","reports.csv");
        reportList = dataSource.readData();
        agencyChoiceBox.getItems().addAll(agency);
        showListView();
        clearSelectedReport();
        handleSelectedListView();
        showStatusChoiceBox();
    }

    private String[] agency = {"กองยานพาหนะ", "อาคารและสถานที่" , "สำนักบริการคอมพิวเตอร์", "กองกิจการนิสิต", "สำนักการกีฬา", "สำนักงานทรัพย์สิน"};

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
        Report report = new Report(v);
        report.addVote();
    }

    @FXML
    public void handleHomeButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("welcome_page");
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    @FXML
    public void handleAddReportButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("addReport_page");
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    @FXML
    public void handleAllComplaintButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("allComplaint_page");
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
}
