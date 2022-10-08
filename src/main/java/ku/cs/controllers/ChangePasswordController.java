package ku.cs.controllers;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import ku.cs.models.account.Account;
import ku.cs.models.account.AccountList;
import ku.cs.models.account.StaffAccount;
import ku.cs.models.account.UserAccount;
import ku.cs.services.AccountListDataSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

public class ChangePasswordController {
    @FXML
    private TextField userName;
    @FXML
    private PasswordField currentPassword;
    @FXML
    private PasswordField newPassword;
    @FXML
    private PasswordField confirmNewPassword;
    @FXML
    private ImageView imageView;

    @FXML
    private AnchorPane pane;
    private Alert alert;
    private AccountList userList;
    private String pathImage ;
    private AccountListDataSource userListDataSource;
    private Account account;
    private File file;

    @FXML
    public void initialize() {
        alert = new Alert(Alert.AlertType.NONE);
        userListDataSource = new AccountListDataSource("assets", "accounts.csv");
        userList = userListDataSource.readData();
        account = userList.findUser((String) FXRouter.getData());
        userName.setText(account.getUsername());
        userName.setDisable(true);
        imageView.setImage(new Image(new File("imagesAvatar/" + account.getPicPath()).toURI().toString()));
    }
    public String handleAddPhoto(ActionEvent event) {
        String userName1 = userName.getText();
        FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("images PNG JPG", "*.png", "*.jpg", "*.jpeg"));
        Node source = (Node) event.getSource();
        file = chooser.showOpenDialog(source.getScene().getWindow());
        if (file != null) {
            try {
                File destDir = new File("imagesAvatar");
                if (!destDir.exists()) destDir.mkdirs();
                String[] fileSplit = file.getName().split("\\.");
                String filename = account.getName() + "."
                        + fileSplit[fileSplit.length - 1];
                Path target = FileSystems.getDefault().getPath(
                        destDir.getAbsolutePath() + System.getProperty("file.separator") + filename
                );
                System.out.println(file.toPath());
                System.out.println(target);
                Files.copy(file.toPath(), target, StandardCopyOption.REPLACE_EXISTING);
                imageView.setImage(new Image(target.toUri().toString()));
                pathImage = filename;
                System.out.println(pathImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (userList.changePicture(userName1) != null) {
                account = userList.changePicture(userName1);
                account.setPicPath(pathImage);
                userListDataSource.writeData(userList);
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("เปลี่ยรูปภาพสำเร็จ");
                alert.show();
            }


        return pathImage;
    }


    @FXML
    public void handleCancelButton(ActionEvent actionEvent) {
        try {
            if (account instanceof StaffAccount){
                com.github.saacsos.FXRouter.goTo("staff_homepage");
            }
            else if (account instanceof UserAccount) {
                com.github.saacsos.FXRouter.goTo("welcome_page");
            }
            else if (account instanceof Account) {
                com.github.saacsos.FXRouter.goTo("admin");
            }
        } catch (IOException e) {
            System.err.println("ไปที่หน้า admin ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
//    @FXML
//    public void handleConfirmButton(ActionEvent actionEvent){
//            account = userList.changePicture(pathImage);
//            account.setPicPath(pathImage);
//            userListDataSource.writeData(userList);
//            alert.setAlertType(Alert.AlertType.INFORMATION);
//            alert.setContentText("เปลี่ยรูปภาพสำเร็จ");
//            alert.show();
//
//    }
    @FXML
    public void handleChangePasswordButton(ActionEvent actionEvent) {
        String userName1 = userName.getText();
        String currentPassword1 = currentPassword.getText();
        String newPassword1 = newPassword.getText();
        String confirmNewPassword1 = confirmNewPassword.getText();


        if (!(newPassword.getText().equals(confirmNewPassword.getText()))) {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setContentText("โปรดกรอกรหัสผ่านให้เหมือนกัน");
            alert.show();
        }
        else if (newPassword.getText().isEmpty() || confirmNewPassword.getText().isEmpty()) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("โปรดกรอกรหัสผ่าน");
            alert.show();
        } else if (userList.changePassword(userName1) != null) {
            account = userList.changePassword(userName1);
            account.setPassword(newPassword1);
            userListDataSource.writeData(userList);
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("เปลี่ยนรหัสผ่านสำเร็จ");
            alert.show();

        }
    }
}
