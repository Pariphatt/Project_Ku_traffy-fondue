package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MyAccountController {
    @FXML private PasswordField currentPassword;
    @FXML private PasswordField newPassword;
    @FXML private PasswordField confirmNewPassword;

    @FXML private AnchorPane pane;

    @FXML
    void handleCancelButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("staff_homepage");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า staff_homepage ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
