package lk.ijse.controller.member;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.custom.SignupBO;
import lk.ijse.bo.custom.impl.SignupBOImpl;
import lk.ijse.dto.UserDTO;

import java.io.IOException;

public class SignupFormController {

    @FXML
    private Button btnSignUp;

    @FXML
    private TextField txEmail;

    @FXML
    private TextField txtConfromPassword;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    private AnchorPane signupPane;

    SignupBO signupBO = new SignupBOImpl();
    @FXML
    void btnSignupOnAction(ActionEvent event) throws IOException {
//        Parent anchorPane = FXMLLoader.load(getClass().getResource("/view/loginForm.fxml"));
//        Scene scene = new Scene(anchorPane);
//
//        Stage stage = new Stage();
//        stage.setTitle("Return Book Manage");
//        stage.setScene(scene);
//        stage.centerOnScreen();
//        stage.show();
//        signupPane.getScene().getWindow().hide();

        boolean isSaved = false;
        if (txtPassword.getText().equals(txtConfromPassword.getText())){

            System.out.println("in");
            try {
                UserDTO userDTO = new UserDTO(txtUserName.getText(), txEmail.getText(), txtPassword.getText());
                isSaved = signupBO.saveUser(userDTO);


                if (isSaved) {
                    Parent root = FXMLLoader.load(getClass().getResource("/view/member/loginForm.fxml"));
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.centerOnScreen();
                    stage.show();

                } else {
                    new Alert(Alert.AlertType.WARNING, "Wrong Password").show();

                    txtUserName.clear();
                    txtPassword.clear();
                    txEmail.clear();

                }
            }catch (Exception e){
                e.printStackTrace();
            }


        }else {

            new Alert(Alert.AlertType.WARNING, "passwords doesn't match").show();

        }

    }

}
