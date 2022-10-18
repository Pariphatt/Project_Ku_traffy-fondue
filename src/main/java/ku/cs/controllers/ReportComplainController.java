package ku.cs.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import ku.cs.models.Mode;
import ku.cs.models.reports.Report;
import ku.cs.models.reports.ReportList;
import ku.cs.services.ReportFIleDataSource;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ReportComplainController {
    @FXML
    private AnchorPane pane;
    @FXML
    private ListView<String> complainListView;
    @FXML
    private Label typeLabel;
    @FXML
    private Label topicLabel;
    @FXML
    private TextArea detailTextArea;
    @FXML
    private TextArea reasonsTextArea;
    @FXML
    private Button deleteButton;
    private List<Report> reportPostLists;
    private String selectedReportTopic;
    private Report tempReport = null;
    private ArrayList<String> arrayList;

    private ReportFIleDataSource reportFIleDataSource;

    private ReportFIleDataSource allReportData;

    @FXML
    public void initialize() {
        Mode.setMode(pane);
        reportFIleDataSource = new ReportFIleDataSource("assets", "report_post.csv");
        reportPostLists = reportFIleDataSource.readReportPost();
        detailTextArea.setEditable(false);
        reasonsTextArea.setEditable(false);
        handleSelectedListView();
        arrayList = new ArrayList<>();
        for (Report reportTemp : reportPostLists) {
            arrayList.add(reportTemp.getTopic());
        }
        showListView();
    }

    public void showListView() {
        for (String reportTemp : arrayList) {
            complainListView.getItems().add(reportTemp);
        }
    }

    private void handleSelectedListView() {
        complainListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println("Selected item: " + newValue);
                showSelectedReport(newValue);
                selectedReportTopic = newValue;
            }
        });
    }
    public void showSelectedReport(String title) {
        topicLabel.setText(title);
        tempReport = null;
        for (Report reportTemp : reportPostLists) {
            if (reportTemp.getTopic().equals(title)) {
                tempReport = reportTemp;
            }
        }
        if (tempReport != null) {
            typeLabel.setText(tempReport.getType());
            detailTextArea.setText(tempReport.getDetail());
            reasonsTextArea.setText(tempReport.getReasonsPost());
        }
    }
    @FXML
    void handleBackButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("admin");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า admin ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    @FXML
    void handleDeleteButton(ActionEvent actionEvent) throws IOException {
        allReportData = new ReportFIleDataSource("assets","reports.csv");
        ReportList rl = new ReportList();
        ReportList rlc = new ReportList();
        ReportList reportList = reportFIleDataSource.readFileDelete();
        ReportList rlComplaints = allReportData.readData();

        for (Report report : rlComplaints.getaAllReport()){
            if (!report.equals(rlComplaints.findReportComplaint(selectedReportTopic))){
                rlc.addReport(report);
            }
        }
        for(Report reportscomplaint : reportList.getaAllReport()){
            if(!reportscomplaint.equals(reportList.findReportComplaint(selectedReportTopic))){
                rl.addReport(reportscomplaint);
            }
        }
        reportFIleDataSource.writeFileDelete(rl);
        allReportData.writeData(rlc);
        deleteButton.setVisible(false);
//        complainListView.getItems().clear();
//        complainListView.getItems().addAll(selectedReportTopic);
//        complainListView.refresh();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("เรื่องร้องเรียนที่ถูกรายงาน");
        alert.setContentText("ลบเรื่องร้องเรียนสำเร็จ");
        alert.show();
        com.github.saacsos.FXRouter.goTo("report_complain");
    }
}


