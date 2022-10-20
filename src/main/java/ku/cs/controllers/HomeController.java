package ku.cs.controllers;
import animatefx.animation.Bounce;
import animatefx.animation.BounceIn;
import animatefx.animation.FadeIn;
import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import ku.cs.models.Mode;
import ku.cs.models.account.Account;
import ku.cs.models.account.AccountList;
import ku.cs.services.AccountListDataSource;
import ku.cs.services.DataSource;


import java.io.IOException;

public class HomeController {

    public static boolean isLightMode = true;
    @FXML private Button mode;
    @FXML private AnchorPane pane;
    @FXML private Button loginBTN;
    @FXML
    public void initialize() {
        Mode.setMode(pane);
        new FadeIn(pane).play();
    }
    @FXML
    public void handleDarkModeButton(ActionEvent event) {
        isLightMode = !isLightMode;
        if (isLightMode) {
            Mode.setLightMode(pane, mode);
        } else {
            Mode.setDarkMode(pane, mode);
        }
        new BounceIn(mode).play();
    }
    @FXML
    public void handleCreditButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("credit");
        } catch (IOException e) {
            System.err.println("ไปที7หน้า profile ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
            e.printStackTrace(); //ดูว่าerorตรงไหนบ้าง
        }
    }
    @FXML
    public void handleguidebookButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("guidebook");
        } catch (IOException e) {
            System.err.println("ไปที7หน้า guidebook ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
            e.printStackTrace(); //ดูว่าerorตรงไหนบ้าง
        }
    }
    @FXML
    public void handleRegisterButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("Register");
        } catch (IOException e) {
            System.err.println("ไปที7หน้า login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
            e.printStackTrace(); //ดูว่าerorตรงไหนบ้าง
        }
    }

    @FXML
    public void handleSignInButton(ActionEvent actionEvent) {
        try {

            FXRouter.goTo("login");
        } catch (IOException e) {
            System.err.println("ไปที7หน้า login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
            e.printStackTrace(); //ดูว่าerorตรงไหนบ้าง
        }
    }
}
