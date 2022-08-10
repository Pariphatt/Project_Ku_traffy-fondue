package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {
    @FXML private TextField usernametextfield;
    @FXML private TextField passwordtextfield;


    //login button
    @FXML
    public void handleLoginButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("home");
        }catch (IOException e) {
            //System.err.println("ยังไม่สร้างhomepage");
        }
    }
    @FXML
    public void handleBackButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("home");
        }catch (IOException e) {
            //System.err.println("ยังไม่สร้างhomepage");
        }
    }
}
