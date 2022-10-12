package ku.cs.controllers;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import ku.cs.models.account.Account;
import ku.cs.models.account.AccountList;
import ku.cs.services.AccountListDataSource;

import javax.swing.text.html.ImageView;
import javax.swing.text.html.ListView;
import java.awt.*;
import java.io.IOException;

public class ReportUserController {
    @FXML Label userNameLabel;
    @FXML Label loginAttemptsLabel;
    @FXML Label statusLabel;
    @FXML Label messageLabel;
    @FXML Label reasonUnbanLabel;
    @FXML ListView userReport;
    @FXML ImageView imageView;


    private Account accounts;
    private AccountList accountList;
    private AccountListDataSource userListDataSource;

    @FXML
    public void initialize() {
        accounts = new Account();
        userListDataSource = new AccountListDataSource("assets", "accounts.csv");
        accountList = userListDataSource.readData();
        accounts = accountList.findUser((String) FXRouter.getData());
    }

    @FXML
    void handleBackButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("admin");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า admin ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

}
