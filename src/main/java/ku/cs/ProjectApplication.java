package ku.cs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.github.saacsos.FXRouter;
import java.io.IOException;

public class ProjectApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(ProjectApplication.class.getResource("home.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
//        stage.setTitle("Project-Name");
//        stage.setScene(scene);
//        stage.show();
        FXRouter.bind(this, stage, "ระบบร้องเรียน",800,600);
        configRoute();
        FXRouter.goTo("home");
    }
    private static void configRoute() {
        String packageStr = "ku/cs/";
        FXRouter.when("home", packageStr+"home.fxml");
        FXRouter.when("credit", packageStr + "credit.fxml");
        FXRouter.when("guidebook", packageStr + "guidebook.fxml");

    }
    public static void main(String[] args) {
        launch();
    }
}
