package ku.cs.controllers;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import ku.cs.models.Register;
import ku.cs.models.RegisterStaff;
import ku.cs.services.AccountListDataSource;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

public class RegisterStaffController {
    private String Path;
    @FXML
    private TextField NameTextField;
    @FXML
    private TextField UsernameTextField;
    @FXML
    private ImageView imageView;
    private RegisterStaff registerStaff;
    private String pathImage ;
    private Register imagePath;
    private BufferedImage pic = null;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Button Register;
    @FXML private ChoiceBox<String> agencyChoiceBox;
    private Alert alert;
    private AccountListDataSource userListDataSource = new AccountListDataSource("assets","user.csv");
    @FXML
    public void initialize() {
        alert = new Alert(Alert.AlertType.NONE);
        agencyChoiceBox.getItems().addAll( agency);
    }

    private String[]  agency = {"กองยานพาหนะ","อาคารและสถานท" +
            "" +
            "ี่","สำนักบริการคอมพิวเตอร์","กองกิจการนิสิต","สำนักการกีฬา","สำนักงานทรัพย์สิน"};

    // add รูปภาพ
    public String handleAddPhoto(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("images PNG JPG", "*.png", "*.jpg", "*.jpeg"));
        Node source = (Node) event.getSource();
        File file = chooser.showOpenDialog(source.getScene().getWindow());
        if (file != null){
            try {
                File destDir = new File("imagesAvatarSaff");
                if (!destDir.exists()) destDir.mkdirs();
                // RENAME FILE
                String[] fileSplit = file.getName().split("\\.");
                String filename = LocalDate.now() + "_"+System.currentTimeMillis() + "."
                        + fileSplit[fileSplit.length - 1];
                Path target = FileSystems.getDefault().getPath(
                        destDir.getAbsolutePath()+System.getProperty("file.separator")+filename
                );
                System.out.println(file.toPath());
                System.out.println(target);
                Files.copy(file.toPath(), target, StandardCopyOption.REPLACE_EXISTING );
                imageView.setImage(new Image(target.toUri().toString()));
                pathImage = filename ;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return pathImage;
    }


    public void RegisterStaffButton(ActionEvent actionEvent){
        registerStaff = new RegisterStaff();
        if (NameTextField.getText().isEmpty() || UsernameTextField.getText().isEmpty() ||
                passwordField.getText().isEmpty() ||
                confirmPasswordField.getText().isEmpty() ||
                agencyChoiceBox.getItems().isEmpty()){
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setContentText("โปรดกรอกข้อมูลให้ครบทุกช่อง");
            alert.show();
        }
        else if(passwordField.getText().equals(confirmPasswordField.getText())){
            registerStaff.validateRegisterStaff(NameTextField.getText(),UsernameTextField.getText(),
                    passwordField.getText(),confirmPasswordField.getText(),pathImage,agencyChoiceBox.getValue());
            {
                try {
                    FXRouter.goTo("login");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        else if(!(passwordField.getText().equals(confirmPasswordField.getText()))){
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setContentText("โปรดกรอกข้อมูลให้เหมือนกัน");
            alert.show();
        }
    }



    public void handleBackButton(ActionEvent actionEvent){
        try {
            FXRouter.goTo("admin");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า admin ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }

    }
}
