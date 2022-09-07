package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import ku.cs.models.reports.Report;

import java.io.IOException;

public class Empty1PageController {
    private ListView<Report> reportListView;
    private Label agencyLabel;
    private Label detailLabel;
    private Label statusLabel;
    private Label timeLabel;
    private Label voteLabel;

    @FXML
    public void handleHomeButton(ActionEvent actionEvent){
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
    public void handleDetailButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("empty1_page");
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    @FXML
    public void handleCategoryButton(ActionEvent actionEvent){
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
