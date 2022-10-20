package ku.cs.controllers;

import animatefx.animation.FadeIn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import ku.cs.models.Mode;

import java.io.IOException;

public class GuideBookPageController {
    @FXML private AnchorPane pane;

    @FXML
    public void initialize() {
        Mode.setMode(pane);
        new FadeIn(pane).play();
    }
    @FXML
    public void handleBackButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("home_page");
        } catch (IOException e) {
            System.err.println("ไปทีหน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
            e.printStackTrace(); //ดูว่าerorตรงไหนบ้าง
        }
    }
}
