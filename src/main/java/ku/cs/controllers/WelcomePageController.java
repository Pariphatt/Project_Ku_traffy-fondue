package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import com.github.saacsos.FXRouter;

public class WelcomePageController {
    @FXML private ImageView image;

    @FXML public void initialize(){
        String url = getClass().getResource("/ku/cs/images/test.jpg").toExternalForm();
        image.setImage(new Image(url));
    }
    @FXML public void handleHomeButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("welcome_page");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            System.err.println(e);
        }
    }
    @FXML
    public void handleReportListButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("report_list");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            System.err.println(e);
        }
    }
}
