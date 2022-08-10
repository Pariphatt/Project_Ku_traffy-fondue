package ku.cs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import com.github.saacsos.FXRouter;

public class ProjectApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        com.github.saacsos.FXRouter.bind(this, stage, "home", 800, 600);
        configRoute();
        com.github.saacsos.FXRouter.goTo("welcome_page");
    }
    private static void configRoute() {
        String packageStr = "ku/cs/";
        com.github.saacsos.FXRouter.when("welcome_page", packageStr+"welcome_page.fxml");
        com.github.saacsos.FXRouter.when("report_list", packageStr+"report_list.fxml");
    }


    public static void main(String[] args) {
        launch();
    }
}
