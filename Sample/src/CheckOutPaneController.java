import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;

public class CheckOutPaneController extends Pizza{

    @FXML
    private Label grandTotalLabel;

    @FXML
    private void initialize(){
        grandTotalLabel.setText(cart.getDf().format(cart.calcTotalPlusTax()));
        if (customer.getType().equals("guest")){
            String SQL2 = "DELETE FROM customers where customerID = " + customer.getcID();
            try {
                db.statement.executeUpdate(SQL2);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        String SQL = "TRUNCATE TABLE orders;";
        try {
            db.statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void backToLoginPane() {
        customer.clearCustomer();
        cart.removeAll();
        switchUI("LoginPane.fxml", grandTotalLabel);
    }
}
