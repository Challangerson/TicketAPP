package ch.challangerson.controller;

import ch.challangerson.object.BaseImplentation;
import ch.challangerson.object.car.Car;
import ch.challangerson.object.user.User;
import ch.challangerson.util.alert.AlertType;
import ch.challangerson.util.alert.AlertUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CarController extends BaseImplentation {

    private User user;

    @FXML
    private TextField yearField, vinField, brandField, modelField, registrationField;

    @FXML
    private CheckBox isUbezpieczony;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setUser(User user) {
        this.user = user;
    }

    public void saveCar() {
        if( yearField.getText().isEmpty() || vinField.getText().isEmpty() ||
            brandField.getText().isEmpty() || modelField.getText().isEmpty() ||
            registrationField.getText().isEmpty()) {
            AlertUtil.showAlert(AlertType.ERROR, "All fields must be filled out.");
            return;
        }

        if( !yearField.getText().matches("\\d{4}") ) {
            AlertUtil.showAlert(AlertType.ERROR, "Year must be a 4-digit number.");
            return;
        }

        if( !vinField.getText().matches("[A-HJ-NPR-Z0-9]{17}") ) {
            AlertUtil.showAlert(AlertType.ERROR, "VIN must be a 17-character alphanumeric string (excluding I, O, Q).");
            return;
        }

        if( !registrationField.getText().matches("^[A-Z]{3}[0-9]{4}[A-Z]{1}$") ) {
            AlertUtil.showAlert(AlertType.ERROR, "Registration must be in the format XXXXXXXX.");
            return;
        }

        this.carManager.addCar(user, new Car(
            user.getCars() == null ? 0 : user.getCars().size() + 1,
            user.getId(),
            Integer.parseInt(yearField.getText()),
            vinField.getText(),
            brandField.getText(),
            modelField.getText(),
            registrationField.getText(),
            isUbezpieczony.isSelected()
        ));

        AlertUtil.showAlert(AlertType.INFORMATION, "Car saved successfully.");

        Stage stage = (Stage) this.modelField.getScene().getWindow();
        stage.close();

    }

    public void cancel() {
        Stage stage = (Stage) this.modelField.getScene().getWindow();
        stage.close();
    }
}
