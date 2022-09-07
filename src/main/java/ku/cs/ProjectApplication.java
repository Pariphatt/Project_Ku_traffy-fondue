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
//        Image image = new Image("E:\\Cs211Project\\project211-pedpap\\src\\main\\resources\\ku\\cs\\images\\kulogo3.png");
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
        FXRouter.when("report_list", packageStr+"report_list.fxml");
        FXRouter.when("welcome_staff", packageStr+"welcome_staff.fxml");
        FXRouter.when("my_account", packageStr+"my_account.fxml");
    }
    public static void main(String[] args) {
        launch();
    }
}
