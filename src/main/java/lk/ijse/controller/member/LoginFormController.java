package lk.ijse.controller.member;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.IssueBookBO;
import lk.ijse.bo.custom.LoginBO;
import lk.ijse.bo.custom.impl.LoginBOImpl;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.bo.custom.impl.UserBOImpl;
import lk.ijse.dto.UserDTO;
import lk.ijse.entity.User;
import org.mindrot.jbcrypt.BCrypt;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class LoginFormController {

    public AnchorPane logPane;
    @FXML
    private Button btnLogin;

    @FXML
    private Button btnSignup;
    public static User user;
    @FXML
    private ImageView imgCloseEye;

    @FXML
    private ImageView imgOpenEye;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassVisible;

    @FXML
    private PasswordField txtPassword1;
    public static User loginUser;
    public static Long userID;
   public static   String user_name;
   public static   String password;
    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.USER);
    LoginBO loginBO = new LoginBOImpl();

    @FXML
    void logInOnAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        // boolean isCorrectUser = loginBO.isCurrectUser(txtName.getText(),txtPassword1.getText());


        user_name = txtName.getText();
        password = txtPassword1.getText();
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/member/homeForm.fxml"))));
        stage.setTitle("Chat Room");
        stage.show();


            //System.out.println(isCorrectUser);

//        if (isCorrectUser) {
//            Parent root = FXMLLoader.load(getClass().getResource("/view/homepage_from.fxml"));
//            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            stage.centerOnScreen();
//            stage.show();
//
//        } else {
//            //new Alert(Alert.AlertType.WARNING,"Wrong Password").show();
//            txtPassword1.setStyle("-fx-border-color: red;");
//
//
//        }


//        if (!txtName.getText().isEmpty() && !txtPassword1.getText().isEmpty()){
//
//            List<String> userNameLists = loginBO.getUserNameList();
//
//            for (String user_name : userNameLists){
//                if (txtName.getText().equals(user_name)) {
//                    String password = loginBO.getPassword(user_name);
//                    if (BCrypt.checkpw(txtPassword1.getText(), password)) {
//                        user = loginBO.getUser(user_name);
//
//                        Stage stage = new Stage();
//
//                        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/member/homeForm.fxml"))));
//                        stage.setTitle("book");
//                        stage.show();
//
//
//                    } else
//                        new Alert(Alert.AlertType.ERROR, "Password mismatched").show();
//
//                }else
//                    new Alert(Alert.AlertType.ERROR,"Invalid User Name!").show();
//            }
//        }else
//            new Alert(Alert.AlertType.ERROR,"Please fill up all fields!").show();


    }




    @FXML
    void passOnAction(MouseEvent event) {
        if(txtPassword1.isVisible()) {
            imgCloseEye.setVisible(false);
            imgOpenEye.setVisible(true);
            txtPassword1.setVisible(false);
            txtPassVisible.setVisible(true);
            txtPassVisible.setText(txtPassword1.getText());
            txtPassVisible.setEditable(false);
        }else{
            imgCloseEye.setVisible(true);
            imgOpenEye.setVisible(false);
            txtPassword1.setVisible(true);
            txtPassVisible.setVisible(false);
        }
        imgCloseEye.setStyle("-fx-color: green");
        imgOpenEye.setStyle("-fx-color: red");
    }

    @FXML
    void signUpOnAction(ActionEvent event) throws IOException {
        Parent anchorPane = FXMLLoader.load(getClass().getResource("/view/member/signupForm.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = new Stage();
        stage.setTitle("Signup Book Manage");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

        logPane.getScene().getWindow().hide();
    }

    public void txtPasswordOnKeyTyped(KeyEvent keyEvent) {

        txtPassword1.setStyle("-fx-border-color: transparent;");
    }

}
