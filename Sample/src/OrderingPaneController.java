import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;


//#006491			//domin blue
//#e31837 		//domin red

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
    private Label taxesLabel;

    @FXML
    private Label totalPlusTaxes;

    @FXML
    private AnchorPane afterCheckOutPane;

    @FXML
    private AnchorPane pictureAnchor;

    @FXML
    private Button removeButton;


    @FXML
    private void initialize() {
        welcomeSound.play();
        //setCurrentStage((Stage) pictureAnchor.getScene().getWindow());
        //inCaseOfClose();
        // initialize visible panels
        toppingsPane.setVisible(false);
        drinksPane.setVisible(false);
        appetizersPane.setVisible(false);
        sandwichesPane.setVisible(false);
        pizzaPane.setVisible(true);
        pictureAnchor.setVisible(true);
        switchToPizzaPane();
        removeButton.setOnAction(e -> removeButtonClicked());

        // adding values for choice box for cc
        ExpMonthDropBox.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        expYearDropBox.getItems().addAll(2018, 2019, 2020, 2021, 2022, 2023, 2024, 2025, 2026, 2027, 2028);

        // intializing columns for shopping cart
        quantColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("orderName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        shoppingCart.setItems(getOrders());


    }

    @FXML
    void CashBoxSelected(ActionEvent event) {
            deliveryCCbox.setSelected(false);
            ccPane.setVisible(false);
    }

    @FXML
    void CreditCardBoxSelected(ActionEvent event) {
        if(deliveryCCbox.isSelected()) {
            deliveryCashbox.setSelected(false);
            ccPane.setVisible(true);
            if (customer.getCreditCard() != null) {
                String substr = customer.getCreditCard().substring(customer.getCreditCard().length() - 4);
                deliveryCCnumTextField.setText("****-****-****-" + substr);
            }
        }
        else{
            ccPane.setVisible(false);
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


    /*
    @FXML
    void goToLocation(){
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        final URL urlGoogleMaps = getClass().getResource("demo.html");
        webEngine.load(urlGoogleMaps.toExternalForm());
        webEngine.setOnAlert(new EventHandler<WebEvent<String>>() {
            @Override
            public void handle(WebEvent<String> e) {
                System.out.println(e.toString());
            }
        });

        getChildren()

    }

    */

    @FXML
    void pickupSelected(ActionEvent event) {
        if(pickupCheckBox.isSelected()) {
            deliveryPane.setVisible(false);
            pickupPane.setVisible(true);
            deliveryCheckBox.setSelected(false);
            // customer = getCustomer();
            if (customer.getPhone() != null) {
                pickupPhoneTextField.setText(customer.getPhone());
            }
        }
        else{
            pickupPane.setVisible(false);
        }
    }

    @FXML
    void deliverySelected() {
        if(deliveryCheckBox.isSelected()) {
            pickupPane.setVisible(false);
            deliveryPane.setVisible(true);
            pickupCheckBox.setSelected(false);
            if (customer.getPhone() != null) {
                deliveryPhoneNumberTextField.setText(customer.getPhone());
            }
            if (customer.getAddress() != null) {
                deliveryAddressTextField.setText(customer.getAddress());
            }
        }
        else{
            deliveryPane.setVisible(false);
        }
    }


    @FXML
    void checkoutButton() {
        pictureAnchor.setVisible(false);
        if (cart.getNumOrders() > 0)
            afterCheckOutPane.setVisible(true);
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please Place an Order before Checking Out.");
            errorSound.setVolume(errorSound.getVolume()+ 50000);
            errorSound.play();

            alert.showAndWait();
        }
    }

    @FXML
    void orderCheesePizza() {
        switchToToppingsPane();
        Order order = new Order(1, cart.menu[0].getOrderName(), cart.menu[0].getOrderPrice());
        cart.addToCart(order);
        shoppingCart.getItems().removeAll(getOrders());
        shoppingCart.setItems(getOrders());
        totalLabel.setText(cart.getDf().format(cart.getTotalPrice()));
        taxesLabel.setText(cart.getDf().format(cart.getTax()));
        totalPlusTaxes.setText(cart.getDf().format(cart.calcTotalPlusTax()));
    }

    @FXML
    void orderThinCrust() {
        switchToToppingsPane();
        Order order = new Order(1, cart.menu[3].getOrderName(), cart.menu[3].getOrderPrice());
        cart.addToCart(order);
        shoppingCart.getItems().removeAll(getOrders());
        shoppingCart.setItems(getOrders());
        totalLabel.setText(cart.getDf().format(cart.getTotalPrice()));
        taxesLabel.setText(cart.getDf().format(cart.getTax()));
        totalPlusTaxes.setText(cart.getDf().format(cart.calcTotalPlusTax()));
    }

    @FXML
    void orderDeepDish() {
        switchToToppingsPane();
        Order order = new Order(1, cart.menu[4].getOrderName(), cart.menu[4].getOrderPrice());
        cart.addToCart(order);
        shoppingCart.getItems().removeAll(getOrders());
        shoppingCart.setItems(getOrders());
        totalLabel.setText(cart.getDf().format(cart.getTotalPrice()));
        taxesLabel.setText(cart.getDf().format(cart.getTax()));
        totalPlusTaxes.setText(cart.getDf().format(cart.calcTotalPlusTax()));
    }

    @FXML
    void orderWhitePizza() {
        switchToToppingsPane();
        Order order = new Order(1, cart.menu[2].getOrderName(), cart.menu[2].getOrderPrice());
        cart.addToCart(order);
        shoppingCart.getItems().removeAll(getOrders());
        shoppingCart.setItems(getOrders());
        totalLabel.setText(cart.getDf().format(cart.getTotalPrice()));
        taxesLabel.setText(cart.getDf().format(cart.getTax()));
        totalPlusTaxes.setText(cart.getDf().format(cart.calcTotalPlusTax()));
    }

    @FXML
    void orderSicilianPizza() {
        switchToToppingsPane();
        Order order = new Order(1, cart.menu[5].getOrderName(), cart.menu[5].getOrderPrice());
        cart.addToCart(order);
        shoppingCart.getItems().removeAll(getOrders());
        shoppingCart.setItems(getOrders());
        totalLabel.setText(cart.getDf().format(cart.getTotalPrice()));
        taxesLabel.setText(cart.getDf().format(cart.getTax()));
        totalPlusTaxes.setText(cart.getDf().format(cart.calcTotalPlusTax()));
    }

    @FXML
    void orderVodkaPizza() {
        switchToToppingsPane();
        Order order = new Order(1, cart.menu[1].getOrderName(), cart.menu[1].getOrderPrice());
        cart.addToCart(order);
        shoppingCart.getItems().removeAll(getOrders());
        shoppingCart.setItems(getOrders());
        totalLabel.setText(cart.getDf().format(cart.getTotalPrice()));
        taxesLabel.setText(cart.getDf().format(cart.getTax()));
        totalPlusTaxes.setText(cart.getDf().format(cart.calcTotalPlusTax()));

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
        taxesLabel.setText(cart.getDf().format(cart.getTax()));
        totalPlusTaxes.setText(cart.getDf().format(cart.calcTotalPlusTax()));
        shoppingCart.setItems(getOrders());
        if(cart.getNumOrders() == 0){
            afterCheckOutPane.setVisible(false);
            pictureAnchor.setVisible(true);
            ccPane.setVisible(false);
            deliveryPane.setVisible(false);
            pickupPane.setVisible(false);
            deliveryCheckBox.setSelected(false);
            pickupCheckBox.setSelected(false);
        }
    }

    @FXML
    void orderPepperoni() {
        Order order = new Order(1, cart.menu[12].getOrderName(), cart.menu[12].getOrderPrice());
        cart.addToCart(order);
        shoppingCart.getItems().removeAll(getOrders());
        shoppingCart.setItems(getOrders());
        totalLabel.setText(cart.getDf().format(cart.getTotalPrice()));
        if (cart.recentPizzaIndex() >= 0) {
            if (cart.checkIfToppingsExist(cart.recentPizzaIndex()) == 3) {
                switchToPizzaPane();
            }
        }
        taxesLabel.setText(cart.getDf().format(cart.getTax()));
        totalPlusTaxes.setText(cart.getDf().format(cart.calcTotalPlusTax()));
    }

    @FXML
    void orderSausage() {
        Order order = new Order(1, cart.menu[13].getOrderName(), cart.menu[13].getOrderPrice());
        cart.addToCart(order);
        shoppingCart.getItems().removeAll(getOrders());
        shoppingCart.setItems(getOrders());
        totalLabel.setText(cart.getDf().format(cart.getTotalPrice()));
        if (cart.recentPizzaIndex() >= 0) {
            if (cart.checkIfToppingsExist(cart.recentPizzaIndex()) == 3) {
                switchToPizzaPane();
            }
        }
        taxesLabel.setText(cart.getDf().format(cart.getTax()));
        totalPlusTaxes.setText(cart.getDf().format(cart.calcTotalPlusTax()));
    }

    @FXML
    void orderChicken() {
        Order order = new Order(1, cart.menu[14].getOrderName(), cart.menu[14].getOrderPrice());
        cart.addToCart(order);
        shoppingCart.getItems().removeAll(getOrders());
        shoppingCart.setItems(getOrders());
        totalLabel.setText(cart.getDf().format(cart.getTotalPrice()));
        if (cart.recentPizzaIndex() >= 0) {
            if (cart.checkIfToppingsExist(cart.recentPizzaIndex()) == 3) {
                switchToPizzaPane();
            }
        }
        taxesLabel.setText(cart.getDf().format(cart.getTax()));
        totalPlusTaxes.setText(cart.getDf().format(cart.calcTotalPlusTax()));
    }

    @FXML
    void orderMeatball() {
        Order order = new Order(1, cart.menu[15].getOrderName(), cart.menu[15].getOrderPrice());
        cart.addToCart(order);
        shoppingCart.getItems().removeAll(getOrders());
        shoppingCart.setItems(getOrders());
        totalLabel.setText(cart.getDf().format(cart.getTotalPrice()));
        if (cart.recentPizzaIndex() >= 0) {
            if (cart.checkIfToppingsExist(cart.recentPizzaIndex()) == 3) {
                switchToPizzaPane();
            }
        }
        taxesLabel.setText(cart.getDf().format(cart.getTax()));
        totalPlusTaxes.setText(cart.getDf().format(cart.calcTotalPlusTax()));
    }

    @FXML
    void orderOlives() {
        Order order = new Order(1, cart.menu[16].getOrderName(), cart.menu[16].getOrderPrice());
        cart.addToCart(order);
        shoppingCart.getItems().removeAll(getOrders());
        shoppingCart.setItems(getOrders());
        totalLabel.setText(cart.getDf().format(cart.getTotalPrice()));
        if (cart.recentPizzaIndex() >= 0) {
            if (cart.checkIfToppingsExist(cart.recentPizzaIndex()) == 3) {
                switchToPizzaPane();
            }
        }
        taxesLabel.setText(cart.getDf().format(cart.getTax()));
        totalPlusTaxes.setText(cart.getDf().format(cart.calcTotalPlusTax()));
    }

    @FXML
    void orderAnchovies() {
        Order order = new Order(1, cart.menu[17].getOrderName(), cart.menu[17].getOrderPrice());
        cart.addToCart(order);
        shoppingCart.getItems().removeAll(getOrders());
        shoppingCart.setItems(getOrders());
        totalLabel.setText(cart.getDf().format(cart.getTotalPrice()));
        if (cart.recentPizzaIndex() >= 0) {
            if (cart.checkIfToppingsExist(cart.recentPizzaIndex()) == 3) {
                switchToPizzaPane();
            }
        }
        taxesLabel.setText(cart.getDf().format(cart.getTax()));
        totalPlusTaxes.setText(cart.getDf().format(cart.calcTotalPlusTax()));
    }


    @FXML
    void orderCheesyBread() {
        Order order = new Order(1, cart.menu[6].getOrderName(), cart.menu[6].getOrderPrice());
        cart.addToCart(order);
        shoppingCart.getItems().removeAll(getOrders());
        shoppingCart.setItems(getOrders());
        totalLabel.setText(cart.getDf().format(cart.getTotalPrice()));
        taxesLabel.setText(cart.getDf().format(cart.getTax()));
        totalPlusTaxes.setText(cart.getDf().format(cart.calcTotalPlusTax()));
    }

    @FXML
    void orderGarlicBread() {
        Order order = new Order(1, cart.menu[7].getOrderName(), cart.menu[7].getOrderPrice());
        cart.addToCart(order);
        shoppingCart.getItems().removeAll(getOrders());
        shoppingCart.setItems(getOrders());
        totalLabel.setText(cart.getDf().format(cart.getTotalPrice()));
        taxesLabel.setText(cart.getDf().format(cart.getTax()));
        totalPlusTaxes.setText(cart.getDf().format(cart.calcTotalPlusTax()));
    }

    @FXML
    void orderMozzarellaSticks() {
        Order order = new Order(1, cart.menu[8].getOrderName(), cart.menu[8].getOrderPrice());
        cart.addToCart(order);
        shoppingCart.getItems().removeAll(getOrders());
        shoppingCart.setItems(getOrders());
        totalLabel.setText(cart.getDf().format(cart.getTotalPrice()));
        taxesLabel.setText(cart.getDf().format(cart.getTax()));
        totalPlusTaxes.setText(cart.getDf().format(cart.calcTotalPlusTax()));
    }

    @FXML
    void orderChickenWings() {
        Order order = new Order(1, cart.menu[9].getOrderName(), cart.menu[9].getOrderPrice());
        cart.addToCart(order);
        shoppingCart.getItems().removeAll(getOrders());
        shoppingCart.setItems(getOrders());
        totalLabel.setText(cart.getDf().format(cart.getTotalPrice()));
        taxesLabel.setText(cart.getDf().format(cart.getTax()));
        totalPlusTaxes.setText(cart.getDf().format(cart.calcTotalPlusTax()));
    }

    @FXML
    void orderGarlicKnots() {
        Order order = new Order(1, cart.menu[10].getOrderName(), cart.menu[10].getOrderPrice());
        cart.addToCart(order);
        shoppingCart.getItems().removeAll(getOrders());
        shoppingCart.setItems(getOrders());
        totalLabel.setText(cart.getDf().format(cart.getTotalPrice()));
        taxesLabel.setText(cart.getDf().format(cart.getTax()));
        totalPlusTaxes.setText(cart.getDf().format(cart.calcTotalPlusTax()));
    }

    @FXML
    void orderFriedCalamari() {
        Order order = new Order(1, cart.menu[11].getOrderName(), cart.menu[11].getOrderPrice());
        cart.addToCart(order);
        shoppingCart.getItems().removeAll(getOrders());
        shoppingCart.setItems(getOrders());
        totalLabel.setText(cart.getDf().format(cart.getTotalPrice()));
        taxesLabel.setText(cart.getDf().format(cart.getTax()));
        totalPlusTaxes.setText(cart.getDf().format(cart.calcTotalPlusTax()));
    }

    @FXML
    void orderChickenParmHero() {
        Order order = new Order(1, cart.menu[18].getOrderName(), cart.menu[18].getOrderPrice());
        cart.addToCart(order);
        shoppingCart.getItems().removeAll(getOrders());
        shoppingCart.setItems(getOrders());
        totalLabel.setText(cart.getDf().format(cart.getTotalPrice()));
        taxesLabel.setText(cart.getDf().format(cart.getTax()));
        totalPlusTaxes.setText(cart.getDf().format(cart.calcTotalPlusTax()));
    }

    @FXML
    void orderChickenCutletHero() {
        Order order = new Order(1, cart.menu[19].getOrderName(), cart.menu[19].getOrderPrice());
        cart.addToCart(order);
        shoppingCart.getItems().removeAll(getOrders());
        shoppingCart.setItems(getOrders());
        totalLabel.setText(cart.getDf().format(cart.getTotalPrice()));
        taxesLabel.setText(cart.getDf().format(cart.getTax()));
        totalPlusTaxes.setText(cart.getDf().format(cart.calcTotalPlusTax()));
    }

    @FXML
    void orderMeatballParmHero() {
        Order order = new Order(1, cart.menu[20].getOrderName(), cart.menu[20].getOrderPrice());
        cart.addToCart(order);
        shoppingCart.getItems().removeAll(getOrders());
        shoppingCart.setItems(getOrders());
        totalLabel.setText(cart.getDf().format(cart.getTotalPrice()));
        taxesLabel.setText(cart.getDf().format(cart.getTax()));
        totalPlusTaxes.setText(cart.getDf().format(cart.calcTotalPlusTax()));
    }

    @FXML
    void orderChickenRoll() {
        Order order = new Order(1, cart.menu[21].getOrderName(), cart.menu[21].getOrderPrice());
        cart.addToCart(order);
        shoppingCart.getItems().removeAll(getOrders());
        shoppingCart.setItems(getOrders());
        totalLabel.setText(cart.getDf().format(cart.getTotalPrice()));
        taxesLabel.setText(cart.getDf().format(cart.getTax()));
        totalPlusTaxes.setText(cart.getDf().format(cart.calcTotalPlusTax()));
    }

    @FXML
    void orderSausageRoll() {
        Order order = new Order(1, cart.menu[22].getOrderName(), cart.menu[22].getOrderPrice());
        cart.addToCart(order);
        shoppingCart.getItems().removeAll(getOrders());
        shoppingCart.setItems(getOrders());
        totalLabel.setText(cart.getDf().format(cart.getTotalPrice()));
        taxesLabel.setText(cart.getDf().format(cart.getTax()));
        totalPlusTaxes.setText(cart.getDf().format(cart.calcTotalPlusTax()));
    }

    @FXML
    void orderVegetableRoll() {
        Order order = new Order(1, cart.menu[23].getOrderName(), cart.menu[23].getOrderPrice());
        cart.addToCart(order);
        shoppingCart.getItems().removeAll(getOrders());
        shoppingCart.setItems(getOrders());
        totalLabel.setText(cart.getDf().format(cart.getTotalPrice()));
        taxesLabel.setText(cart.getDf().format(cart.getTax()));
        totalPlusTaxes.setText(cart.getDf().format(cart.calcTotalPlusTax()));
    }

    @FXML
    void orderCoke() {
        Order order = new Order(1, cart.menu[24].getOrderName(), cart.menu[24].getOrderPrice());
        cart.addToCart(order);
        shoppingCart.getItems().removeAll(getOrders());
        shoppingCart.setItems(getOrders());
        totalLabel.setText(cart.getDf().format(cart.getTotalPrice()));
        taxesLabel.setText(cart.getDf().format(cart.getTax()));
        totalPlusTaxes.setText(cart.getDf().format(cart.calcTotalPlusTax()));
    }

    @FXML
    void orderSprite() {
        Order order = new Order(1, cart.menu[25].getOrderName(), cart.menu[25].getOrderPrice());
        cart.addToCart(order);
        shoppingCart.getItems().removeAll(getOrders());
        shoppingCart.setItems(getOrders());
        totalLabel.setText(cart.getDf().format(cart.getTotalPrice()));
        taxesLabel.setText(cart.getDf().format(cart.getTax()));
        totalPlusTaxes.setText(cart.getDf().format(cart.calcTotalPlusTax()));
    }

    @FXML
    void orderLemonade() {
        Order order = new Order(1, cart.menu[26].getOrderName(), cart.menu[26].getOrderPrice());
        cart.addToCart(order);
        shoppingCart.getItems().removeAll(getOrders());
        shoppingCart.setItems(getOrders());
        totalLabel.setText(cart.getDf().format(cart.getTotalPrice()));
        taxesLabel.setText(cart.getDf().format(cart.getTax()));
        totalPlusTaxes.setText(cart.getDf().format(cart.calcTotalPlusTax()));
    }

    @FXML
    void orderIcedTea() {
        Order order = new Order(1, cart.menu[27].getOrderName(), cart.menu[27].getOrderPrice());
        cart.addToCart(order);
        shoppingCart.getItems().removeAll(getOrders());
        shoppingCart.setItems(getOrders());
        totalLabel.setText(cart.getDf().format(cart.getTotalPrice()));
        taxesLabel.setText(cart.getDf().format(cart.getTax()));
        totalPlusTaxes.setText(cart.getDf().format(cart.calcTotalPlusTax()));
    }

    @FXML
    void orderWater() {
        Order order = new Order(1, cart.menu[28].getOrderName(), cart.menu[28].getOrderPrice());
        cart.addToCart(order);
        shoppingCart.getItems().removeAll(getOrders());
        shoppingCart.setItems(getOrders());
        totalLabel.setText(cart.getDf().format(cart.getTotalPrice()));
        taxesLabel.setText(cart.getDf().format(cart.getTax()));
        totalPlusTaxes.setText(cart.getDf().format(cart.calcTotalPlusTax()));
    }

    @FXML
    void orderRootBeer() {
        Order order = new Order(1, cart.menu[29].getOrderName(), cart.menu[29].getOrderPrice());
        cart.addToCart(order);
        shoppingCart.getItems().removeAll(getOrders());
        shoppingCart.setItems(getOrders());
        totalLabel.setText(cart.getDf().format(cart.getTotalPrice()));
        taxesLabel.setText(cart.getDf().format(cart.getTax()));
        totalPlusTaxes.setText(cart.getDf().format(cart.calcTotalPlusTax()));
    }

    @FXML
    void placeOrder(ActionEvent event) {
        // check all inputs first
        if(deliveryCheckBox.isSelected() == true) {
            if (deliveryPhoneNumberTextField.getText().trim().isEmpty() || deliveryPhoneNumberTextField.getText().length() > 14 || deliveryPhoneNumberTextField.getText().length() < 9 || deliveryAddressTextField.getText().trim().isEmpty()
                    || deliveryCCbox.isSelected() == false && deliveryCashbox.isSelected() == false) {
                Alert alert = new Alert(Alert.AlertType.ERROR); // alert box if blank fields
                alert.setTitle("Error");
                alert.setContentText("Invalid Field.");
                alert.setContentText("Invalid Field.");String mediaFile = "Sample/src/Sounds/ErrorSound.wav";
                errorSound.setVolume(errorSound.getVolume()+ 50000);
                errorSound.play();
                alert.showAndWait();
            }
            else  if (deliveryCCbox.isSelected() == true){
                if(deliveryCCnumTextField.getText().trim().isEmpty() || deliveryCCnumTextField.getText().length() < 16 || deliveryCCnumTextField.getText().length() > 20 || deliveryCVVTextField.getText().trim().isEmpty()
                        || expYearDropBox.getSelectionModel().isEmpty() || ExpMonthDropBox.getSelectionModel().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Invalid Field.");
                    alert.setContentText("Invalid Field.");String mediaFile = "Sample/src/Sounds/ErrorSound.wav";
                    errorSound.setVolume(errorSound.getVolume()+ 50000);
                    errorSound.play();
                    alert.showAndWait();
                }
                else {

                    String SQL1 = "UPDATE customers set phone = '" + deliveryPhoneNumberTextField.getText() + "' WHERE customerID = " + customer.getcID();
                    String SQL2 = "UPDATE customers set address = '" + deliveryAddressTextField.getText() + "'WHERE customerID =" + customer.getcID();
                    String SQL3 = "UPDATE customers set creditCard = '" + deliveryCCnumTextField.getText() + "' WHERE customerID = " + customer.getcID();
                    try {
                        db.statement.executeUpdate(SQL1);
                        db.statement.executeUpdate(SQL2);
                        db.statement.executeUpdate(SQL3);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    for (int i = 0; i < cart.getDbnumorders(); i++) {
                        int temp = 1;
                        String SQL = "INSERT INTO orders(orderID, customerID, menuID, quantity) VALUES ('" + temp + "','" + customer.getcID() + "','" + (cart.findMenuID(i) + 1) + "','" + cart.getQuantityofdbOrders(i) + "')";
                        System.out.println(SQL + "\n");
                        try {
                            db.statement.executeUpdate(SQL);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    switchUI("CheckOutPane.fxml", totalPlusTaxes);
                }
            }
            else if (deliveryCashbox.isSelected() == true){
                for (int i = 0; i < cart.getDbnumorders(); i++) {
                    int temp = 1;
                    String SQL = "INSERT INTO orders(orderID, customerID, menuID, quantity) VALUES ('" + temp + "','" + customer.getcID() + "','" + (cart.findMenuID(i) + 1) + "','" + cart.getQuantityofdbOrders(i) + "')";
                    System.out.println(SQL + "\n");
                    try {
                        db.statement.executeUpdate(SQL);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                switchUI("CheckOutPane.fxml", totalPlusTaxes);

            }
        }

        else if (pickupCheckBox.isSelected() == true) {
            if (pickupPhoneTextField.getText().trim().isEmpty() || pickupPhoneTextField.getText().length() > 14 || pickupPhoneTextField.getText().length() < 9) {
                Alert alert = new Alert(Alert.AlertType.ERROR); // alert box if blank fields
                alert.setTitle("Error");
                alert.setContentText("Invalid Field.");
                alert.setContentText("Invalid Field.");String mediaFile = "Sample/src/Sounds/ErrorSound.wav";
                errorSound.setVolume(errorSound.getVolume()+ 50000);
                errorSound.play();
                alert.showAndWait();

            } else {
                String SQL3 = "UPDATE customers set phone = '" + pickupPhoneTextField.getText() + "' WHERE customerID = " + customer.getcID();
                try {
                    db.statement.executeUpdate(SQL3);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < cart.getDbnumorders(); i++) {
                    int temp = 1;
                    String SQL = "INSERT INTO orders(orderID, customerID, menuID, quantity) VALUES ('" + temp + "','" + customer.getcID() + "','" + (cart.findMenuID(i) + 1) + "','" + cart.getQuantityofdbOrders(i) + "')";
                    System.out.println(SQL + "\n");
                    try {
                        db.statement.executeUpdate(SQL);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                switchUI("CheckOutPane.fxml", totalPlusTaxes);


            }
        }

        }


    }
