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
        //if login successful go to orderingPane
        //else update failedLoginLabel
        failedLoginLabel.setText("No User Found.");
    }


    @FXML
    void switchToSignUpScene(MouseEvent event) {
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("SignUpPane.fxml"));
            Stage stage = (Stage) loginButton.getScene().getWindow();
            Scene scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
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
