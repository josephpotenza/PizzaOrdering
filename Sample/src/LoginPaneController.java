import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class LoginPaneController {

    @FXML
    private TextField loginEmail;

    @FXML
    private Label signupButton;

    @FXML
    private AnchorPane stage;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField loginPassword;

    @FXML
    private Label failedLoginLabel;

    @FXML
    void submitLogin(ActionEvent event) {
        //if login successful go to orderingPane
        //else update failedLoginLabel
        failedLoginLabel.setText("No User Found.");
    }


    @FXML
    void switchToSignUpScene(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("SignUpPane.fxml"));
            Stage stage = (Stage) loginButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


    @FXML
    void switchToGuestLogin(MouseEvent event) {

    }


}
