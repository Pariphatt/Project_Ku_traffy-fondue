package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import ku.cs.models.reports.Report;
import ku.cs.models.reports.ReportList;
import ku.cs.services.ReportFIleDataSource;
import ku.cs.services.ThemeMode;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;

public class AddReportController {

    @FXML private TextField topicTextField;
    @FXML private TextArea detailTextArea;
    private Alert alert;

    private String Path;
    private ImageView imageView;
    private BufferedImage pic = null;

    private  ReportFIleDataSource dataSource;

    private  Report report;


    @FXML
    public void initialize() {
        alert = new Alert(Alert.AlertType.NONE);
        dataSource = new ReportFIleDataSource("assets", "reports.csv");
        ReportList report = dataSource.readData();

    }

    public void handleSubmitButton(ActionEvent actionEvent) {
        String topic = topicTextField.getText();
        String detail = detailTextArea.getText();
        Report newReport = new Report(topic,detail);


        topicTextField.clear();
        detailTextArea.clear();




        if (topicTextField.getText().equals("") || detailTextArea.getText().equals("")) {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setContentText("โปรดกรอกข้อมูลให้ครบทุกช่อง");
            alert.show();
        } else {

            try {
                com.github.saacsos.FXRouter.goTo("addReport_page");
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }


    public void handleAddPhoto(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg"));
        File picFile = fileChooser.showOpenDialog(null);
        imageView.setEffect(new DropShadow(20, Color.BLACK));
        if (picFile != null) {
            Path = picFile.getAbsolutePath();
            imageView.setImage(new Image(new File(Path).toURI().toString()));
        }
        try {
            pic = ImageIO.read(picFile);
        } catch (IOException e) {
            System.err.println("Cannot load picture");
        }
    }

    @FXML
    public void handleHomeButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("welcome_page");
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    @FXML
    public void handleAddReportButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("addReport_page");
        } catch (IOException e) {
            System.err.println(e);
        }

    }
    @FXML
    public void handleAllComplaintButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("allComplaint_page");
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
}
