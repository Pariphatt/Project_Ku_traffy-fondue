package ku.cs.controllers;
import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.models.Login;
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



    @FXML
    public void initialize() {
        alert = new Alert(Alert.AlertType.NONE);

    }
    public void handleLoginButton(ActionEvent actionEvent){
        login = new Login();
       if (username.getText().isEmpty() == true &&
               password.getText().isEmpty() == true){
           alert.setAlertType(Alert.AlertType.WARNING);
           alert.setContentText("Please enter username and password");
           alert.show();
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
       else {
           alert.setAlertType(Alert.AlertType.WARNING);
           alert.setContentText("Inviled login");
           alert.show();
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
