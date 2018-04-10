import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

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
    void signInAsGuest(ActionEvent event) {
        Database temp = new Database();
        temp.connect();

        String SQL = "Insert INTO customers(LastName, FirstName, email) VALUES ('" + lastNameInput.getText() + "','" + firstNameInput.getText() + "','" + emailInput.getText() + "')";
        if(lastNameInput.getText().trim().isEmpty() || firstNameInput.getText().trim().isEmpty()|| emailInput.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Missing Required Fields.");

            alert.showAndWait();
        }
        else{
            try {
                temp.statement.executeUpdate(SQL);
                Parent root = FXMLLoader.load(getClass().getResource("OrderingPane.fxml"));
                Stage stage = (Stage) emailInput.getScene().getWindow();
                Scene scene = new Scene(root, 1600,900);
                stage.setScene(scene);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
