package ku.cs.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ku.cs.models.Account;
import com.github.saacsos.FXRouter;

import java.io.IOException;

public class RegisterController{
    private Account Register;
    @FXML
    private TextField Name;
    @FXML
    private TextField Username;

    @FXML
    private PasswordField PasswordHidden;

    @FXML
    private TextField PasswordText;

    @FXML
    private CheckBox checkBox;
    @FXML
    private Label ConfirmPassword;

    @FXML
    void changeVisibility(ActionEvent event){
        if (checkBox.isSelected()){
            PasswordText.setText(PasswordHidden.getText());
            PasswordText.setVisible(true);
            PasswordHidden.setVisible(false);
            return;
        }
        PasswordHidden.setText(PasswordText.getText());
        PasswordHidden.setVisible(true);
        PasswordText.setVisible(false);
    }

    public void handleBackButton(ActionEvent actionEvent){
        try {
            FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }

    }

}
