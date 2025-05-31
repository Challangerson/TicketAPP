package ch.challangerson.util.alert;

import ch.challangerson.object.Resource;
import ch.challangerson.object.Type;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;

public class AlertUtil {

    public static void showAlert(AlertType type, String message) {
        switch (type) {
            case CONFIRMATION:
                System.out.println("INFO: " + message);
                break;
            case INFORMATION:
                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setTitle("Information");
                info.setContentText(message);

                DialogPane infoDialogPane = info.getDialogPane();

                infoDialogPane.getStylesheets().add(Resource.getResourceName(Type.ALERT_CSS).toExternalForm());
                infoDialogPane.getStyleClass().add("custom-alert");

                info.showAndWait();
                break;
            case ERROR:
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Error");
                error.setContentText(message);

                DialogPane errorDialogPane = error.getDialogPane();

                errorDialogPane.getStylesheets().add(Resource.getResourceName(Type.ALERT_CSS).toExternalForm());
                errorDialogPane.getStyleClass().add("custom-alert");

                error.showAndWait();

                break;
            default:
                throw new IllegalArgumentException("Unknown alert type: " + type);
        }
    }
}
