package QuestionForm;

import AdminInterface.AdminInterfaceController;
import StudentInterface.StudentInterfaceController;
import WebUtilities.AdminQuestionReq;
import WebUtilities.AdminQuestionRes;
import WebUtilities.LoginRes;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by crims_000 on 4/1/2016.
 */
public class QuestionFormController {
    public Button adminSubmitButton;
    public TextArea adminQuestionForm;
    public TextField adminAnswerOneText;
    public TextField adminAnswerTwoText;
    public TextField adminAnswerThreeText;
    public TextField adminAnswerFourText;
    public RadioButton adminAnswerRButtonOne;
    public RadioButton adminAnswerRButtonTwo;
    public RadioButton adminAnswerRButtonThree;
    public RadioButton adminAnswerRButtonFour;
    public int correctQuestion;

    public void initForm(){
        ToggleGroup questionGroup = new ToggleGroup();
        adminAnswerRButtonOne.setToggleGroup(questionGroup);
        adminAnswerRButtonOne.setSelected(true);

        adminAnswerRButtonTwo.setToggleGroup(questionGroup);
        adminAnswerRButtonThree.setToggleGroup(questionGroup);
        adminAnswerRButtonFour.setToggleGroup(questionGroup);
    }

    public void submitAdminQuestion(ActionEvent actionEvent) {
        AdminQuestionReq adminQuestion = new AdminQuestionReq();

        adminQuestion.adminQuestion = adminQuestionForm.getText();
        String[] questions = new String[] {adminAnswerOneText.getText(), adminAnswerTwoText.getText(), adminAnswerThreeText.getText(), adminAnswerFourText.getText()};
        adminQuestion.adminOptions = questions;

        if(adminAnswerRButtonOne.isSelected() == true){
            correctQuestion = 0;
        }else if(adminAnswerRButtonTwo.isSelected() == true){
            correctQuestion = 1;
        }else if(adminAnswerRButtonThree.isSelected() == true){
            correctQuestion = 2;
        }else if(adminAnswerRButtonFour.isSelected() == true){
             correctQuestion =3;
        }else{
            System.out.println("Please Select the Create Answer");
            return;
        }
        adminQuestion.adminCorrectAnswer = correctQuestion;

        try{
            Socket clientSocket = new Socket("127.0.0.1", 3000);
            ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream inFromServer = new ObjectInputStream(clientSocket.getInputStream());
            outToServer.writeObject(adminQuestion);

            AdminQuestionRes adminquestionResponse = (AdminQuestionRes) inFromServer.readObject();

            System.out.println(adminquestionResponse.success);

            clientSocket.close();
        }catch(Exception e) {
            System.out.println(e);
        }

    }
}
