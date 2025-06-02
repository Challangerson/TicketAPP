package ch.challangerson.controller;

import ch.challangerson.object.BaseImplentation;
import ch.challangerson.object.Resource;
import ch.challangerson.object.Type;
import ch.challangerson.object.punishment.Punishment;
import ch.challangerson.object.session.Session;
import ch.challangerson.object.table.TableType;
import ch.challangerson.object.ticket.Ticket;
import ch.challangerson.object.user.User;
import ch.challangerson.util.alert.AlertType;
import ch.challangerson.util.alert.AlertUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserController extends BaseImplentation {

    @FXML
    private ImageView profileImageView;

    @FXML
    private Label data, address;

    @FXML
    private TableView<Object> parkingTableView;

    @FXML
    private Button add,remove, extend;


    private User user;

    private boolean isAddingOpen = false, isRemovingOpen = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }




    private void switchTable(TableType tableType) {

        this.parkingTableView.getColumns().clear();
        this.parkingTableView.getItems().clear();

        switch (tableType) {
            case SESSION -> {

                this.add.setDisable(false);
                this.remove.setDisable(false);
                this.extend.setDisable(false);

                TableColumn<Object, String> idParkinguColumn = new TableColumn<>("parking_id");
                idParkinguColumn.setCellValueFactory(new PropertyValueFactory<>("parking_id"));

                TableColumn<Object, String> czasStartuColumn = new TableColumn<>("session_start");
                czasStartuColumn.setCellValueFactory(new PropertyValueFactory<>("session_start"));

                TableColumn<Object, String> adresColumn = new TableColumn<>("address");
                adresColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

                TableColumn<Object, String> platnyColumn = new TableColumn<>("isPaid");
                platnyColumn.setCellValueFactory(new PropertyValueFactory<>("paid"));

                this.parkingTableView.getColumns().addAll(idParkinguColumn, czasStartuColumn, adresColumn, platnyColumn);
                ObservableList<Session> parkinguList = FXCollections.observableArrayList(user.getSessionList());

                parkingTableView.setItems((ObservableList<Object>) (ObservableList<?>) parkinguList);

            }

            case PUNISHMENTS -> {
                this.add.setDisable(true);
                this.remove.setDisable(true);
                this.extend.setDisable(true);


                TableColumn<Object, String> idPunishmentColumn = new TableColumn<>("punishment_id");
                idPunishmentColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

                TableColumn<Object, String> pricePunishment = new TableColumn<>("price");
                pricePunishment.setCellValueFactory(new PropertyValueFactory<>("price"));

                TableColumn<Object, String> responsePunishment = new TableColumn<>("response");
                responsePunishment.setCellValueFactory(new PropertyValueFactory<>("response"));

                TableColumn<Object, String> datePunishment = new TableColumn<>("ticket");
                datePunishment.setCellValueFactory(new PropertyValueFactory<>("ticket"));

                this.parkingTableView.getColumns().addAll(idPunishmentColumn, pricePunishment, responsePunishment, datePunishment);
                ObservableList<Punishment> punishmentList = FXCollections.observableArrayList(user.getPunishmentList());

                parkingTableView.setItems((ObservableList<Object>) (ObservableList<?>) punishmentList);
            }

            case CONCLUSIONS -> {
                this.add.setDisable(true);
                this.remove.setDisable(true);
                this.extend.setDisable(true);

                TableColumn<Object, String> idConclusionColumn = new TableColumn<>("ticket");
                idConclusionColumn.setCellValueFactory(new PropertyValueFactory<>("id_ticket"));

                TableColumn<Object, String> priceConclusionColumn = new TableColumn<>("checked");
                priceConclusionColumn.setCellValueFactory(new PropertyValueFactory<>("checked"));

                TableColumn<Object, String> responseConclusionColumn = new TableColumn<>("response");
                responseConclusionColumn.setCellValueFactory(new PropertyValueFactory<>("response"));

                TableColumn<Object, String> responseTextColumn = new TableColumn<>("response_text");
                responseTextColumn.setCellValueFactory(new PropertyValueFactory<>("response_text"));

                this.parkingTableView.getColumns().addAll(idConclusionColumn, priceConclusionColumn, responseConclusionColumn, responseTextColumn);

                ObservableList<Ticket> tickets = FXCollections.observableArrayList(user.getTicketsList());
                parkingTableView.setItems((ObservableList<Object>) (ObservableList<?>) tickets);

            }
        }
    }


    public void setUserInformation(User user) {
        this.user = user;

        data.setText(user.getFirstName() + " " + user.getLastName());
        address.setText(user.getAdress());


        Image image = new Image(getClass().getResourceAsStream(user.getPhoto()));
        profileImageView.setImage(image);

        this.sessionManager.loadSessionForUser(user);

        this.switchTable(TableType.SESSION);

    }

    public void refreshParkingTable(User user) {
        parkingTableView.setItems(FXCollections.observableArrayList(user.getSessionList()));
    }

    @FXML
    public void addSession() {

        if(this.isAddingOpen) {
            AlertUtil.showAlert(AlertType.ERROR, "Close window of adding");
            return;
        }

        try {
            Stage stage = new Stage();
            stage.setTitle("Add Session");
            FXMLLoader fxmlLoader = new FXMLLoader(Resource.getResourceName(Type.ADD_PANEL));
            Parent root = fxmlLoader.load();
            AddSessionPanelController controller = fxmlLoader.getController();
            controller.setCars(user, this);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

            stage.setOnCloseRequest(_ -> this.isAddingOpen = false);

            this.isAddingOpen = true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setAddingOpen(boolean addingOpen) {
        isAddingOpen = addingOpen;
    }

    public void setSessions() {
        this.switchTable(TableType.SESSION);
    }

    public void setPunishments() {
        this.switchTable(TableType.PUNISHMENTS);
    }

    public void setWnioski() {
        this.switchTable(TableType.CONCLUSIONS);
    }


    public void setRemovingOpen(boolean removingOpen) {
        isRemovingOpen = removingOpen;
    }


    public void removeSession() {

        if(this.isRemovingOpen) {
            AlertUtil.showAlert(AlertType.ERROR, "Close window of removing");
            return;
        }

        try {
            Stage stage = new Stage();
            stage.setTitle("Remove Session");
            FXMLLoader fxmlLoader = new FXMLLoader(Resource.getResourceName(Type.REMOVE_PANEL));
            Parent root = fxmlLoader.load();
            RemoveSessionController controller = fxmlLoader.getController();
            controller.setUser(user, this);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

            stage.setOnCloseRequest(_ -> this.isRemovingOpen = false);

            this.isRemovingOpen = true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
