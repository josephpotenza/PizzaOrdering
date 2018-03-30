import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.*;
import java.sql.DriverManager;

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
    }


    public static void main(String[] args){
        launch(args);
    }
}

//hello world Christian Posada