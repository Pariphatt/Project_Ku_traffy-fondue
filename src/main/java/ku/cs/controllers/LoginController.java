package ku.cs.controllers;
import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import ku.cs.models.Login;
import ku.cs.services.ThemeMode;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import java.net.URL;
public class LoginController  {
    @FXML private TextField username;
   @FXML private PasswordField password;
    @FXML private ImageView iconFileImage;
    @FXML private Label loginMessageLabel;
    private Login login;
    private Alert alert;
    //login button
    //กดไปหน้าหลักได้ก่อนยังไม่ได้ทำเงื่อนไข
    @FXML private AnchorPane pane;
    @FXML private Button mode;
    public static boolean isLightMode = true;


    @FXML
    public void initialize() {
        alert = new Alert(Alert.AlertType.NONE);
        ThemeMode.setThemeMode(pane);

    }
    public void handleLoginButton(ActionEvent actionEvent){
        login = new Login();
       if (username.getText().isEmpty() == true &&
               password.getText().isEmpty() == true){
           alert.setAlertType(Alert.AlertType.WARNING);
           alert.setContentText("Please enter username and password");
           alert.show();
       }
       else if ( login.staffLogin(username.getText(),password.getText())){
           {
               try {
                   com.github.saacsos.FXRouter.goTo("staff_homepage");
               } catch (IOException e) {
                   throw new RuntimeException(e);
               }
           }
       }
       else if ( login.validateLogin(username.getText(),password.getText())){
               {
                   try {
                       com.github.saacsos.FXRouter.goTo("welcome_page");
                   } catch (IOException e) {
                       throw new RuntimeException(e);
                   }
               }
       }
       else if ( login.adminLogin(username.getText(),password.getText())){
           {
               try {
                   com.github.saacsos.FXRouter.goTo("admin");
               } catch (IOException e) {
                   throw new RuntimeException(e);
               }
           }
       }

       else {
           alert.setAlertType(Alert.AlertType.WARNING);
           alert.setContentText("Inviled login");
           alert.show();
       }


       }
       @FXML

    public void handleDarkModeButton(ActionEvent event) {
        isLightMode = !isLightMode;
        if (isLightMode) {
            ThemeMode.setLightMode(pane, mode);
        } else {
            ThemeMode.setDarkMode(pane, mode);
        }
    }


    public void handleBackButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("home");
        }catch (IOException e) {
            //System.err.println("ยังไม่สร้างhomepage");
        }
    }
}
