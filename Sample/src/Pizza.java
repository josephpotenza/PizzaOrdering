
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

public class Pizza extends Application{
    static Scanner reader;
    public void start(Stage primaryStage){
        try{
            Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
            Parent root = FXMLLoader.load(getClass().getResource("/LoginPane.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("The Best Project");
            primaryStage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        primaryStage.setMaximized(true);
        primaryStage.setResizable(true);
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
                customer.login();

            }else{
                Customer customer = new Customer();
                customer.createAccount();
            }


        }while (choice != 3) ;

        */
    }
}





