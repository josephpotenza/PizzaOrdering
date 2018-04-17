import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class OrderingPaneController {

    @FXML
    private Label pizzaCategory;

    @FXML
    private Label drinksCategory;

    @FXML
    private Label sandwichesCategory;

    @FXML
    private Label appetizersCategory;

    @FXML
    private CheckBox pickupCheckBox;

    @FXML
    private CheckBox deliveryCheckBox;

    @FXML
    private AnchorPane pizzaPane;

    @FXML
    private AnchorPane drinksPane;

    @FXML
    private AnchorPane appetizersPane;

    @FXML
    private AnchorPane sandwichesPane;

    @FXML
    void switchToPizzaPane(ActionEvent event) {
        drinksPane.setVisible(false);
        appetizersPane.setVisible(false);
        sandwichesPane.setVisible(false);
        pizzaPane.setVisible(true);
    }

    @FXML
    void switchToSandwichesPane(ActionEvent event) {
        drinksPane.setVisible(false);
        appetizersPane.setVisible(false);
        pizzaPane.setVisible(false);
        sandwichesPane.setVisible(true);
    }

    @FXML
    void switchToAppetizersPane(ActionEvent event) {
        drinksPane.setVisible(false);
        pizzaPane.setVisible(false);
        sandwichesPane.setVisible(false);
        appetizersPane.setVisible(true);
    }

    @FXML
    void switchToDrinksPane(ActionEvent event) {
        pizzaPane.setVisible(false);
        sandwichesPane.setVisible(false);
        appetizersPane.setVisible(false);
        drinksPane.setVisible(true);
    }

    @FXML
    void pickupSelected(ActionEvent event) {
        if(deliveryCheckBox.isSelected())
            deliveryCheckBox.setSelected(false);
    }

    @FXML
    void deliverySelected(ActionEvent event){
        if(pickupCheckBox.isSelected())
            pickupCheckBox.setSelected(false);
    }

}
