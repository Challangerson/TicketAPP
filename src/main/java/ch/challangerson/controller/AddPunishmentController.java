package ch.challangerson.controller;

import ch.challangerson.object.BaseImplentation;
import ch.challangerson.object.punishment.Punishment;
import ch.challangerson.object.ticket.Ticket;
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

public class AddPunishmentController extends BaseImplentation {

    private User user;

    @FXML
    private TextField priceField, ticketField, responseField;

    @FXML
    private CheckBox paidCheckBox, recidivismCheckBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialization if needed
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void savePunishment() {
        if (priceField.getText().isEmpty() || ticketField.getText().isEmpty() ||
                responseField.getText().isEmpty()) {
            AlertUtil.showAlert(AlertType.ERROR, "All fields must be filled out.");
            return;
        }

        if (!priceField.getText().matches("\\d+")) {
            AlertUtil.showAlert(AlertType.ERROR, "Price must be a number.");
            return;
        }

        if (!ticketField.getText().matches("\\d+")) {
            AlertUtil.showAlert(AlertType.ERROR, "Ticket number must be a number.");
            return;
        }

        for(Ticket ticket : user.getTicketsList()) {
            if (ticket.getId_ticket() == Integer.parseInt(ticketField.getText())) {
                AlertUtil.showAlert(AlertType.ERROR, "This ticket number already exists for the user.");
                return;
            }
        }

        this.punishmentManager.addPunishment(user, new Punishment(
                user.getPunishmentList() == null ? 0 : user.getPunishmentList().size() + 1,
                user.getId(),
                Integer.parseInt(priceField.getText()),
                Integer.parseInt(ticketField.getText()),
                responseField.getText(),
                paidCheckBox.isSelected(),
                recidivismCheckBox.isSelected()
        ));

        AlertUtil.showAlert(AlertType.INFORMATION, "Punishment saved successfully.");

        Stage stage = (Stage) this.responseField.getScene().getWindow();
        stage.close();
    }

    public void cancel() {
        Stage stage = (Stage) this.responseField.getScene().getWindow();
        stage.close();
    }
}
