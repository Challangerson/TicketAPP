package ch.challangerson.controller;

import ch.challangerson.object.BaseImplentation;
import ch.challangerson.object.Resource;
import ch.challangerson.object.Type;
import ch.challangerson.object.car.Car;
import ch.challangerson.object.punishment.Punishment;
import ch.challangerson.object.ticket.Ticket;
import ch.challangerson.object.user.User;
import ch.challangerson.util.alert.AlertType;
import ch.challangerson.util.alert.AlertUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminController extends BaseImplentation {

    @FXML private ComboBox<User> userComboBox;
    @FXML private Button loadUserDataButton;

    @FXML private TableView<Punishment> karyTableView;
    @FXML private TableColumn<Punishment, Void> karyActionColumn;
    @FXML private TableColumn<Punishment, Integer> karyIdKaryColumn;
    @FXML private TableColumn<Punishment, Integer> karyIdUzytkownikaColumn;
    @FXML private TableColumn<Punishment, Integer> karyCenaColumn;
    @FXML private TableColumn<Punishment, Boolean> karyOplaconeColumn;
    @FXML private TableColumn<Punishment, Boolean> karyRecydywaColumn;
    @FXML private TableColumn<Punishment, String> karyPowodColumn;
    @FXML private TableColumn<Punishment, Integer> karyZazaleniaIdColumn;



    @FXML private TableView<Ticket> zazaleniaTableView;
    @FXML private TableColumn<Ticket, Void> zazaleniaActionColumn;
    @FXML private TableColumn<Ticket, Integer> zazaleniaIdZazaleniaColumn;
    @FXML private TableColumn<Ticket, Integer> zazaleniaIdUzytkownikaColumn;
    @FXML private TableColumn<Ticket, Boolean> zazaleniaSprawdzoneColumn;
    @FXML private TableColumn<Ticket, Boolean> zazaleniaOdpowiedzColumn; // Maps to Ticket.responseGiven
    @FXML private TableColumn<Ticket, String> zazaleniaPowodOdrzutuColumn; // Maps to Ticket.responseText
    @FXML private TextField zazalenieOdpowiedzTextField;
    @FXML private CheckBox zazalenieSprawdzoneCheckBox;

    @FXML private TableView<Car> pojazdyTableView;
    @FXML private TableColumn<Car, Void> pojazdyActionColumn;
    @FXML private TableColumn<Car, Integer> pojazdyIdPojazduColumn;
    @FXML private TableColumn<Car, Integer> pojazdyIdUzytkownikaColumn;
    @FXML private TableColumn<Car, String> pojazdyVinColumn;
    @FXML private TableColumn<Car, String> pojazdyMarkaColumn;
    @FXML private TableColumn<Car, String> pojazdyModelColumn;
    @FXML private TableColumn<Car, Integer> pojazdyRokProdukcjiColumn;
    @FXML private TableColumn<Car, String> pojazdyNrRejestracyjnyColumn;
    @FXML private TableColumn<Car, Boolean> pojazdyUbezpieczonyColumn;

    private final ObservableList<User> allUsersList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.allUsersList.addAll(this.userManager.getUsers().values());

        userComboBox.setItems(this.allUsersList);



        this.setupKaryTable();
        this.setupZazaleniaTable();
        this.setupPojazdyTable();

        this.setupKaryActionColumn();
        this.setupZazaleniaActionColumn();
        this.setupPojazdyActionColumn();
    }



    private void setupKaryTable() {
        karyIdKaryColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        karyIdUzytkownikaColumn.setCellValueFactory(new PropertyValueFactory<>("id_user"));
        karyCenaColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        karyOplaconeColumn.setCellValueFactory(new PropertyValueFactory<>("paid"));
        karyRecydywaColumn.setCellValueFactory(new PropertyValueFactory<>("recidivism"));
        karyPowodColumn.setCellValueFactory(new PropertyValueFactory<>("response"));
        karyZazaleniaIdColumn.setCellValueFactory(new PropertyValueFactory<>("ticket"));
    }

    private void setupKaryActionColumn() {
        Callback<TableColumn<Punishment, Void>, TableCell<Punishment, Void>> cellFactory = _ -> new TableCell<>() {
            private final Button deleteButton = new Button("Delete");
            private final HBox pane = new HBox(deleteButton);

            {
                pane.setSpacing(5);
                deleteButton.setOnAction(_ -> {
                    Punishment punishment = getTableView().getItems().get(getIndex());
                    getTableView().getItems().remove(punishment);
                    punishmentManager.deletePunishment(punishment.getId());

                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(pane);
                }
            }
        };
        karyActionColumn.setCellFactory(cellFactory);
    }

    private void setupZazaleniaTable() {
        zazaleniaIdZazaleniaColumn.setCellValueFactory(new PropertyValueFactory<>("id_ticket"));
        zazaleniaIdUzytkownikaColumn.setCellValueFactory(new PropertyValueFactory<>("id_user"));
        zazaleniaSprawdzoneColumn.setCellValueFactory(new PropertyValueFactory<>("checked"));
        zazaleniaOdpowiedzColumn.setCellValueFactory(new PropertyValueFactory<>("response"));
        zazaleniaPowodOdrzutuColumn.setCellValueFactory(new PropertyValueFactory<>("response_text"));
    }

    private void setupZazaleniaActionColumn() {
        Callback<TableColumn<Ticket, Void>, TableCell<Ticket, Void>> cellFactory = _ -> new TableCell<>() {
            private final Button editButton = new Button("Edit");
            private final HBox pane = new HBox(editButton);
            {
                pane.setSpacing(5);
                editButton.setOnAction(event -> {
                    Ticket ticket = getTableView().getItems().get(getIndex());
                    zazalenieOdpowiedzTextField.setText(ticket.getResponse_text());
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : pane);
            }
        };
        zazaleniaActionColumn.setCellFactory(cellFactory);
    }

    private void setupPojazdyTable() {
        pojazdyIdPojazduColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        pojazdyIdUzytkownikaColumn.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        pojazdyVinColumn.setCellValueFactory(new PropertyValueFactory<>("vin"));
        pojazdyMarkaColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        pojazdyModelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        pojazdyRokProdukcjiColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        pojazdyNrRejestracyjnyColumn.setCellValueFactory(new PropertyValueFactory<>("registration"));
        pojazdyUbezpieczonyColumn.setCellValueFactory(new PropertyValueFactory<>("ubezpieczony"));
    }

    private void setupPojazdyActionColumn() {
        Callback<TableColumn<Car, Void>, TableCell<Car, Void>> cellFactory = param -> new TableCell<>() {
            private final Button deleteButton = new Button("Delete");
            private final HBox pane = new HBox(deleteButton);
            {
                pane.setSpacing(5);
                deleteButton.setOnAction(event -> {
                    Car car = getTableView().getItems().get(getIndex());
                    carManager.deleteCar(car.getId());
                    getTableView().getItems().remove(car);
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : pane);
            }
        };
        pojazdyActionColumn.setCellFactory(cellFactory);
    }


    public void handleLoadUserData() {
        User selectedUser = userComboBox.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            karyTableView.setItems(FXCollections.observableArrayList(selectedUser.getPunishmentList()));
            zazaleniaTableView.setItems(FXCollections.observableArrayList(selectedUser.getTicketsList()));
            pojazdyTableView.setItems(FXCollections.observableArrayList(selectedUser.getCars()));
        } else {
            karyTableView.getItems().clear();
            zazaleniaTableView.getItems().clear();
            pojazdyTableView.getItems().clear();
        }
    }

    public void handleAddKary() {
        User selectedUser = userComboBox.getSelectionModel().getSelectedItem();

        if(selectedUser == null) {
            AlertUtil.showAlert(AlertType.ERROR, "No user selected, select someone to manage!");
            return;
        }

        this.openWindow(Type.ADD_PUNISHMENT, selectedUser);
    }

    public void handleSubmitZazalenie() {
        Ticket selectedTicket = zazaleniaTableView.getSelectionModel().getSelectedItem();
        User selectedUser = userComboBox.getSelectionModel().getSelectedItem();

        if(selectedUser == null) {
            AlertUtil.showAlert(AlertType.ERROR, "No user selected, select someone to manage!");
            return;
        }

        if(selectedTicket == null) {
            AlertUtil.showAlert(AlertType.ERROR, "No ticket selected, select a ticket to respond to!");
            return;
        }

        selectedTicket.setResponse(true);
        selectedTicket.setResponse_text(zazalenieOdpowiedzTextField.getText());
        selectedTicket.setChecked(zazalenieSprawdzoneCheckBox.isSelected());

        this.ticketManager.updateResponseTicket(selectedTicket);
        selectedTicket.setChecked(true);
        zazaleniaTableView.refresh();

    }

    public void handleAddPojazd() {
        User selectedUser = userComboBox.getSelectionModel().getSelectedItem();

        if(selectedUser == null) {
            AlertUtil.showAlert(AlertType.ERROR, "No user selected, select someone to manage!");
            return;
        }

        this.openWindow(Type.ADD_CAR, selectedUser);

    }

    public void logout() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Resource.getResourceName(Type.MAIN));

            try {
                Parent root = fxmlLoader.load();
                Stage stage = (Stage) this.loadUserDataButton.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();

            } catch (Exception e) {
                AlertUtil.showAlert(AlertType.ERROR, "Panie kolego cos nie dziala, sprawdz logi!");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void openWindow(Type type, User user) {
        try {
            Stage stage = new Stage();
            Image image = new Image(getClass().getResourceAsStream("/ch/challangerson/view/image/logo.png"));
            FXMLLoader fxmlLoader = new FXMLLoader(Resource.getResourceName(type));
            Parent root = fxmlLoader.load();

            switch (type) {
                case ADD_CAR -> {
                    stage.setTitle("Add Car");
                    CarController carController = fxmlLoader.getController();
                    carController.setUser(user);
                }

                case ADD_PUNISHMENT -> {
                    stage.setTitle("Add Punishment");
                    AddPunishmentController adminAddPunishmentController = fxmlLoader.getController();
                    adminAddPunishmentController.setUser(user);
                }
            }
            stage.getIcons().add(image);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
