package ch.challangerson.controller;

import ch.challangerson.object.BaseImplentation;
import ch.challangerson.object.Resource;
import ch.challangerson.object.Type;
import ch.challangerson.object.role.Role;
import ch.challangerson.object.user.Job;
import ch.challangerson.object.user.User;
import ch.challangerson.object.user.UserManager;
import ch.challangerson.util.StarterFile;
import ch.challangerson.util.alert.AlertType;
import ch.challangerson.util.alert.AlertUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController extends BaseImplentation {

    @FXML
    private ImageView logoImageView;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        StarterFile.createObjects(this);

        Image image = new Image(getClass().getResourceAsStream("/ch/challangerson/view/image/parking-ticket.png"));
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

        User user = this.userManager.getUser(login);

        if (user == null) {
            AlertUtil.showAlert(AlertType.ERROR, "User does not exist or password is incorrect!");
            return;
        }

        if (!user.getPassword().equals(password)) {
            AlertUtil.showAlert(AlertType.ERROR, "User does not exist or password is incorrect!");
            return;
        }

        Job job = user.getJob();


        switch (job) {
            case USER -> this.openPanel(Type.USER, user);
        }


    }



    private void openPanel(Type type, User user) {

        FXMLLoader fxmlLoader = new FXMLLoader(Resource.getResourceName(type));

        try {
            Parent root = fxmlLoader.load();
            UserController userController = fxmlLoader.getController();
            userController.setUserInformation(user);

            Stage stage = (Stage) this.loginField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            AlertUtil.showAlert(AlertType.ERROR, "Panie kolego cos nie dziala, sprawdz logi!");

        }

    }
}
