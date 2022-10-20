package ku.cs.controllers;
import animatefx.animation.BounceIn;
import animatefx.animation.FadeIn;
import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import ku.cs.models.Mode;


import java.io.IOException;

public class HomePageController {

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
            FXRouter.goTo("credit_page");
        } catch (IOException e) {
            System.err.println("ไปที7หน้า profile ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
            e.printStackTrace(); //ดูว่าerorตรงไหนบ้าง
        }
    }
    @FXML
    public void handleguidebookButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("guide_book_page");
        } catch (IOException e) {
            System.err.println("ไปที7หน้า guidebook ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
            e.printStackTrace(); //ดูว่าerorตรงไหนบ้าง
        }
    }
    @FXML
    public void handleRegisterButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("register_page");
        } catch (IOException e) {
            System.err.println("ไปที7หน้า login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
            e.printStackTrace(); //ดูว่าerorตรงไหนบ้าง
        }
    }

    @FXML
    public void handleSignInButton(ActionEvent actionEvent) {
        try {

            FXRouter.goTo("login_page");
        } catch (IOException e) {
            System.err.println("ไปที7หน้า login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
            e.printStackTrace(); //ดูว่าerorตรงไหนบ้าง
        }
    }
}
