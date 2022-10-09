package ku.cs.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import ku.cs.models.account.Account;
import ku.cs.models.account.AccountList;
import ku.cs.models.account.StaffAccount;
import ku.cs.models.account.UserAccount;
import ku.cs.services.AccountListDataSource;
import ku.cs.services.DataSource;
import ku.cs.services.ThemeMode;

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

    @FXML private Button mode;
    public static boolean isLightMode = true;

    @FXML
    public void initialize() {
        alert = new Alert(Alert.AlertType.NONE);
        dataSource = new AccountListDataSource("assets", "accounts.csv");
        accountList = dataSource.readData();
//        ThemeMode.setThemeMode(pane);

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
                            AccountListDataSource <StaffAccount> staffAccountAccountListDataSource = new
                                    AccountListDataSource<>("assets","log.csv");
                            staffAccountAccountListDataSource.log((StaffAccount) account);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    } else if (account instanceof UserAccount) {
                        try {
                            com.github.saacsos.FXRouter.goTo("welcome_page", account.getUsername());
                            AccountListDataSource <UserAccount> userAccountAccountListDataSource = new
                                    AccountListDataSource<>("assets","log.csv");
                           userAccountAccountListDataSource.log((UserAccount) account);
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
