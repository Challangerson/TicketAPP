package ch.challangerson.main;

import ch.challangerson.object.user.User;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainController {

//    private final User user;


    @FXML
    private Text information;


    @FXML
    public void showText() {
//        System.out.println(information.getText());

        Node source = information.getScene().getRoot();
        Stage stage = (Stage) source.getScene().getWindow();

        User user = (User) stage.getUserData();
        information.setText(user.toString());
    }


    public void initialize() {
        Node source = information.getScene().getRoot();
        Stage stage = (Stage) source.getScene().getWindow();

        User user = (User) stage.getUserData();
        information.setText(user.toString());
    }



//    public MainController(User user) {
//        this.user = user;
//        this.text.setText(user.toString());
//    }
}
