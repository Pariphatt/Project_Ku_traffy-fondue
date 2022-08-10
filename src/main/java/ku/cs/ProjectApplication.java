package ku.cs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.github.saacsos.FXRouter;
import java.io.IOException;
import com.github.saacsos.FXRouter;

public class ProjectApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
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
        FXRouter.when("report_list", packageStr+"report_list.fxml");
    }
    public static void main(String[] args) {
        launch();
    }
}
