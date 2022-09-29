package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import ku.cs.models.User;
import ku.cs.models.account.AccountList;
import ku.cs.services.AccountListDataSource;

import java.io.IOException;

public class ChangePasswordController {
    @FXML private TextField userName;
    @FXML private PasswordField currentPassword;
    @FXML private PasswordField newPassword;
    @FXML private PasswordField confirmNewPassword;

    @FXML private AnchorPane pane;
    private Alert alert;
    private AccountList userList;
    private AccountListDataSource userListDataSource ;
    User user;
    @FXML
    public void initialize() {
        alert = new Alert(Alert.AlertType.NONE);
        userListDataSource = new AccountListDataSource("assets","accounts.csv");

    }
    @FXML
    public void handleCancelButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("admin");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า admin ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    @FXML
    public void handleChangePasswordButton(ActionEvent actionEvent) {
        String userName1 = userName.getText();
        String currentPassword1 = currentPassword.getText();
        String newPassword1 = newPassword.getText();
        String confirmNewPassword1 = confirmNewPassword.getText();

//        userList.findUserPassword(userName1,currentPassword1);

        if (!(newPassword.getText().equals(confirmNewPassword.getText()))) {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setContentText("โปรดกรอกรหัสผ่านให้เหมือนกัน");
            alert.show();
        } else if (newPassword.getText().isEmpty() || confirmNewPassword.getText().isEmpty()) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("โปรดกรอกรหัสผ่าน");
            alert.show();
        } else if (userListDataSource.ChangePassword(userName1,currentPassword1,newPassword1)  ) {
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("เปลี่ยนรหัสผ่านสำเร็จ_____");
            alert.show();

//        } else {
//            user.setPassword(newPassword1);
//            try {
//                alert.setAlertType(Alert.AlertType.INFORMATION);
//                alert.setContentText("เปลี่ยนรหัสผ่านสำเร็จ");
//                alert.show();
//                com.github.saacsos.FXRouter.goTo("admin");
//
//            } catch (IOException e) {
//                System.err.println("ไปที่หน้า admin ไม่ได้");
//                System.err.println("ให้ตรวจสอบการกำหนด route");
//            }
        }

    }
//    public void refreshUser(User user, String password) {
//        userList.removeUser(user.getUsername());
//        user.setPassword(password);
//        userList.addUser(user);
//        userListDataSource.setUserList(userList);
//        userListDataSource.saveData();
//    }
}
