package ku.cs;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import com.github.saacsos.FXRouter;
public class ProjectApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
       FXRouter.bind(this, stage, "Register", 800, 600);
        configRoute();
        FXRouter.goTo("Register");
    }
    private static void configRoute() {
        String packageStr = "ku/cs/";
        FXRouter.when("Register",packageStr + "Register.fxml");
    }

    public static void main(String[] args) {
        launch();
    }
}
