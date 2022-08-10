package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {
    @FXML private TextField usernametextfield;
    @FXML private PasswordField passwordtextfield;


    //login button
    //กดไปหน้าหลักได้ก่อนยังไม่ได้ทำเงื่อนไข
    @FXML
    public void handleLoginButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("welcome_page");
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
