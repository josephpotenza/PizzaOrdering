import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.sql.SQLException;

public class SignUpPaneController {

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

            alert.showAndWait();
        }
        else{
            try {
                temp.statement.executeUpdate(SQL);
                successLabel.setText("Success!");
                 goBackButton.setText("Go Back To Login.");
               } catch (SQLException e) {
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
