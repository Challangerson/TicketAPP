package ch.challangerson.controller;

import ch.challangerson.object.Resource;
import ch.challangerson.object.Type;
import ch.challangerson.util.alert.AlertType;
import ch.challangerson.util.alert.AlertUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private ImageView logoImageView;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        InputStream stream = getClass().getResourceAsStream("/ch/challangerson/view/image/parking-ticket.png");

        try {
            if (stream == null) {
                System.err.println("Image resource not found!");
                return;
            }
        } catch (Exception e) {
            System.err.println("Error loading image: " + e.getMessage());
            return;
        }

        Image image = new Image(stream);
        logoImageView.setImage(image);
    }

    @FXML
    public void onLoginClicked() {
        String login = this.loginField.getText();
        String password = this.passwordField.getText();

        if (login.isEmpty() || password.isEmpty()) {
            AlertUtil.showAlert(AlertType.ERROR, "Login and password cannot be empty!");
            return;
        }



        AlertUtil.showAlert(AlertType.INFORMATION, "Login: " + login + " : " + "Password: " + password);
    }
}
