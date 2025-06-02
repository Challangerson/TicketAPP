package ch.challangerson;

import ch.challangerson.menu.MainMenu;
import ch.challangerson.object.Resource;
import javafx.application.Application;

public class Runner {

    public static void main(String[] args) {
        new Resource();
        Application.launch(MainMenu.class, args);
    }
}
