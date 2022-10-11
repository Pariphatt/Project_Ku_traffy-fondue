package ku.cs.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import com.github.saacsos.FXRouter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import ku.cs.models.account.Account;
import ku.cs.models.account.AccountList;
import ku.cs.models.account.UserAccount;
import ku.cs.services.AccountListDataSource;
import ku.cs.services.DataSource;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterController {
    private String Path;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private ImageView imageView;

    private String pathImage;

    private BufferedImage pic = null;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;
    private Account accounts;
    @FXML
    private Button Register;
    private Alert alert;
    private AccountListDataSource userListDataSource;
    private AccountList accountList;

    private File file;

    @FXML
    public void initialize() {
        accounts = new Account();
        userListDataSource = new AccountListDataSource("assets", "accounts.csv");
        accountList = userListDataSource.readData();
//        accounts = accountList.findUser((String) FXRouter.getData());
        for (Account account : accountList.getAllUsers()){
            System.out.println(account.getRole());
        }
        alert = new Alert(Alert.AlertType.NONE);
    }


    // add รูปภาพ
    public String handleAddPhoto(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("images PNG JPG", "*.png", "*.jpg", "*.jpeg"));
        Node source = (Node) event.getSource();
        File file = chooser.showOpenDialog(source.getScene().getWindow());
        if (file != null){
            imageView.setImage(new Image(file.getAbsolutePath()));
        }

        return pathImage;
    }


    public void RegisterButton(ActionEvent actionEvent) {
        String name = nameTextField.getText();
        String usernameText = usernameTextField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        if ((!accounts.validUsername(usernameTextField.getText()))) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Username ใส่ได้แค่ A-Z หรือ a-z ยาว 3-20 ตัวอักษร");
            alert.show();
        }
        else if ((!accounts.validPassword(password))) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("password ใส่ได้แค่ A-Z หรือ a-z หรือ 0-9 ยาว 6-20 ตัวอักษร");
            alert.show();
        }
        else if (nameTextField.getText().isEmpty() || usernameTextField.getText().isEmpty() ||
                    passwordField.getText().isEmpty() ||
                    confirmPasswordField.getText().isEmpty()) {
                alert.setAlertType(Alert.AlertType.WARNING);
                alert.setContentText("โปรดกรอกข้อมูลให้ครบทุกช่อง");
                alert.show();
        }
        else if (!(passwordField.getText().equals(confirmPassword))) {
                alert.setAlertType(Alert.AlertType.WARNING);
                alert.setContentText("โปรดกรอกรหัสผ่านให้เหมือนกัน");
                alert.show();
        }
        else  {
                if (accountList.isExistUsername(usernameText)) {
                    System.out.println(accountList.isExistUsername(usernameText));
                    System.out.println(usernameText);
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setContentText("มีผู้ใช้งานนี้ในระบบแล้ว");
                    alert.show();
                } else {
                    userListDataSource = new AccountListDataSource("assets", "accounts.csv");
                    if (file != null) {
                        try {
                            File destDir = new File("imagesAvatar");
                            if (!destDir.exists()) destDir.mkdirs();
                            String[] fileSplit = file.getName().split("\\.");
                            String filename = LocalDate.now() + "_" + System.currentTimeMillis() + "."
                                    + fileSplit[fileSplit.length - 1];
                            Path target = FileSystems.getDefault().getPath(
                                    destDir.getAbsolutePath() + System.getProperty("file.separator") + filename
                            );
                            System.out.println(file.toPath());
                            System.out.println(target);
                            Files.copy(file.toPath(), target, StandardCopyOption.REPLACE_EXISTING);
                            imageView.setImage(new Image(target.toUri().toString()));
                            pathImage = filename;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (pathImage == null) {
                        UserAccount user = new UserAccount("User", name, usernameText, password, "profile-user.png", "never");
                        System.out.println(user.getUsername());
                        accountList.addUser(user);
                    } else {
                        File dest = new File("assets/imagesAvatar/" + pathImage);
                        accountList.addUser(new UserAccount("User", name, usernameText, password, pathImage, "never"));
                    }
                    System.out.println(accountList.getAllUsers().get(0));
                    userListDataSource.writeData(accountList);
                    System.out.println(accountList.getAllUsers());
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setContentText("ลงทะเบียนสำเร็จ");
                    alert.show();

                    try {
                        com.github.saacsos.FXRouter.goTo("login");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

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
