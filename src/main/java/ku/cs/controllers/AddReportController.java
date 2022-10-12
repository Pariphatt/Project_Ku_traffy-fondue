package ku.cs.controllers;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import ku.cs.models.account.Account;
import ku.cs.models.account.AccountList;
import ku.cs.models.reports.Report;

import ku.cs.models.reports.ReportList;
import ku.cs.services.DataSource;
import ku.cs.services.ReportFIleDataSource;

import ku.cs.services.AccountListDataSource;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddReportController {
    @FXML private Label userLabel;
    private Report report;
    @FXML private TextField topicTextField;
    @FXML private TextArea detailTextArea;
    private Alert alert;
    @FXML ChoiceBox typeChoiceBox;
    private String Path;
    private ImageView imageView;
    private String pathImage;
    private BufferedImage pic = null;
    private ReportList reportList;
    private DataSource<ReportList> dataSource;

    @FXML private ImageView userShow;
    private Account account;
    private AccountListDataSource userListDataSource;
    private AccountList userList;
    private    String agency;
    private String userName;
    private String reportTime;

    @FXML
    public void initialize() {
        dataSource = new ReportFIleDataSource("assets","reports.csv");
        reportList = dataSource.readData();
        alert = new Alert(Alert.AlertType.NONE);
        typeChoiceBox.getItems().addAll(type);

        File imagePic = new File("imagesAvatar/profile-user.png");
        userShow.setImage(new Image(imagePic.toURI().toString()));
        userListDataSource = new AccountListDataSource("assets", "accounts.csv");
        userList = userListDataSource.readData();
        account = userList.findUser((String) FXRouter.getData());
        userShow.setImage(new Image(new File("imagesAvatar/" + account.getPicPath()).toURI().toString()));

        userLabel.setText(account.getUsername());
    }

    public void handleSubmitButton(ActionEvent actionEvent){
        if (topicTextField.getText().equals("") || detailTextArea.getText().equals("")) {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setContentText("โปรดกรอกข้อมูลให้ครบทุกช่อง");
            alert.show();
        } else {
            String topic = topicTextField.getText();
            String detail = detailTextArea.getText();
            if (typeChoiceBox.getValue().equals("ยานพาหนะ") ){
                agency = "กองยานพาหนะ";
            }
            else if (typeChoiceBox.getValue().equals("อาคารสถานที่และความปลอดภัย") ){
                agency = "อาคารและสถานที่";
            }
            else if (typeChoiceBox.getValue().equals("IT หรือ ปัญหาด้านคอมพิวเตอร์") ){
                agency = "สำนักบริการคอมพิวเตอร์";
            }
            else if (typeChoiceBox.getValue().equals("กิจกรรมนิสิต") ){
                agency = "กองกิจการนิสิต";
            }
            else if (typeChoiceBox.getValue().equals("ทรัพย์สินในมหาวิทยาลัย") ){
                agency = "สำนักงานทรัพย์สิน";
            }
            else if (typeChoiceBox.getValue().equals("อื่นๆ") ){
                agency = "สำนักงานอธิการบดี";
            }
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            Report report = new Report(topic,detail,account.getUsername(), (String) typeChoiceBox.getValue(),agency,dtf.format(now));
            reportList.addReport(report);
            dataSource.writeData(reportList);

            try {
                com.github.saacsos.FXRouter.goTo("addReport_page", account.getUsername());
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }


    private String[] type = {"ยานพาหนะ", "อาคารสถานที่และความปลอดภัย" , "IT หรือ ปัญหาด้านคอมพิวเตอร์", "กิจกรรมนิสิต", "ทรัพย์สินในมหาวิทยาลัย", "อื่นๆ"
    };

    @FXML
    public void handleHomeButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("welcome_page", account.getUsername());
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    @FXML
    public void handleAddReportButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("addReport_page", account.getUsername());
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    @FXML
    public void handleMyReportButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("allComplaint_page", account.getUsername());
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    @FXML
    public void handleLogOutButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("login");
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    @FXML
    public void handleSettingButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("change_password",account.getUsername());
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
