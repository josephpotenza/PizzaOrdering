import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.sql.SQLException;

public class OrderingPaneController extends Pizza {

    @FXML
    private AnchorPane appetizersPane;

    @FXML
    private Button deliverySubmitButton;

    @FXML
    private Label sandwichesCategory;

    @FXML
    private ChoiceBox<Integer> ExpMonthDropBox;

    @FXML
    private Label appetizersCategory;

    @FXML
    private CheckBox deliveryCheckBox;

    @FXML
    private TableColumn<Order, Integer> quantColumn;

    @FXML
    private Label pizzaCategory;

    @FXML
    private Label drinksCategory;

    @FXML
    private AnchorPane toppingsPane;

    @FXML
    private TableColumn<Order, String> nameColumn;

    @FXML
    private CheckBox deliveryCashbox;

    @FXML
    private ChoiceBox<Integer> expYearDropBox;

    @FXML
    private TableView<Order> shoppingCart;

    @FXML
    private TextField pickupPhoneTextField;

    @FXML
    private TableColumn<Order, Double> priceColumn;

    @FXML
    private AnchorPane deliveryPane;

    @FXML
    private AnchorPane pizzaPane;

    @FXML
    private AnchorPane drinksPane;

    @FXML
    private AnchorPane ccPane;

    @FXML
    private CheckBox deliveryCCbox;

    @FXML
    private AnchorPane sandwichesPane;

    @FXML
    private TextField deliveryCVVTextField;

    @FXML
    private TextField deliveryAddressTextField;

    @FXML
    private TextField deliveryCCnumTextField;

    @FXML
    private TextField deliveryPhoneNumberTextField;

    @FXML
    private CheckBox pickupCheckBox;

    @FXML
    private AnchorPane pickupPane;

    @FXML
    private Button cheesePizzaButton;

    @FXML
    private Label totalLabel;

    @FXML
    private AnchorPane afterCheckOutPane;

    @FXML
    private Button removeButton;


