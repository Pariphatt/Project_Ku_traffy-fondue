package ku.cs.controllers;
import animatefx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import ku.cs.models.Mode;
import ku.cs.models.account.Account;
import ku.cs.models.account.AccountList;
import ku.cs.models.account.StaffAccount;
import ku.cs.models.account.UserAccount;
import ku.cs.services.AccountListDataSource;
import ku.cs.services.DataSource;

import java.io.IOException;

public class LoginController  {
    @FXML private TextField username;
   @FXML private PasswordField password;
    @FXML private ImageView iconFileImage;
    @FXML private Label loginMessageLabel;
    private Alert alert;
    //login button
    //กดไปหน้าหลักได้ก่อนยังไม่ได้ทำเงื่อนไข

    private DataSource<AccountList> dataSource;
    private AccountList accountList;
    @FXML private AnchorPane pane;



    @FXML
    public void initialize() {
        alert = new Alert(Alert.AlertType.NONE);
        dataSource = new AccountListDataSource("assets", "accounts.csv");
        accountList = dataSource.readData();
        Mode.setMode(pane);
        new FadeIn(pane).play();


    }
    public void handleLoginButton(ActionEvent actionEvent){

       if (username.getText().isEmpty() &&
               password.getText().isEmpty()){
           alert.setAlertType(Alert.AlertType.WARNING);
           alert.setContentText("Please enter username and password");
           alert.show();
       }

       else {
            Account account = accountList.findUser(username.getText());
            if (account == null) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("username is incorrect.");
                alert.show();
            } else {
                if (!account.isPassword(password.getText())) {
                    account.loginFailed();
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setContentText("password is incorrect.");
                    alert.show();
                }

                else {
                    account.loginPass();
                    dataSource.writeData(accountList);
                    if (account instanceof StaffAccount) {
                        try {
                            com.github.saacsos.FXRouter.goTo("staff_homepage", account.getUsername());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    } else if (account instanceof UserAccount) {
                        try {
                            com.github.saacsos.FXRouter.goTo("welcome_page", account.getUsername());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        try {
                            com.github.saacsos.FXRouter.goTo("admin", account.getUsername());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }

                }
            }
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
