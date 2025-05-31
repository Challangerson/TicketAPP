package ch.challangerson.menu;

import ch.challangerson.object.Resource;
import ch.challangerson.object.Type;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainMenu extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("Starting Main Menu...");
        System.out.println("Loading FXML file: " + Resource.getResourceName(Type.MAIN));
        FXMLLoader fxmlLoader = new FXMLLoader(Resource.getResourceName(Type.MAIN));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }

}
