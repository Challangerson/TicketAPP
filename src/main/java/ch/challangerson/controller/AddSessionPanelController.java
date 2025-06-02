package ch.challangerson.controller;

import ch.challangerson.object.BaseImplentation;
import ch.challangerson.object.car.Car;
import ch.challangerson.object.parking.Parking;
import ch.challangerson.object.session.Session;
import ch.challangerson.object.user.User;
import ch.challangerson.util.alert.AlertType;
import ch.challangerson.util.alert.AlertUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class AddSessionPanelController extends BaseImplentation {


    @FXML
    private ComboBox<String> adressSelected;

    @FXML
    private ComboBox<String> carSelected;

    private User user;

    private UserController userController;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> adresses = FXCollections.observableArrayList();
        adresses.addAll(this.getParkingManager().getParkings().values().stream().map(Parking::getAdress).toList());

        this.adressSelected.setItems(adresses);
    }

    public void setCars(User user, UserController userController) {
        ObservableList<String> cars = FXCollections.observableArrayList();
        cars.addAll(user.getCars().stream().map(Car::getRegistration).toList());
        this.carSelected.setItems(cars);

        this.user = user;
        this.userController = userController;
    }


    private void setOpened() {
        this.userController.setAddingOpen(false);
    }


    public void closeForm() {
        Stage stage = (Stage) this.adressSelected.getScene().getWindow();
        this.setOpened();
        stage.close();
    }



    public void startSession() {

        String adress = this.adressSelected.getValue();
        String car = this.carSelected.getValue();

        if (adress.isEmpty() || car.isEmpty()) {
            AlertUtil.showAlert(AlertType.ERROR, "Select car and adress");
            return;
        }


        List<Session> sessions = user.getSessionList();

        for(Session session : sessions) {
            if(session.getAddress().equals(adress)) {
                AlertUtil.showAlert(AlertType.ERROR, "Session already exists");
                return;
            }
        }

        Parking parking = this.getParkingManager().getParking(adress);

        if(parking == null) {
            AlertUtil.showAlert(AlertType.ERROR, "Parking not found, send this to administrator");
            return;
        }

        String time = new SimpleDateFormat("HH:mm:00").format(new Date());

        Session session = new Session(
                parking.getId(),
                parking.isPaid(),
                time,
                parking.getAdress(),
                user.getId()
        );

//        user.getSessionList().add(session);
        this.sessionManager.addSession(user, session);
        this.setOpened();
        this.userController.refreshParkingTable(user);
        this.closeForm();

        AlertUtil.showAlert(AlertType.INFORMATION, "Session created");

    }
}
