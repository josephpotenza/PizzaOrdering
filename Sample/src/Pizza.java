import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Pizza extends Application{

    public void start(Stage primaryStage){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/GuiFX.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        primaryStage.setMaximized(true);
        primaryStage.setResizable(false);
    }



    public static void main(String[] args){
        launch(args);

        // this is a working extraction method to pull info from customers table
        // your welcome -Joe :)
        try {
            String host = "jdbc:mysql://localhost:3306/pizzaOrdering";
            String uName = "root";
            String uPass = "joepot95";
            Connection con = DriverManager.getConnection(host, uName, uPass);
            Statement stmt = con.createStatement( );
            String SQL = "SELECT * FROM customers";
            ResultSet rs = stmt.executeQuery(SQL);

            while ( rs.next( ) ) {

                int id_col = rs.getInt("customerID");
                String first_name = rs.getString("FirstName");
                String last_name = rs.getString("LastName");
                String phone = rs.getString( "phone");
                String email = rs.getString( "email");
                System.out.println("Customer ID: " + id_col + " Name: " + first_name + " " +last_name);
                System.out.println("Email: " + email + " Phone: "+ phone);
            }
        }
        catch (SQLException err) {
            System.out.println(err.getMessage());
        }


        }
    }

