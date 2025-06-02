package ch.challangerson;

import ch.challangerson.menu.MainMenu;
import ch.challangerson.object.Resource;
import javafx.application.Application;

public class Runner {

    public static void main(String[] args) {
        // Launch the JavaFX application
        new Resource();
        Application.launch(MainMenu.class, args);

//        System.out.println("Starting application... " + Resource.getResourceName(Type.MAIN));

    }
}
