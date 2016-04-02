package Login;

import WebUtilities.LoginRes;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
        /*
        if(LoginRes.success == true && LoginRes.admin == true){

        }else if(LoginRes.success == true && LoginRes.admin == false){

        }
        */
    }


    public static void main(String[] args) {
        launch(args);
    }
}
