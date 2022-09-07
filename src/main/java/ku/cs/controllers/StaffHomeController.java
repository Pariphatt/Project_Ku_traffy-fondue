package ku.cs.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import ku.cs.models.reports.Report;
import ku.cs.models.reports.ReportList;
import ku.cs.services.ReportListDataSource;

import java.io.IOException;

public class StaffHomeController {
    @FXML
    private ListView<Report> reportListView;
    @FXML private Label userLebel;
    @FXML private Label agencyLebel;
    @FXML private Label detailLebel;
    private ReportListDataSource dataSource;
    private ReportList reportList;
    @FXML
    public void initialize() {
        dataSource = new ReportListDataSource();
        reportList = dataSource.getReportList();
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
                    public void changed(ObservableValue<? extends Report>
                                                observable,
                                        Report oldValue, Report newValue) {
                        System.out.println(newValue + " is selected");
                        showSelectedReport(newValue);
                    }
                });
    }
    private void showSelectedReport(Report report) {
        agencyLebel.setText(report.getAgency());
        detailLebel.setText(report.getDetail());
    }
    private void clearSelectedReport() {
        detailLebel.setText("");
        agencyLebel.setText("");
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
    public void handleMyAccountButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("my_account");
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
