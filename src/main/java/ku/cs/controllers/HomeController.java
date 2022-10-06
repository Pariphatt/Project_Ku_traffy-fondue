package ku.cs.controllers;
import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import ku.cs.models.account.Account;
import ku.cs.models.account.AccountList;
import ku.cs.services.AccountListDataSource;
import ku.cs.services.DataSource;

import java.io.IOException;

public class HomeController {
    private DataSource<AccountList> userListDataSource;
    private AccountList userList;
    private Account account;


    @FXML
    public void handleCreditButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("credit");
        } catch (IOException e) {
            System.err.println("ไปที7หน้า profile ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
            e.printStackTrace(); //ดูว่าerorตรงไหนบ้าง
        }
    }
    @FXML
    public void handleguidebookButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("guidebook");
        } catch (IOException e) {
            System.err.println("ไปที7หน้า guidebook ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
            e.printStackTrace(); //ดูว่าerorตรงไหนบ้าง
        }
    }
    @FXML
    public void handleRegisterButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("Register",account.getUsername());
        } catch (IOException e) {
            System.err.println("ไปที7หน้า login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
            e.printStackTrace(); //ดูว่าerorตรงไหนบ้าง
        }
    }
    @FXML
    public void handleSignInButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("login");
        } catch (IOException e) {
            System.err.println("ไปที7หน้า login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
            e.printStackTrace(); //ดูว่าerorตรงไหนบ้าง
        }
    }
}
