package ch.challangerson.menu;

import ch.challangerson.object.user.User;
import ch.challangerson.object.user.UserManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    @FXML
    private TextField loginField, passwordField;

    @FXML
    private Label information;



    private final UserManager userManager;

    public MenuController() {
        userManager = new UserManager();
    }

    @FXML
    public void onLogIn() {
        User user = userManager.getUser(this.loginField.getText());

        if(user == null) {
            this.showError("Unknown user!");
            return;
        }

        long lastLogin = user.getLastLogin();

        if(lastLogin >= System.currentTimeMillis() - 1000 * 3) {
            this.showError("Wait before trying again!");
            return;
        }

        user.setLastLogin(System.currentTimeMillis());

        if(!user.getPassword().equals(this.passwordField.getText())) {
            this.showError("Wrong password!");
            return;
        }


        this.showSuccess("Logged in successfully!");
        this.loginField.getScene().getWindow().hide();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("main.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setTitle("Menu");
        stage.setResizable(false);
//        scene.getStylesheets().add(getClass().getResource("menu.css").toExternalForm());
        stage.setScene(scene);
        stage.setUserData(user);
        stage.show();

//        new MainController().initData(user);

    }


    private void showError(String message) {
       this.information.setText(message);
       this.information.setStyle("-fx-text-fill: red");
    }

    private void showSuccess(String message) {
        this.information.setText(message);
        this.information.setStyle("-fx-text-fill: green");
    }
}
