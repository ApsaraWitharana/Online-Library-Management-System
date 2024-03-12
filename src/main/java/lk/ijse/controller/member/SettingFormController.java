package lk.ijse.controller.member;

import com.ctc.wstx.shaded.msv_core.grammar.util.NameClassComparator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import lk.ijse.bo.custom.SettingBO;
import lk.ijse.bo.custom.impl.SettingBOImpl;
import org.mindrot.jbcrypt.BCrypt;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import static lk.ijse.controller.member.LoginFormController.user;
import static org.mindrot.jbcrypt.BCrypt.*;

public class SettingFormController implements Initializable {

    @FXML
    private Circle circle;

    @FXML
    private PasswordField txtCurrPass;

    @FXML
    private TextField txtCurrentPass;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNewPassword;

    @FXML
    private PasswordField txtPass;

    @FXML
    private PasswordField txtReEnter;

    @FXML
    private TextField txtconfirmValid;

    public static Image image;
    @FXML
    private Label lblName;

    @FXML
    private Label lblUserName;
    public static   String user_name;

    SettingBO settingBO = new SettingBOImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      lblName.setText(user.getName());
//        lblUserName.setText(user.getUserName());

//        lblName.setText(LoginFormController.user_name);

    }
    @FXML
    void PasswordOnAction(MouseEvent event) {
        if(txtCurrPass.isVisible()) {
            txtCurrPass.setVisible(false);
            txtCurrentPass.setVisible(true);
            txtCurrentPass.setText(txtCurrPass.getText());
            txtCurrentPass.setEditable(false);
        }else{
            txtCurrPass.setVisible(true);
            txtCurrentPass.setVisible(false);
        }
        if(txtPass.isVisible()) {
            txtPass.setVisible(false);
            txtNewPassword.setVisible(true);
            txtNewPassword.setText(txtPass.getText());
            txtNewPassword.setEditable(false);
        }else{
            txtPass.setVisible(true);
            txtNewPassword.setVisible(false);
        }
        if(txtReEnter.isVisible()) {
            txtReEnter.setVisible(false);
            txtconfirmValid.setVisible(true);
            txtconfirmValid.setText(txtReEnter.getText());
            txtconfirmValid.setEditable(false);
        }else{
            txtReEnter.setVisible(true);
            txtconfirmValid.setVisible(false);
        }

    }

    public void passwordOnAction(javafx.event.ActionEvent event) {
        System.out.println(txtCurrPass.getText());
        System.out.println(user.getPassword());
        if (!txtCurrPass.getText().isEmpty() && !txtPass.getText().isEmpty() && !txtReEnter.getText().isEmpty()){
            if (BCrypt.checkpw(txtCurrPass.getText(),user.getPassword())){
                if (txtPass.getText().equals(txtReEnter.getText())){
                    String hashed = BCrypt.hashpw(txtReEnter.getText(),BCrypt.gensalt());
                    boolean isUpdate = settingBO.updatePassword(hashed,user.getName());
                    if (isUpdate){
                        new Alert(Alert.AlertType.CONFIRMATION,"Update password successfully!!").show();

                    }else
                        new Alert(Alert.AlertType.ERROR,"Update password failed!!").show();

                }else
                    new Alert(Alert.AlertType.ERROR,"password doesn't match ! please re-enter").show();
            }else
                new Alert(Alert.AlertType.ERROR,"Current Password is incorrect").show();

        }else
            new Alert(Alert.AlertType.ERROR,"Please fill up all fields!").show();
    }

    @FXML
    void UserOnAction(MouseEvent event) {

        if(!txtName.getText().isEmpty()) {
            boolean isUpdated =settingBO.updateUserName(txtName.getText(), user.getName());
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated User Name Successfully!").show();
            } else
                new Alert(Alert.AlertType.ERROR, "Update User Name Failed!").show();
        }else
            new Alert(Alert.AlertType.ERROR, "Please enter user name!").show();

    }

    @FXML
    void profileOnAction(MouseEvent actionEvent) {
        Window window = ((Node) (actionEvent.getSource())).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(window);
        actionEvent.consume();
        try {
            InputStream in = new FileInputStream(file);
            image = new Image(in);
            circle.setFill(new ImagePattern(image));

        } catch (FileNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        if(image==null) {
            image = new Image("/image/images-wp-profile.jpeg");
        }
        circle.setFill(new ImagePattern(image));
    }



}


