package ku.cs.controllers;

import animatefx.animation.FadeIn;
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
import ku.cs.models.Mode;
import ku.cs.models.accounts.Account;
import ku.cs.models.accounts.AccountList;
import ku.cs.models.accounts.StaffAccount;
import ku.cs.models.accounts.UserAccount;
import ku.cs.services.AccountListDataSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

public class ChangePasswordPageController {
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
    private AccountList accountList;
    private String pathImage;
    private AccountListDataSource userListDataSource;
    private Account accounts;
    private File file;
    public static boolean isLightMode = true;
    @FXML
    public void initialize() {
        accounts = new Account();
        alert = new Alert(Alert.AlertType.NONE);
        userListDataSource = new AccountListDataSource("assets", "accounts.csv");
        accountList = userListDataSource.readData();
        accounts = accountList.findUser((String) FXRouter.getData());
        userName.setText(accounts.getUsername());
        userName.setEditable(false);
        imageView.setImage(new Image(new File("imagesAvatar/" + accounts.getPicPath()).toURI().toString()));
        Mode.setMode(pane);
        new FadeIn(pane).play();
    }

    public String handleAddPhoto(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("images PNG JPG", "*.png", "*.jpg", "*.jpeg"));
        Node source = (Node) event.getSource();
         file = chooser.showOpenDialog(source.getScene().getWindow());
        String userName1 = userName.getText();
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
        if (accountList.changePicture(userName1) != null) {
            accounts = accountList.changePicture(userName1);
            accounts.setPicPath(pathImage);
            userListDataSource.writeData(accountList);
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("??????????????????????????????????????????????????????");
            alert.show();
        }
        return pathImage;
    }

    @FXML
    public void handleCancelButton(ActionEvent actionEvent) {
        try {
            if (accounts instanceof StaffAccount){
                com.github.saacsos.FXRouter.goTo("staff_page");
            }
            else if (accounts instanceof UserAccount) {
                com.github.saacsos.FXRouter.goTo("welcome_page");
            }
            else if (accounts instanceof Account) {
                com.github.saacsos.FXRouter.goTo("admin_page");
            }
        } catch (IOException e) {
            System.err.println("??????????????????????????? admin ??????????????????");
            System.err.println("?????????????????????????????????????????????????????? route");
        }
    }

    @FXML
    public void handleChangePasswordButton(ActionEvent actionEvent) {
        String userName1 = userName.getText();
        String currentPassword1 = currentPassword.getText();
        String newPassword1 = newPassword.getText();
        String confirmNewPassword1 = confirmNewPassword.getText();


        if (!(newPassword.getText().equals(confirmNewPassword.getText()))) {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setContentText("????????????????????????????????????????????????????????????????????????????????????");
            alert.show();
        }
        else if ((!accounts.validUsername(userName1))) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Username ??????????????????????????? A-Z ???????????? a-z ????????? 3-20 ????????????????????????");
            alert.show();
        }
        else if ((!accounts.validPassword(newPassword1))&& (!accounts.validPassword(confirmNewPassword1))) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("password ??????????????????????????? A-Z ???????????? a-z ???????????? 0-9 ????????? 6-20 ????????????????????????");
            alert.show();
        }
        else if (newPassword.getText().isEmpty() || confirmNewPassword.getText().isEmpty()) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("????????????????????????????????????????????????");
            alert.show();
        }
        else if (currentPassword1.equals(confirmNewPassword1) ) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("?????????????????????????????????????????????");
            alert.show();
        }
        else if (accountList.findPassword(userName1,currentPassword1)  == null ) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("??????????????????????????????????????????????????????????????????");
            alert.show();
        }
        else if (accountList.changePassword(userName1) != null) {
            accounts = accountList.changePassword(userName1);
            accounts.setPassword(newPassword1);
            userListDataSource.writeData(accountList);
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("???????????????????????????????????????????????????????????????");
            alert.show();

        }
    }
}
