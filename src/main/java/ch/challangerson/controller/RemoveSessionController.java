package ch.challangerson.controller;

import ch.challangerson.object.BaseImplentation;
import ch.challangerson.object.parking.Parking;
import ch.challangerson.object.session.Session;
import ch.challangerson.object.user.User;
import ch.challangerson.util.alert.AlertType;
import ch.challangerson.util.alert.AlertUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RemoveSessionController extends BaseImplentation {

    @FXML
    private ComboBox<String> adressSelected;

    private User user;

    private UserController userController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void setUser(User user, UserController userController) {
        this.user = user;
        this.userController = userController;



        ObservableList<String> adresses = FXCollections.observableArrayList();
        adresses.addAll(this.user.getSessionList().stream().map(Session::getAddress).toList());

        this.adressSelected.setItems(adresses);
    }

    private void setOpened() {
        this.userController.setRemovingOpen(false);
    }

    public void removeSession() {

        String adress = this.adressSelected.getValue();

        if(adress.isEmpty()) {
            AlertUtil.showAlert(AlertType.ERROR, "No adress selected");
            return;
        }

        List<Session> sessions = user.getSessionList();

        Session session = null;

        for(Session s : sessions) {
            if(s.getAddress().equals(adress)) {
                session = s;
            }
        }

        this.sessionManager.removeSession(user, session);
        this.userController.refreshParkingTable(user);
        this.setOpened();
        this.closeForm();
        AlertUtil.showAlert(AlertType.INFORMATION, "Session removed");
    }


    public void closeForm() {
        Stage stage = (Stage) this.adressSelected.getScene().getWindow();
        this.setOpened();
        stage.close();
    }
}
