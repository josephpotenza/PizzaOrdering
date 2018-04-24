import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class OrderingPaneController extends Pizza{

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
    private AnchorPane toppingsPane;

    @FXML
    private Button cheesePizzaButton;

    @FXML
    private Button addToCartButton;

    @FXML
    private VBox quantityVBox;

    @FXML
    private VBox orderVBox;

    @FXML
    private VBox priceVBox;

    @FXML
    private TableView<Order> shoppingCart;

    @FXML
    private TableColumn<Order, String> nameColumn;

    @FXML
    private TableColumn<Order, Integer> quantColumn;

    @FXML
    private TableColumn<Order, Double> priceColumn;


    @FXML
    private void initialize(){

        toppingsPane.setVisible(false);
        drinksPane.setVisible(false);
        appetizersPane.setVisible(false);
        sandwichesPane.setVisible(false);
        pizzaPane.setVisible(true);
        switchToPizzaPane();
        cart.getMenu();
        nameColumn.

    }

    @FXML
    void switchToPizzaPane() {
        drinksPane.setVisible(false);
        appetizersPane.setVisible(false);
        sandwichesPane.setVisible(false);
        pizzaPane.setVisible(true);
    }

    @FXML
    void switchToSandwichesPane(MouseEvent event) {
        drinksPane.setVisible(false);
        appetizersPane.setVisible(false);
        pizzaPane.setVisible(false);
        sandwichesPane.setVisible(true);
    }

    @FXML
    void switchToAppetizersPane(MouseEvent event) {
        drinksPane.setVisible(false);
        pizzaPane.setVisible(false);
        sandwichesPane.setVisible(false);
        appetizersPane.setVisible(true);
    }

    @FXML
    void switchToDrinksPane(MouseEvent event) {
        pizzaPane.setVisible(false);
        sandwichesPane.setVisible(false);
        appetizersPane.setVisible(false);
        drinksPane.setVisible(true);
    }

    @FXML
    void switchToToppingsPane(){
        pizzaPane.setVisible(false);
        sandwichesPane.setVisible(false);
        appetizersPane.setVisible(false);
        drinksPane.setVisible(false);
        toppingsPane.setVisible(true);
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

    @FXML
    void orderCheesePizza(){
        switchToToppingsPane();
        Order order = new Order(1, cart.menu[0].getOrderName(), cart.menu[0].getOrderPrice());
        cart.orders.add(order);
    }

    @FXML
    void addToCart(){

        Order order = new Order(4, "Pizza", 12.00);
        cart.addToCart(order);


        //Working Test
       /*
        Label order1 = new Label();
        order1.setText(cart.menu[0].getOrderName());
        Label order1Price = new Label();
        order1Price.setText(Double.toString(cart.menu[0].getOrderPrice()));
        orderVBox.getChildren().add(order1);
        priceVBox.getChildren().add(order1Price);
        */
    }

}
