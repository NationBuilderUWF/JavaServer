package Login;

import AdminInterface.AdminInterfaceController;
import MapRender.MapRenderController;
import StudentInterface.StudentInterfaceController;
import WebUtilities.LoginReq;
import WebUtilities.LoginRes;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class LoginController {

    public AnchorPane rootPane;
    public Button loginButton;
    public TextField userNameField;
    public TextField passwordTextField;

    public void loginCheck(ActionEvent actionEvent) {
        LoginReq login = new LoginReq();
        LoginRes loginResponse;

        login.username = userNameField.getText();
        login.password = passwordTextField.getText();

        try{
            Socket clientSocket = new Socket("127.0.0.1", 3000);
            ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream inFromServer = new ObjectInputStream(clientSocket.getInputStream());
            outToServer.writeObject(login);

            loginResponse = (LoginRes) inFromServer.readObject();

            System.out.println(loginResponse.success);
            System.out.println(loginResponse.admin);

            int sceneSizeX = (int)MapRenderController.width;
            int sceneSizeY = (int)MapRenderController.height + 100;
            if(loginResponse.success == true && loginResponse.admin == true){
                Stage stage = (Stage) rootPane.getScene().getWindow();
                stage.setResizable(false);
                stage.close();
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("AdminInterface/AdminInterface.fxml"));
                Parent root = loader.load();
                stage.setTitle("[Enter Game Name Here] Admin Console");
                stage.setScene(new Scene(root, sceneSizeX, sceneSizeY));
                stage.show();
                AdminInterfaceController controller = loader.getController();
                controller.loadInterface();

            }else if(loginResponse.success == true && loginResponse.admin == false){
                Stage stage = (Stage) rootPane.getScene().getWindow();
                stage.close();
                stage.setResizable(false);
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("StudentInterface/StudentInterface.fxml"));
                Parent root = loader.load();
                stage.setTitle("[Enter Game Name Here] Student Console");
                stage.setScene(new Scene(root, sceneSizeX, sceneSizeY));
                stage.show();
                StudentInterfaceController controller = loader.getController();
                controller.loadInterface();
            }
            clientSocket.close();
        }catch(Exception e) {
            System.out.println(e);
        }
    }
}
