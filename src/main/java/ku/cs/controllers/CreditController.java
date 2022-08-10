package ku.cs.controllers;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.github.saacsos.FXRouter;
public class CreditController {

    //    @FXML
//    private ImageView imagenat;
//    private ImageView imageneuw;
//    private ImageView imagechutter;
//    private ImageView imagearty;
//    @FXML public void initialize(){
//        String url = getClass().getResource("/ku/cs/images/nat.jpg").toExternalForm();
//        imagenat.setImage(new Image(url));
//        String url = getClass().getResource("/ku/cs/images/neuw.jpg").toExternalForm();
//        imageneuw.setImage(new Image(url));
//        String url = getClass().getResource("/ku/cs/images/chutter.jpg").toExternalForm();
//        imagechutter.setImage(new Image(url));
//        String url = getClass().getResource("/ku/cs/images/arty.jpg").toExternalForm();
//        imagearty.setImage(new Image(url));
//    }
    @FXML
    public void handleBackButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที7หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
            e.printStackTrace(); //ดูว่าerorตรงไหนบ้าง
        }
    }



}
