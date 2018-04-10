import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SignInAsGuestController {
    @FXML
    private AnchorPane stage;

    @FXML
    private TextField lastNameInput;

    @FXML
    private Label goBackButton;

    @FXML
    private Button signInButton;

    @FXML
    private TextField emailInput;

    @FXML
    private TextField firstNameInput;

    @FXML
    void signUpNewCustomer(ActionEvent event) {

    }

    @FXML
    void backToLoginPane(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("LoginPane.fxml"));
            Stage stage = (Stage) emailInput.getScene().getWindow();
            Scene scene = new Scene(root, 1600,900);
            stage.setScene(scene);

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
