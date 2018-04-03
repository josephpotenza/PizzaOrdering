
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



    public static void main(String[] args){
        launch(args);
        /*Scanner read = new Scanner(System.in);
        System.out.print("First Name:");
        String fn;
        fn = read.next();
        System.out.print("Last Name:");
        String ln;
        ln = read.next();
        System.out.print("Phone #");
        String pn;
        pn = read.next();
        System.out.print("Email");
        String em;
        em = read.next();
*/
        // this is a working extraction method to pull info from customers table
        // your welcome -Joe :)
        try {
            String host = "jdbc:mysql://18.221.69.113:3306/pizzaOrdering";
            String uName = "root";
            String uPass = "csc430db";
            Connection con = DriverManager.getConnection(host, uName, uPass);
            Statement stmt = con.createStatement( );
            // this is a working insertion method to insert user input to db.
          //  String SQL = "Insert INTO customers(LastName, FirstName, phone, email) VALUES ('"+ln+"','"+fn+"','"+pn+"','"+em+"')";
            //stmt.executeUpdate(SQL);
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

