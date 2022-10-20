package ku.cs.controllers;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import ku.cs.models.Mode;
import ku.cs.models.accounts.Account;
import ku.cs.models.accounts.AccountList;
import ku.cs.models.accounts.StaffAccount;
import ku.cs.services.AccountListDataSource;
import ku.cs.services.DataSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

public class RegisterStaffPageController {
    private AccountList accountList;
    private DataSource<AccountList> accountListDataSource;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private ImageView imageView;
    private String pathImage;

    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private ChoiceBox<String> agencyChoiceBox;
    private Account accounts;
    private Alert alert;
    private File file;
    @FXML private AnchorPane pane;
    @FXML
    public void initialize() {
        accounts = new Account();
        alert = new Alert(Alert.AlertType.NONE);
        agencyChoiceBox.getItems().addAll(agency);
        accounts = new Account();
        accountList = new AccountList();
        alert = new Alert(Alert.AlertType.NONE);
        AccountListDataSource accountListDataSource1 = new AccountListDataSource("assets", "accounts.csv");
        accountList = accountListDataSource1.readData();
        Mode.setMode(pane);
    }

    private String[] agency = {"กองยานพาหนะ", "อาคารและสถานที่" , "สำนักบริการคอมพิวเตอร์", "กองกิจการนิสิต", "สำนักงานทรัพย์สิน", "สำนักงานอธิการบดี"};

    // add รูปภาพ
    public String handleAddPhoto(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("images PNG JPG", "*.png", "*.jpg", "*.jpeg"));
        Node source = (Node) event.getSource();
       file = chooser.showOpenDialog(source.getScene().getWindow());
        if (file != null){
            imageView.setImage(new Image(file.getAbsolutePath()));
        }
        return pathImage;
    }


    public void RegisterStaffButton(ActionEvent actionEvent) {
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
                confirmPasswordField.getText().isEmpty() ||
                agencyChoiceBox.getItems().isEmpty()) {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setContentText("โปรดกรอกข้อมูลให้ครบทุกช่อง");
            alert.show();
        }
        else if (accountList.isExistUsername(usernameText)){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("มีผู้ใช้งานนี้ในระบบแล้ว");
            alert.show();

        }
        else if (!accountList.isExistUsername(usernameText)) {
            accountListDataSource = new AccountListDataSource("assets", "accounts.csv");
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
                    Files.copy(file.toPath(), target, StandardCopyOption.REPLACE_EXISTING);
                    imageView.setImage(new Image(target.toUri().toString()));
                    pathImage = filename;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (pathImage == null) {
                StaffAccount user = new StaffAccount("staff", name, usernameText, password, "profile-user.png",agencyChoiceBox.getValue(),"never");
                accountList.addUser(user);
            } else {
                File dest = new File("assets/imagesAvatar/" + pathImage);
                accountList.addUser(new StaffAccount("staff", name, usernameText, password, pathImage,agencyChoiceBox.getValue(),"never"));
            }
            accountListDataSource.writeData(accountList);
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("ลงทะเบียนสำเร็จ");
            alert.show();

            try {
                com.github.saacsos.FXRouter.goTo("admin_page");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (!(passwordField.getText().equals(confirmPasswordField.getText()))) {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setContentText("โปรดกรอกข้อมูลให้เหมือนกัน");
            alert.show();
        }
    }
    public void handleBackButton(ActionEvent actionEvent){
        try {
            FXRouter.goTo("admin_page");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }

    }
}
