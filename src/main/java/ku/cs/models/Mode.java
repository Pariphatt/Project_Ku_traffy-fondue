package ku.cs.models;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import ku.cs.controllers.HomePageController;

public class Mode {
    public static void setMode(AnchorPane parent) {
        if (HomePageController.isLightMode) {
            setLightMode(parent);
        } else {
            setDarkMode(parent);
        }
    }

    public static void setLightMode(AnchorPane parent) {
        parent.getStylesheets().clear();
        parent.getStylesheets().add(Mode.class.getResource("/ku/cs/styles/style.css").toExternalForm());
    }

    public static void setDarkMode(AnchorPane parent) {
        parent.getStylesheets().clear();
        parent.getStylesheets().add(Mode.class.getResource("/ku/cs/styles/darkMode.css").toExternalForm());
    }

    public static void setLightMode(AnchorPane parent, Button mode) {
        setLightMode(parent);
        mode.setText("Dark Mode");
    }

    public static void setDarkMode(AnchorPane parent, Button mode) {
        setDarkMode(parent);
        mode.setText("Light Mode");
    }
}
