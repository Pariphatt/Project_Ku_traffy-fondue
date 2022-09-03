package ku.cs.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.github.saacsos.FXRouter;
import ku.cs.models.Register;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ku.cs.models.Login;
import ku.cs.models.Register;
import ku.cs.models.UserList;
import ku.cs.services.UserListDataSource;
import com.github.saacsos.FXRouter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

public class RegisterController{
    private String Path;
    @FXML
    private TextField NameTextField;
    @FXML
    private TextField UsernameTextField;
    @FXML
    private ImageView  imageView;
    private Register register;
    private BufferedImage pic = null;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Button Register;
    private Alert alert;
    @FXML
    public void initialize() {
        alert = new Alert(Alert.AlertType.NONE);

    }



    // add รูปภาพ
    public void handleAddPhoto(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg"));
        File picFile = fileChooser.showOpenDialog(null);
        imageView.setEffect(new DropShadow(20, Color.BLACK));
        if (picFile != null) {
            Path = picFile.getAbsolutePath();
            imageView.setImage(new Image(new File(Path).toURI().toString()));
        }
        try {
            pic = ImageIO.read(picFile);
        } catch (IOException e) {
            System.err.println("Cannot load picture");
        }
    }

    public void RegisterButton(ActionEvent actionEvent){
        register = new Register();
        if (NameTextField.getText().isEmpty() == true || UsernameTextField.getText().isEmpty() == true ||
                passwordField.getText().isEmpty() == true ||
                confirmPasswordField.getText().isEmpty() == true  ){
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setContentText("โปรดกรอกข้อมูลให้ครบทุกช่อง");
            alert.show();
        }
        else if(passwordField.getText().equals(confirmPasswordField.getText()) == true){
            register.validateRegister(NameTextField.getText(),UsernameTextField.getText(),
                passwordField.getText(),confirmPasswordField.getText(),Path);
            {
                try {
                    FXRouter.goTo("login");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        else if(!(passwordField.getText().equals(confirmPasswordField.getText())) == true){
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setContentText("โปรดกรอกข้อมูลให้เหมือนกัน");
            alert.show();
        }
       }



    public void handleBackButton(ActionEvent actionEvent){
        try {
            FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }

    }

}
