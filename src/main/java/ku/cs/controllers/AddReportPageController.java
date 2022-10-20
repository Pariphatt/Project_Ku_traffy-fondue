package ku.cs.controllers;

import animatefx.animation.FadeIn;
import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import ku.cs.models.Mode;
import ku.cs.models.accounts.Account;
import ku.cs.models.accounts.AccountList;
import ku.cs.models.reports.Report;

import ku.cs.models.reports.ReportList;
import ku.cs.services.DataSource;
import ku.cs.services.ReportFIleDataSource;

import ku.cs.services.AccountListDataSource;


import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddReportPageController {
    @FXML private Label userLabel;
    @FXML private TextField topicTextField;
    @FXML private TextArea detailTextArea;
    @FXML private TextArea specificTopicTextArea;
    @FXML private Label specificTopicLabel;
    private Alert alert;
    @FXML ChoiceBox typeChoiceBox;

    private ReportList reportList;
    private DataSource<ReportList> dataSource;

    @FXML private ImageView userShow;
    private Account account;
    private AccountListDataSource userListDataSource;
    private AccountList userList;
    private    String agency;
    @FXML private AnchorPane pane;

    @FXML
    public void initialize() {
        dataSource = new ReportFIleDataSource("assets","reports.csv");
        reportList = dataSource.readData();
        alert = new Alert(Alert.AlertType.NONE);

        File imagePic = new File("imagesAvatar/profile-user.png");
        userShow.setImage(new Image(imagePic.toURI().toString()));
        userListDataSource = new AccountListDataSource("assets", "accounts.csv");
        userList = userListDataSource.readData();
        account = userList.findUser((String) FXRouter.getData());
        userShow.setImage(new Image(new File("imagesAvatar/" + account.getPicPath()).toURI().toString()));

        userLabel.setText(account.getUsername());
        Mode.setMode(pane);
        new FadeIn(pane).play();

        setFirstComePage();
        showType();
    }

    private void setFirstComePage(){
        specificTopicLabel.setText("ทะเบียนรถ");
    }

    public void showType(){
        typeChoiceBox.getItems().addAll(type);
        typeChoiceBox.getSelectionModel().selectFirst();
        typeChoiceBox.setOnAction(this::handleShowType);
    }

    private void handleShowType(Event event) {
        handleShowLabel();
    }
    private void handleShowLabel(){
        if (typeChoiceBox.getValue().equals("ยานพาหนะ")){
            specificTopicLabel.setText("ทะเบียนรถ");
        } else if (typeChoiceBox.getValue().equals("อาคารสถานที่และความปลอดภัย")){
            specificTopicLabel.setText("แผนก");
        } else if (typeChoiceBox.getValue().equals("IT หรือ ปัญหาด้านคอมพิวเตอร์")){
            specificTopicLabel.setText("ซอฟต์เเวร์หรือฮาร์ดแวร์");
        } else if (typeChoiceBox.getValue().equals("กิจกรรมนิสิต")){
            specificTopicLabel.setText("ชื่อกิจกกรม");
        } else if (typeChoiceBox.getValue().equals("ทรัพย์สินในมหาวิทยาลัย")){
            specificTopicLabel.setText("มูลค่า");
        } else if (typeChoiceBox.getValue().equals("อื่นๆ")){
            specificTopicLabel.setText("อื่นๆ");
        }
    }
    public void handleSubmitButton(ActionEvent actionEvent){
        if (topicTextField.getText().equals("") || detailTextArea.getText().equals("") || specificTopicTextArea.getText().equals("")) {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setContentText("โปรดกรอกข้อมูลให้ครบทุกช่อง");
            alert.show();
        } else {
            String topic = topicTextField.getText();
            String detail = detailTextArea.getText();
            String specificTopic = specificTopicTextArea.getText();
            if (reportList.isExistTopic(topic)){
                alert.setAlertType(Alert.AlertType.WARNING);
                alert.setContentText("ไม่สามารถตั้งหัวข้อซ้ำกันได้");
                alert.show();
            } else {
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
                Report report = new Report(topic,detail,account.getUsername(), (String) typeChoiceBox.getValue(),agency,dtf.format(now),specificTopic);
                reportList.addReport(report);
                dataSource.writeData(reportList);
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("เเจ้งเรื่องร้องเรียนสำเร็จ");
                alert.show();

                try {
                    com.github.saacsos.FXRouter.goTo("add_report_page", account.getUsername());
                } catch (IOException e) {
                    System.err.println(e);
                }
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
            com.github.saacsos.FXRouter.goTo("add_report_page", account.getUsername());
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    @FXML
    public void handleMyReportButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("my_report_page", account.getUsername());
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    @FXML
    public void handleLogOutButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("login_page");
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    @FXML
    public void handleSettingButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("change_password_page",account.getUsername());
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
