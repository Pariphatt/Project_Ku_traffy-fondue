package ku.cs.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import ku.cs.models.AdminLogin;
import ku.cs.models.Login;

public class AdminLoginController {
    private AdminLogin adminLogin;
    private Alert alert;
    @FXML
    public void initialize() {
        alert = new Alert(Alert.AlertType.NONE);

    }
}
