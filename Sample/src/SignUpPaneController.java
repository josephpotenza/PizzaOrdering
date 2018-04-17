import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.sql.SQLException;

public class SignUpPaneController extends Pizza {

    @FXML
    private AnchorPane stage;

    @FXML
    private TextField lastNameInput;

    @FXML
    private Button signUpButton;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private TextField emailInput;

    @FXML
    private TextField firstNameInput;

    @FXML
    private Label goBackButton;

    @FXML
    private Label successLabel;

    @FXML
    void signUpNewCustomer(ActionEvent event) {
        Database temp = new Database();
        temp.connect(); // instantiate db and connect

        String SQL = "Insert INTO customers(LastName, FirstName, password, email) VALUES ('" + lastNameInput.getText() + "','" + firstNameInput.getText() + "','" + passwordInput.getText() + "','" + emailInput.getText() + "')"; //insert info on signup into db

        if(lastNameInput.getText().trim().isEmpty() || firstNameInput.getText().trim().isEmpty() || passwordInput.getText().trim().isEmpty() || emailInput.getText().trim().isEmpty()){ // check for blank fields in sign up
            Alert alert = new Alert(Alert.AlertType.ERROR); // alert box if blank fields
            alert.setTitle("Error");
            alert.setContentText("Missing Required Fields.");
            String mediaFile = "Sample/src/Sounds/ErrorSound.wav";
            Media media = new Media(new File(mediaFile).toURI().toString());
            MediaPlayer errorSound = new MediaPlayer(media);
            errorSound.setVolume(errorSound.getVolume()+ 50000);
            errorSound.play();

            alert.showAndWait();
        }
        else{
            if(customer.validEmail(emailInput.getText()) && customer.validName(firstNameInput.getText()) && customer.validName(lastNameInput.getText()) && customer.validPass(passwordInput.getText())) {//add other tests example password more than 6 char etc. use OR || statement
                try {
                    temp.statement.executeUpdate(SQL);
                    successLabel.setText("Success!");
                    goBackButton.setText("Go Back To Login.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR); // alert box if blank fields

                alert.setTitle("Error");
                alert.setContentText("Invalid Field.");String mediaFile = "Sample/src/Sounds/ErrorSound.wav";
                Media media = new Media(new File(mediaFile).toURI().toString());
                MediaPlayer errorSound = new MediaPlayer(media);
                errorSound.setVolume(errorSound.getVolume()+ 50000);
                errorSound.play();


                alert.showAndWait();
            }
        }

    }

    @FXML
    void backToLoginPane(MouseEvent event) {
        switchUI("LoginPane.fxml", goBackButton);
    }

}
