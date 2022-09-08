package ku.cs.controllers;

import com.github.saacsos.FXRouter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import ku.cs.models.reports.Report;
import ku.cs.models.reports.ReportList;
import ku.cs.services.DataSource;
import ku.cs.services.ReportListDataSource;
import ku.cs.services.ReportListHardCodeDataSource;

import java.io.IOException;

public class StaffHomeController {
    @FXML
    private ListView<Report> reportListView;

    @FXML
    private Label userLabel;
    @FXML private Label detailLabel;
    @FXML
    private Label agencyLabel;

    private DataSource<ReportList> dataSource;
    private ReportList reportList;

    @FXML
    public void initialize() {
        int test = 0;
        if (test == 1) {
            dataSource = new ReportListHardCodeDataSource();
        } else {
            dataSource = new ReportListDataSource("assets", "reports.csv");
        }

        reportList = dataSource.readData();
        showListView();
        clearSelectedReport();
        handleSelectedListView();
    }
    private void showListView() {
        reportListView.getItems().addAll(reportList.getAllReports());
        reportListView.refresh();
    }
    private void handleSelectedListView() {
        reportListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Report>() {
                    @Override
                    public void changed(ObservableValue<? extends Report> observable,
                                        Report oldValue, Report newValue) {
                        System.out.println(newValue + " is selected");
                        showSelectedReport(newValue);
                    }
                });
    }
    private void showSelectedReport(Report report){
        detailLabel.setText(report.getDetail());
    }
    private void clearSelectedReport() {
        detailLabel.setText("");
        agencyLabel.setText("");
    }

    @FXML
    public void handleLogoutButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("login");
        } catch (IOException e) {
            System.err.println("ไปที7หน้า profile ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
            e.printStackTrace(); //ดูว่าerorตรงไหนบ้าง
        }
    }

    @FXML
    public void handleMyAccountButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("my_account");
        } catch (IOException e) {
            System.err.println("ไปที7หน้า profile ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
            e.printStackTrace(); //ดูว่าerorตรงไหนบ้าง
        }
    }
}
