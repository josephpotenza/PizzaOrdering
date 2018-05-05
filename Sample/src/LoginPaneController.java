import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPaneController extends Pizza
{


    @FXML
    private TextField loginEmail;

    @FXML
    private PasswordField loginPassword;

    @FXML
    private Label signupButton;

    @FXML
    private Label failedLoginLabel;

    @FXML
    private Button loginButton;


    @FXML
    private void initialize(){
        loginPassword.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent ke)
            {
                if (ke.getCode().equals(KeyCode.ENTER))
                {
                    submitLogin();
                }
            }
        });
    }

    /*                     working example of choiceBox
    @FXML
    private ChoiceBox choiceBox;
    ObservableList<String> numberList = FXCollections.observableArrayList("1","2" ,"3","4", "5");
    @FXML
    private void initialize(){
        choiceBox.setItems(numberList);
        choiceBox.setValue("1");
        label.setText(choiceBox.getValue().toString());
    }
    */



    @FXML
    void submitLogin() {
        boolean valid = false;
        try {
            String email = loginEmail.getText(); // capture user login and email
            String pass = loginPassword.getText();
            String SQL = "SELECT * FROM customers WHERE email = '" + email + "'" + "and password = '" + pass + "'";
            ResultSet result = Database.getResult(SQL);
            while (result.next()) {
                valid = true; // if valid login store user first / last name
                customer.setType("customer");
                setCustomerInfo(result.getString("firstName"), result.getString("lastName"), email, result.getString("password"), result.getString("address"), result.getString("creditCard"), result.getString("phone"), result.getString("customerID"));

            }
            if (valid != true) { // if invalid login return error
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Invalid Email or Password, Try Again.");
                errorSound.setVolume(errorSound.getVolume()+ 50000);
                errorSound.play();

                alert.showAndWait();
            }
            else{   //if login worked
                switchUI("OrderingPane.fxml", signupButton);
                String mediaFile = "Sample/src/Sounds/WelcomeSound.wav";
                Media media = new Media(new File(mediaFile).toURI().toString());
                MediaPlayer welcomeSound = new MediaPlayer(media);
                welcomeSound.play();

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Could not connect to Database, Please check internet connection");
            errorSound.setVolume(errorSound.getVolume()+ 50000);
            errorSound.play();

            alert.showAndWait();
        }

    }


    @FXML
    void switchToSignUpScene(MouseEvent event) {
        switchUI("SignUpPane.fxml", failedLoginLabel);
    }


    @FXML
    void switchToGuestLogin(MouseEvent event) {
        switchUI("SignInAsGuest.fxml", signupButton);


    }


}