import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class PizzaGuiFX extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    /*
    //Example Button

    Button button = new button();
    button.setText("I am a Button");

    button.setOnAction(e -> {
        System.out.println("InnerClass Event Handler));
        System.out.println("InnerClass Event Handler with 2 lines));
    }
     */


    Stage stage;
    Scene login, loginFail, menu;


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("PizzaGUI");
    }

}
