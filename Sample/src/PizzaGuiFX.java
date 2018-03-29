import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class PizzaGuiFX{

    @FXML
    private ImageView pope;

    @FXML
    private TextField loginEmail;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField loginPassword;

    @FXML
    private Label label;

    @FXML
    void submitLogin(ActionEvent event) {

        label.setText(loginEmail.getText() + " " + loginPassword.getText());

    }


}
