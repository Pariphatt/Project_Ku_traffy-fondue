package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import ku.cs.models.reports.DetailReport;

import java.io.File;
import java.io.IOException;

public class WelcomePageController {
    private DetailReport report;
    @FXML private TextField topicTextField;
    @FXML private TextField detailTextField;
    private Alert alert;

    @FXML
    public void initialize() {alert = new Alert(Alert.AlertType.NONE);}

    public void reportButton(ActionEvent actionEvent){
        report = new DetailReport();
        if (topicTextField.getText().equals("") || detailTextField.getText().equals("")) {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setContentText("โปรดกรอกข้อมูลให้ครบทุกช่อง");
            alert.show();
        } else {
            report.reportButton(topicTextField.getText(),detailTextField.getText());
            try {
                com.github.saacsos.FXRouter.goTo("welcome_page");
            } catch (IOException e) {
                System.err.println(e);
            }
        }

    }

    @FXML public void handleHomeButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("welcome_page");
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    @FXML
    public void handleVoteButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("vote_page");
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    @FXML
    public void handleEmpty1Button(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("empty1_page");
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    @FXML
    public void handleEmpty2Button(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("empty2_page");
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
