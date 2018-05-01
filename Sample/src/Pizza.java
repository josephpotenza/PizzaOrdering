
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static javafx.application.Application.launch;



public class Pizza extends Application{

    public fetchCustomer fetchCustomer;

    public void switchUI(String fileName, Label locator){
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fileName));
            Stage stage = (Stage) locator.getScene().getWindow();
            Scene scene = new Scene(root, 1600,900);
            stage.setScene(scene);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


    public ObservableList<Order> getOrders(){
        ObservableList<Order> orders = FXCollections.observableArrayList();
        for(int i = 0; i < cart.numOrders; i++){
            orders.add(cart.getOrder(i));
        }
        return orders;
    }

    Cart cart = new Cart();

    public Customer customer = new Customer();

    String mediaFile = "Sample/src/Sounds/ErrorSound.wav";
    Media media = new Media(new File(mediaFile).toURI().toString());
    MediaPlayer errorSound = new MediaPlayer(media);

    public void setCustomerInfo(String firstN, String lastN, String email, String pass, String address, String creditCard, String phone){
        this.customer.setFirstName(firstN);
        this.customer.setLastName(lastN);
        this.customer.setEmail(email);
        this.customer.setPass(pass);
        this.customer.setAddress(address);
        this.customer.setCreditCard(creditCard);
        this.customer.setPhone(phone);
        System.out.println("from Pizza setCustomer" + this.customer);
        getCustomer(this.customer);
    }

    public Customer getCustomer(Customer c) {
        System.out.println("from Pizza getCustomer with argument" + c);
        // should be able to call initailize method inside ordering panel controller
      //  fetchCustomer.Hello();
        return c;
    }



    public Customer getCustomer() {
        System.out.println("from Pizza getCustomer " + this.customer);
        return this.customer;
    }

    public String getCustomerFirstName(){
        return customer.getFirstName();
    }

    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/LoginPane.fxml"));
            Scene scene = new Scene(root, 1600, 900);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Pizza Ordering");
            primaryStage.show();
            System.out.println("from Pizza" + this.customer);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        primaryStage.setResizable(false);
    }


    public static void main(String[] args) {
        launch(args);
        /*reader = new Scanner(System.in);
        int choice = 0;
        do {
            System.out.println("1. Customer 2. createAccount");
            choice = reader.nextInt();
            if (choice == 1) {
                Customer customer = new Customer();
                if (customer.login()) {
                    if (customer.getValidation().equals("true")) {
                        System.out.println("Login Successful!");
                        //then show menu

                    }
                } else {
                    System.out.println("invalid login. Would you like to create an account? press 3");
                    choice = reader.nextInt();
                    if (choice == 3) {
                        customer.createAccount();
                    }
                }


            }
            if (choice == 2){
                Customer customer = new Customer();
                customer.createAccount();
                //show menu
            }
        }while (choice != 4) ;
        }*/
    }
}





