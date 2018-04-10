import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;


import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPaneController {

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
                String firstName = result.getString("FirstName");
                String lastName = result.getString("LastName");
                System.out.println("Welcome:" + " " + firstName + " " + lastName);
            } if (valid !=true) // if invalid login return error
                System.out.println("No user found");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


            @FXML
            void switchToSignUpScene (MouseEvent event){
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("SignUpPane.fxml"));
                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    Scene scene = new Scene(root, 1600, 900);
                    stage.setScene(scene);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


            @FXML
            void switchToGuestLogin (MouseEvent event){
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("SignInAsGuest.fxml"));
                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    Scene scene = new Scene(root, 1600, 900);
                    stage.setScene(scene);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }
