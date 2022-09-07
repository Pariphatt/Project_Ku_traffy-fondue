package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class Empty2PageController {
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
