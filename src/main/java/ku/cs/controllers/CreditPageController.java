package ku.cs.controllers;
import java.io.IOException;

import animatefx.animation.FadeInDown;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.github.saacsos.FXRouter;
import javafx.scene.layout.AnchorPane;
import ku.cs.models.Mode;

public class CreditPageController {
    @FXML private AnchorPane pane;

    @FXML
    public void initialize() {
        Mode.setMode(pane);
        new FadeInDown(pane).play();
    }
    @FXML
    public void handleBackButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("home_page");
        } catch (IOException e) {
            System.err.println("ไปที7หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
            e.printStackTrace(); //ดูว่าerorตรงไหนบ้าง
        }
    }



}
