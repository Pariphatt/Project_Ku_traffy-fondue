package ku.cs.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import ku.cs.models.reports.Filterer;
import ku.cs.models.reports.Report;
import ku.cs.models.reports.TopicReport;
import ku.cs.services.ReportListFileDataSource;

import java.awt.*;
import java.util.Collection;
import java.util.HashSet;

public class StaffHomepageController {
    @FXML
    Label topicLabel;
    @FXML
    Label statusLabel;
    @FXML
    ChoiceBox agencyChoiceBox;
    @FXML
    ListView reportListView;
    @FXML
    TextArea detailTextArea;
    @FXML
    TextField searchReportTextField;

    private ReportListFileDataSource dataSource;
    private Report report;

    public void initialize() {
        dataSource = new ReportListFileDataSource("asset", "reports.csv");
        report = dataSource.readData();
        showListView(); // load word list
        showChoiceBoxView();// choice box load
        clearSelectedReport(); // set label to empty
        handleSelectedListView();// handle with selecting in list word
    }

    private void showChoiceBoxView() {
        Report posTemp = dataSource.readData();
        Collection<String> agency = new HashSet<>();
        for (String topic : posTemp.getaAllReport()) {
            TopicReport found = report.find(topic);
            agency.add(found.getAgency().trim());
        }
        agencyChoiceBox.getItems().addAll(agency);
        //agencyChoiceBox.setOnAction(this::handleSearchChoiceBox);
    }

    private void showListView() {

        reportListView.getItems().addAll(report.getaAllReport());
        reportListView.refresh();
    }

    private void handleSelectedListView() {
        reportListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // Your action here
                System.out.println("Selected item: " + newValue);
                showSelectedReport(newValue);
            }
        });
    }

    private void showSelectedReport(String topic) {
        TopicReport topicReport = report.find(topic);
        topicLabel.setText(topicReport.getTopic());
        String detail = topicReport.getDetail().replace(";", ",");
        detailTextArea.setText(detail);
    }

    private void clearSelectedReport() {
        topicLabel.setText("");
        statusLabel.setText("");
        detailTextArea.setText("");
    }
//    private void handleSearchChoiceBox(ActionEvent actionEvent) {
//        report = dataSource.readData();
//        String agency = agencyChoiceBox.getValue();
//
//        Report search = report.filterBy(new Filterer<TopicReport>() {
//            @Override
//            public boolean filter(TopicReport o) {
//                return o.getAgency().contains(agency);
//            }
//        });
//
//        report = search;
//        wordListView.getItems().clear();
//        showListView();
//    }

    public void handleSearchButton(ActionEvent actionEvent) {
        //อ่าน input
        String input = searchReportTextField.getText();
        //***************

        Report search = report.filterBy(new Filterer<TopicReport>() {
            @Override
            public boolean filter(TopicReport o) {
                return o.getTopic().contains(input);
            }
        });
        report = search;

        if (input == ""){
            report = dataSource.readData();
        }
        reportListView.getItems().clear();
        showListView();

    }
}