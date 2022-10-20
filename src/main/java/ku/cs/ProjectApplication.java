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

        stage.getIcons().add(new Image("https://play-lh.googleusercontent.com/Xv4Ze-dmrbnMgyP_2HxCrGaV5VJfe5vAk3Sekza42K1EON2J5sEVwXI8-0cB6Rmtgw"));
        com.github.saacsos.FXRouter.bind(this, stage, "ระบบร้องเรียนมหาวิทยาลัยเกษตรศาสตร์", 800, 600);
        configRoute();
        com.github.saacsos.FXRouter.goTo("home_page");
    }
    private static void configRoute() {
        String packageStr = "ku/cs/";
        FXRouter.when("home_page", packageStr+"home_page.fxml");
        FXRouter.when("login_page", packageStr+"login_page.fxml");
        FXRouter.when("register_page", packageStr+"register_page.fxml");
        FXRouter.when("credit_page", packageStr + "credit_page.fxml");
        FXRouter.when("guide_book_page", packageStr + "guide_book_page.fxml");
        FXRouter.when("welcome_page", packageStr+"welcome_page.fxml");
        FXRouter.when("add_report_page", packageStr+"add_report_page.fxml");
        FXRouter.when("my_report_page", packageStr+"my_report_page.fxml");
        FXRouter.when("admin_page", packageStr+"admin_page.fxml");
        FXRouter.when("change_password_page", packageStr+"change_password_page.fxml");
        FXRouter.when("report_user_page", packageStr+"report_user_page.fxml");
        FXRouter.when("staff_page", packageStr+"staff_page.fxml");
        FXRouter.when("register_staff_page", packageStr+"register_staff_page.fxml");
        FXRouter.when("manage_reports_page", packageStr+"manage_reports_page.fxml");
        FXRouter.when("report_complaint_page", packageStr+"report_complaint_page.fxml");
        FXRouter.when("request_unban_page", packageStr+"request_unban_page.fxml");
        FXRouter.when("banned_user_page", packageStr+"banned_user_page.fxml");
    }
    public static void main(String[] args) {
        launch();
    }
}
