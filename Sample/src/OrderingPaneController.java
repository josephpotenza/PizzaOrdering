import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class OrderingPaneController extends Pizza{

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
    private void initialize(){
        // initialize visible panels
        //customer = getCustomer(c);
        System.out.println("fromOrder " + customer);

        toppingsPane.setVisible(false);
        drinksPane.setVisible(false);
        appetizersPane.setVisible(false);
        sandwichesPane.setVisible(false);
        pizzaPane.setVisible(true);
        switchToPizzaPane();
        cart.getMenu();
        removeButton.setOnAction(e-> removeButtonClicked());

        // adding values for choice box for cc
        ExpMonthDropBox.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12);
        expYearDropBox.getItems().addAll(2018,2019,2020,2021,2022,2023,2024,2025,2026,2027,2028);
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
        if(customer.getCreditCard() != null){
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
            deliveryPane.setVisible(false);
            pickupPane.setVisible(true);
            deliveryCheckBox.setSelected(false);
           // customer = getCustomer();
            if(customer.getPhone() != null){
                pickupPhoneTextField.setText(customer.getPhone());

            }
            System.out.println("From Pickup" + customer);

        }
    @FXML
    void deliverySelected(ActionEvent event){
            pickupPane.setVisible(false);
            deliveryPane.setVisible(true);
            pickupCheckBox.setSelected(false);
            if(customer.getPhone() != null){
                deliveryPhoneNumberTextField.setText(customer.getPhone());
            }
            if(customer.getAddress() != null){
                deliveryAddressTextField.setText(customer.getAddress());
            }
            System.out.println(customer.getAddress());
    }


    @FXML
    void checkoutButton(ActionEvent event) {
        if(cart.getNumOrders()>0)
            afterCheckOutPane.setVisible(true);
    }

    @FXML
    void orderCheesePizza(){
        switchToToppingsPane();
        Order order = new Order(1, cart.menu[0].getOrderName(), cart.menu[0].getOrderPrice());
        cart.addToCart(order);
        shoppingCart.setItems(getOrders());
        totalLabel.setText(Double.toString(cart.getTotalPrice()));
    }

    @FXML
    void orderThinCrust(){
        switchToToppingsPane();
        Order order = new Order(1, cart.menu[3].getOrderName(), cart.menu[3].getOrderPrice());
        cart.addToCart(order);
        shoppingCart.setItems(getOrders());
        totalLabel.setText(Double.toString(cart.getTotalPrice()));
    }

    @FXML
    void orderDeepDish(){
        switchToToppingsPane();
        Order order = new Order(1, cart.menu[4].getOrderName(), cart.menu[4].getOrderPrice());
        cart.addToCart(order);
        shoppingCart.setItems(getOrders());
        totalLabel.setText(Double.toString(cart.getTotalPrice()));
    }

    @FXML
    void orderWhitePizza(){
        switchToToppingsPane();
        Order order = new Order(1, cart.menu[2].getOrderName(), cart.menu[2].getOrderPrice());
        cart.addToCart(order);
        shoppingCart.setItems(getOrders());
        totalLabel.setText(Double.toString(cart.getTotalPrice()));
    }

    @FXML
    void orderSicilianPizza(){
        switchToToppingsPane();
        Order order = new Order(1, cart.menu[5].getOrderName(), cart.menu[5].getOrderPrice());
        cart.addToCart(order);
        shoppingCart.setItems(getOrders());
        totalLabel.setText(Double.toString(cart.getTotalPrice()));
    }

    @FXML
    void orderVodkaPizza(){
        switchToToppingsPane();
        Order order = new Order(1, cart.menu[1].getOrderName(), cart.menu[1].getOrderPrice());
        cart.addToCart(order);
        shoppingCart.setItems(getOrders());
        totalLabel.setText(Double.toString(cart.getTotalPrice()));
    }
    @FXML
    void removeButtonClicked(){
        ObservableList<Order> orderSelected, allOrders;
        allOrders = shoppingCart.getItems();
        orderSelected = shoppingCart.getSelectionModel().getSelectedItems();
        //Order temp = shoppingCart.getSelectionModel().getSelectedItems().get(0);
        int index = shoppingCart.getSelectionModel().getSelectedIndex();
        cart.removeItem(index);
        totalLabel.setText(Double.toString(cart.getTotalPrice()));
        orderSelected.forEach(allOrders::remove);
        for(int i = 0; i < cart.getNumOrders(); i++)
            System.out.println(cart.getOrder(i).getOrderName());

    }

}
