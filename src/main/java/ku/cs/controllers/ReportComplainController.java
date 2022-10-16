package ku.cs.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
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
    private List<Report> reportPostLists;
    private String selectedReportTopic;
    private Report tempReport = null;
    private ArrayList<String> arrayList;

    private ReportFIleDataSource reportFIleDataSource;


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
//            complainListView.getItems().addAll(allTopic);

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

    private void refresh() {

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
        ReportList rl = new ReportList();
        ReportList reportList = reportFIleDataSource.readFileDelete();
        for (Report reportscomplaint : reportList.getaAllReport()) {
            if (!reportscomplaint.equals(reportList.findReportComplaint(selectedReportTopic))) {
                rl.addReport(reportscomplaint);
            }
        }
        reportFIleDataSource.writeFileDelete(rl);
            com.github.saacsos.FXRouter.goTo("report_complain");
    }
}
//    @FXML
//    public void handleDeleteButton(ActionEvent actionEvent) {
//        if (tempReport != null){
//            ReportFIleDataSource reportFIleDataSource = new ReportFIleDataSource("assets","report_post.csv");
//            int count = 0;
//            for (String temp : arrayList){
////                if (){
////                    arrayList.remove(count);
////                }
//            count++;
//            }
//          complainListView.getItems().clear();
//            showListView();
////            ArrayList<String[]> removePost1 = reportFIleDataSource.getRemovePost();
////            reportFIleDataSource.writeFileDynamic(removePost1);
////            complainListView.getItems().clear();
////           showListView();
//        }
//    }

