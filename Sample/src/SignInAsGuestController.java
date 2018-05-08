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
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignInAsGuestController extends Pizza{
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

        String SQL = "Insert INTO customers(LastName, FirstName, email) VALUES ('" + lastNameInput.getText() + "','" + firstNameInput.getText() + "','" + emailInput.getText() + "')";
        if(lastNameInput.getText().trim().isEmpty() || firstNameInput.getText().trim().isEmpty()|| emailInput.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Missing Required Fields.");

            alert.showAndWait();
        }
        else{
            if(customer.validName(firstNameInput.getText()) && customer.validName(lastNameInput.getText()) && customer.validName(firstNameInput.getText()) && customer.validEmail(emailInput.getText())) {
                try {
                    customer.setFirstName(firstNameInput.getText());
                    customer.setLastName(lastNameInput.getText());
                    customer.setEmail(emailInput.getText());
                    customer.setType("guest");
                    db.statement.executeUpdate(SQL);
                    String SQL1 = "SELECT * FROM customers WHERE email = '" + emailInput.getText() + "'" + "and FirstName = '" + firstNameInput.getText() + "'";
                    ResultSet result = Database.getResult(SQL1);
                    while (result.next()){
                        customer.setcID(result.getString("customerID"));
                    }
                    switchUI("OrderingPane.fxml", goBackButton);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR); // alert box if blank fields

                alert.setTitle("Error");
                alert.setContentText("Invalid Field.");
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
