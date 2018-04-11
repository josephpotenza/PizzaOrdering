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
import javafx.stage.Window;


import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPaneController extends Pizza {

    @FXML
    private TextField loginEmail;

    @FXML
    private PasswordField loginPassword;

    @FXML
    private Label signupButton;

    @FXML
    private Label failedLoginLabel;

    @FXML
    private AnchorPane stage;

    @FXML
    private Button loginButton;


    @FXML
    void submitLogin(ActionEvent event) {
        boolean valid = false;
        try {
            String email = loginEmail.getText(); // capture user login and email
            String pass = loginPassword.getText();
            String SQL = "SELECT * FROM customers WHERE email = '" + email + "'" + "and password = '" + pass + "'";
            ResultSet result = Database.getResult(SQL);
            while (result.next()) {
                valid = true; // if valid login store user first / last name
            }
            if (valid != true) { // if invalid login return error
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Invalid Email or Password, Try Again.");
                String mediaFile = "Sample/src/Sounds/ErrorSound.wav";
                Media media = new Media(new File(mediaFile).toURI().toString());
                MediaPlayer errorSound = new MediaPlayer(media);
                errorSound.setVolume(errorSound.getVolume()+ 50000);
                errorSound.play();

                alert.showAndWait();
            }
            else{
                switchUI("OrderingPane.fxml", signupButton);
                String mediaFile = "Sample/src/Sounds/WelcomeSound.wav";
                Media media = new Media(new File(mediaFile).toURI().toString());
                MediaPlayer welcomeSound = new MediaPlayer(media);
                welcomeSound.play();
        }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }


    @FXML
    void switchToSignUpScene(MouseEvent event) {
        switchUI("SignUpPane.fxml", signupButton);
    }


    @FXML
    void switchToGuestLogin(MouseEvent event) {
        switchUI("SignInAsGuest.fxml", signupButton);


    }
}
