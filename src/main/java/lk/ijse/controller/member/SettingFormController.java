package lk.ijse.controller.member;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import lk.ijse.bo.custom.SettingBO;
import lk.ijse.bo.custom.impl.SettingBOImpl;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import static lk.ijse.controller.member.LoginFormController.user;

public class SettingFormController implements Initializable {

    @FXML
    private Circle circle;

    @FXML
    private TextField txtCurrentP;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNewP;

    @FXML
    private TextField txtReEnPa;
    public static Image image;
    @FXML
    private Label lblName;

    @FXML
    private Label lblUserName;
    public static   String user_name;

    SettingBO settingBO = new SettingBOImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//       lblName.setText(user.getName());
//        lblUserName.setText(user.getUserName());

//        lblName.setText(LoginFormController.user_name);

    }
    @FXML
    void PasswordOnAction(MouseEvent event) {

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


