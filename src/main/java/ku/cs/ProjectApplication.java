package ku.cs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import com.github.saacsos.FXRouter;
import java.io.IOException;
import com.github.saacsos.FXRouter;

public class ProjectApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setFullScreen(false);
        stage.setResizable(false);
//        Image image = new Image(".\\src\\main\\resources\\ku\\cs\\images\\kulogo3.png");
//        stage.getIcons().add(image);
        com.github.saacsos.FXRouter.bind(this, stage, "ระบบร้องเรียนมหาวิทยาลัยเกษตรศาสตร์", 800, 600);
        configRoute();
        com.github.saacsos.FXRouter.goTo("home");
    }
    private static void configRoute() {
        String packageStr = "ku/cs/";
        FXRouter.when("home", packageStr+"home.fxml");
        FXRouter.when("login", packageStr+"login.fxml");
        FXRouter.when("Register", packageStr+"Register.fxml");
        FXRouter.when("credit", packageStr + "credit.fxml");
        FXRouter.when("guidebook", packageStr + "guidebook.fxml");
        FXRouter.when("welcome_page", packageStr+"welcome_page.fxml");
        FXRouter.when("addReport_page", packageStr+"addReport_page.fxml");
        FXRouter.when("allComplaint_page", packageStr+"allComplaint_page.fxml");
        FXRouter.when("empty2_page", packageStr+"empty2_page.fxml");
        FXRouter.when("welcome_staff", packageStr+"welcome_staff.fxml");
        FXRouter.when("my_account", packageStr+"my_account.fxml");
        FXRouter.when("admin", packageStr+"admin.fxml");
        FXRouter.when("change_password", packageStr+"change_password.fxml");
        FXRouter.when("report_user", packageStr+"report_user.fxml");
        FXRouter.when("staff_homepage", packageStr+"staff_homepage.fxml");
        FXRouter.when("manage_reports", packageStr+"manage_reports.fxml");
        FXRouter.when("RegisterStaff", packageStr+"RegisterStaff.fxml");
    }
    public static void main(String[] args) {
        launch();
    }
}
