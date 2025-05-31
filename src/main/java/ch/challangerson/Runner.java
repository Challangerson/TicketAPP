package ch.challangerson;

import ch.challangerson.menu.MainMenu;
import ch.challangerson.object.Resource;
import ch.challangerson.object.Type;
import ch.challangerson.util.SQLUtil;
import javafx.application.Application;

public class Runner {

    public static void main(String[] args) {
        // Launch the JavaFX application
        new Resource();
        Application.launch(MainMenu.class, args);
//        new SQLUtil(
//                "mysql.titanaxe.com",
//                3306,
//                "srv310626",
//                "srv310626",
//                "ezjV3sX8"
//        );

//        System.out.println("Starting application... " + Resource.getResourceName(Type.MAIN));

    }
}