    @FXML
    private void initialize() {
        // initialize visible panels
        toppingsPane.setVisible(false);
        drinksPane.setVisible(false);
        appetizersPane.setVisible(false);
        sandwichesPane.setVisible(false);
        pizzaPane.setVisible(true);
        switchToPizzaPane();
        removeButton.setOnAction(e -> removeButtonClicked());

        // adding values for choice box for cc
        ExpMonthDropBox.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        expYearDropBox.getItems().addAll(2018, 2019, 2020, 2021, 2022, 2023, 2024, 2025, 2026, 2027, 2028);

        // intializing columns for shopping cart
        quantColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("orderName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("orderPrice"));
        shoppingCart.setItems(getOrders());


    }

    @FXML
    void CashBoxSelected(ActionEvent event) {
        deliveryCCbox.setSelected(false);
        ccPane.setVisible(false);

    }

    @FXML
    void CreditCardBoxSelected(ActionEvent event) {
        deliveryCashbox.setSelected(false);
        ccPane.setVisible(true);
        if (customer.getCreditCard() != null) {
            String substr = customer.getCreditCard().substring(customer.getCreditCard().length() - 4);
            deliveryCCnumTextField.setText("****-****-****-" + substr);
        }

    }

    @FXML
    void switchToPizzaPane() {
        drinksPane.setVisible(false);
        appetizersPane.setVisible(false);
        sandwichesPane.setVisible(false);
        pizzaPane.setVisible(true);
        toppingsPane.setVisible(false);
    }

    @FXML
    void switchToSandwichesPane(MouseEvent event) {
        drinksPane.setVisible(false);
        appetizersPane.setVisible(false);
        pizzaPane.setVisible(false);
        sandwichesPane.setVisible(true);
        toppingsPane.setVisible(false);
    }

    @FXML
    void switchToAppetizersPane(MouseEvent event) {
        drinksPane.setVisible(false);
        pizzaPane.setVisible(false);
        sandwichesPane.setVisible(false);
        appetizersPane.setVisible(true);
        toppingsPane.setVisible(false);
    }

    @FXML
    void switchToDrinksPane(MouseEvent event) {
        pizzaPane.setVisible(false);
        sandwichesPane.setVisible(false);
        appetizersPane.setVisible(false);
        drinksPane.setVisible(true);
        toppingsPane.setVisible(false);
    }

    @FXML
    void switchToToppingsPane() {
        pizzaPane.setVisible(false);
        sandwichesPane.setVisible(false);
        appetizersPane.setVisible(false);
        drinksPane.setVisible(false);
        toppingsPane.setVisible(true);
    }

    @FXML
    void pickupSelected(ActionEvent event) {
        deliveryPane.setVisible(false);
        pickupPane.setVisible(true);
        deliveryCheckBox.setSelected(false);
        // customer = getCustomer();
        if (customer.getPhone() != null) {
            pickupPhoneTextField.setText(customer.getPhone());

        }
        System.out.println("From Pickup" + customer);

    }

    @FXML
    void deliverySelected() {
        pickupPane.setVisible(false);
        deliveryPane.setVisible(true);
        pickupCheckBox.setSelected(false);
        if (customer.getPhone() != null) {
            deliveryPhoneNumberTextField.setText(customer.getPhone());
        }
        if (customer.getAddress() != null) {
            deliveryAddressTextField.setText(customer.getAddress());
        }
        System.out.println(customer.getAddress());
    }


    @FXML
    void checkoutButton() {
        if (cart.getNumOrders() > 0)
            afterCheckOutPane.setVisible(true);
    }

    @FXML
    void orderCheesePizza() {
        switchToToppingsPane();
        Order order = new Order(1, cart.menu[0].getOrderName(), cart.menu[0].getOrderPrice());
        cart.addToCart(order);
        shoppingCart.setItems(getOrders());
        totalLabel.setText(cart.getDf().format(cart.getTotalPrice()));
    }

    @FXML
    void orderThinCrust() {
        switchToToppingsPane();
        Order order = new Order(1, cart.menu[3].getOrderName(), cart.menu[3].getOrderPrice());
        cart.addToCart(order);
        shoppingCart.setItems(getOrders());
        totalLabel.setText(cart.getDf().format(cart.getTotalPrice()));
    }

    @FXML
    void orderDeepDish() {
        switchToToppingsPane();
        Order order = new Order(1, cart.menu[4].getOrderName(), cart.menu[4].getOrderPrice());
        cart.addToCart(order);
        shoppingCart.setItems(getOrders());
        totalLabel.setText(cart.getDf().format(cart.getTotalPrice()));
    }

    @FXML
    void orderWhitePizza() {
        switchToToppingsPane();
        Order order = new Order(1, cart.menu[2].getOrderName(), cart.menu[2].getOrderPrice());
        cart.addToCart(order);
        shoppingCart.setItems(getOrders());
        totalLabel.setText(cart.getDf().format(cart.getTotalPrice()));
    }

    @FXML
    void orderSicilianPizza() {
        switchToToppingsPane();
        Order order = new Order(1, cart.menu[5].getOrderName(), cart.menu[5].getOrderPrice());
        cart.addToCart(order);
        shoppingCart.setItems(getOrders());
        totalLabel.setText(cart.getDf().format(cart.getTotalPrice()));
    }

    @FXML
    void orderVodkaPizza() {
        switchToToppingsPane();
        Order order = new Order(1, cart.menu[1].getOrderName(), cart.menu[1].getOrderPrice());
        cart.addToCart(order);
        shoppingCart.setItems(getOrders());
        totalLabel.setText(cart.getDf().format(cart.getTotalPrice()));

    }

    @FXML
    void removeButtonClicked() {
        int index = shoppingCart.getSelectionModel().getSelectedIndex();
        int numToppings = cart.checkIfToppingsExist(index);
        for (int i = numToppings; i > 0; i--) {
            cart.removeItem(index + i);
        }
        cart.removeItem(index);
        totalLabel.setText(cart.getDf().format(cart.getTotalPrice()));
        shoppingCart.setItems(getOrders());
    }

    @FXML
    void orderPepperoni() {
        Order order = new Order(1, cart.menu[12].getOrderName(), cart.menu[12].getOrderPrice());
        cart.addToCart(order);
        shoppingCart.setItems(getOrders());
        totalLabel.setText(cart.getDf().format(cart.getTotalPrice()));
        if (cart.recentPizzaIndex() >= 0) {
            if (cart.checkIfToppingsExist(cart.recentPizzaIndex()) == 3) {
                switchToPizzaPane();
            }
        }
    }

    @FXML
    void orderSausage() {
        Order order = new Order(1, cart.menu[13].getOrderName(), cart.menu[13].getOrderPrice());
        cart.addToCart(order);
        shoppingCart.setItems(getOrders());
        totalLabel.setText(cart.getDf().format(cart.getTotalPrice()));
        if (cart.recentPizzaIndex() >= 0) {
            if (cart.checkIfToppingsExist(cart.recentPizzaIndex()) == 3) {
                switchToPizzaPane();
            }
        }
    }

    @FXML
    void orderChicken() {
        Order order = new Order(1, cart.menu[14].getOrderName(), cart.menu[14].getOrderPrice());
        cart.addToCart(order);
        shoppingCart.setItems(getOrders());
        totalLabel.setText(cart.getDf().format(cart.getTotalPrice()));
        if (cart.recentPizzaIndex() >= 0) {
            if (cart.checkIfToppingsExist(cart.recentPizzaIndex()) == 3) {
                switchToPizzaPane();
            }
        }
    }

    @FXML
    void orderMeatball() {
        Order order = new Order(1, cart.menu[15].getOrderName(), cart.menu[15].getOrderPrice());
        cart.addToCart(order);
        shoppingCart.setItems(getOrders());
        totalLabel.setText(cart.getDf().format(cart.getTotalPrice()));
        if (cart.recentPizzaIndex() >= 0) {
            if (cart.checkIfToppingsExist(cart.recentPizzaIndex()) == 3) {
                switchToPizzaPane();
            }
        }
    }

    @FXML
    void orderOlives() {
        Order order = new Order(1, cart.menu[16].getOrderName(), cart.menu[16].getOrderPrice());
        cart.addToCart(order);
        shoppingCart.setItems(getOrders());
        totalLabel.setText(cart.getDf().format(cart.getTotalPrice()));
        if (cart.recentPizzaIndex() >= 0) {
            if (cart.checkIfToppingsExist(cart.recentPizzaIndex()) == 3) {
                switchToPizzaPane();
            }
        }
    }

    @FXML
    void orderAnchovies() {
        Order order = new Order(1, cart.menu[17].getOrderName(), cart.menu[17].getOrderPrice());
        cart.addToCart(order);
        shoppingCart.setItems(getOrders());
        totalLabel.setText(cart.getDf().format(cart.getTotalPrice()));
        if (cart.recentPizzaIndex() >= 0) {
            if (cart.checkIfToppingsExist(cart.recentPizzaIndex()) == 3) {
                switchToPizzaPane();
            }
        }
    }


    @FXML
    void orderCheesyBread() {
        Order order = new Order(1, cart.menu[6].getOrderName(), cart.menu[6].getOrderPrice());
        cart.addToCart(order);
        shoppingCart.setItems(getOrders());
        totalLabel.setText(cart.getDf().format(cart.getTotalPrice()));
    }

    @FXML
    void orderGarlicBread() {
        Order order = new Order(1, cart.menu[7].getOrderName(), cart.menu[7].getOrderPrice());
        cart.addToCart(order);
        shoppingCart.setItems(getOrders());
        totalLabel.setText(cart.getDf().format(cart.getTotalPrice()));
    }

    @FXML
    void orderMozzarellaSticks() {
        Order order = new Order(1, cart.menu[8].getOrderName(), cart.menu[8].getOrderPrice());
        cart.addToCart(order);
        shoppingCart.setItems(getOrders());
        totalLabel.setText(cart.getDf().format(cart.getTotalPrice()));
    }

    @FXML
    void orderChickenWings() {
        Order order = new Order(1, cart.menu[9].getOrderName(), cart.menu[9].getOrderPrice());
        cart.addToCart(order);
        shoppingCart.setItems(getOrders());
        totalLabel.setText(cart.getDf().format(cart.getTotalPrice()));
    }

    @FXML
    void orderGarlicKnots() {
        Order order = new Order(1, cart.menu[10].getOrderName(), cart.menu[10].getOrderPrice());
        cart.addToCart(order);
        shoppingCart.setItems(getOrders());
        totalLabel.setText(cart.getDf().format(cart.getTotalPrice()));
    }

    @FXML
    void orderFriedCalamari() {
        Order order = new Order(1, cart.menu[11].getOrderName(), cart.menu[11].getOrderPrice());
        cart.addToCart(order);
        shoppingCart.setItems(getOrders());
        totalLabel.setText(cart.getDf().format(cart.getTotalPrice()));
    }

    @FXML
    void placeOrder(ActionEvent event) {
        // check all inputs first
        for (int i = 0; i < cart.getDbnumorders(); i++) {
            int temp = 1;
            String SQL = "INSERT INTO orders(orderID, customerID, menuID, quantity) VALUES ('" + temp + "','" + customer.getcID() + "','" + (cart.findMenuID(i) + 1) + "','" + cart.getQuantityofdbOrders(i) + "')";
            try {
                db.statement.executeUpdate(SQL);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}