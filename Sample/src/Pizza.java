
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
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
    static Scanner reader;
    protected void startUI(){
        System.out.println("UI");
    }
    public void start(Stage primaryStage){

        try{
            Parent root = FXMLLoader.load(getClass().getResource("/LoginPane.fxml"));
            Scene scene = new Scene(root, 1600,900);
            primaryStage.setScene(scene);
            primaryStage.setTitle("The Best Project");
            primaryStage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        primaryStage.setResizable(false);


    }


    public static void main(String[] args) {
        launch(args);
        reader = new Scanner(System.in);
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
        }
    }





