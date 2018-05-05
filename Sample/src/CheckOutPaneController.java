import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class CheckOutPaneController extends Pizza{

    @FXML
    private Label grandTotalLabel;

    @FXML
    private void initialize(){
        grandTotalLabel.setText(cart.getDf().format(cart.calcTotalPlusTax()));
    }

    @FXML
    void backToLoginPane() {
        customer.clearCustomer();
        cart.removeAll();
        switchUI("LoginPane.fxml", grandTotalLabel);
    }
}
