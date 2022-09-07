package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class WelcomePageController {

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
