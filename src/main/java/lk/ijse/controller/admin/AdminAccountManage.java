
package lk.ijse.controller.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.bo.custom.AdminBO;
import lk.ijse.bo.custom.impl.AdminBOImpl;
import lk.ijse.entity.Admin;
import lk.ijse.entity.User;
import org.mindrot.jbcrypt.BCrypt;

import java.awt.*;
import java.io.*;
import java.util.Optional;

public class AdminAccountManage {

    @FXML
    private Button btnDelete;

    @FXML
    private Circle circle;

    @FXML
    private Label lblName;

    @FXML
    private TextField txtAdminName;

    @FXML
    private PasswordField txtCurrPass;

    @FXML
    private TextField txtCurrentPass;

    @FXML
    private TextField txtNewPass;

    @FXML
    private PasswordField txtPass;

    @FXML
    private PasswordField txtReEnter;

    @FXML
    private TextField txtconfirmValid;

    public static Admin admin;
     public static Image image;

     AdminBO adminBO = (AdminBO) new AdminBOImpl();
    @FXML
    void adminNameOnAction(ActionEvent event) {

        if(!txtAdminName.getText().isEmpty()) {
            boolean isUpdated = adminBO.updateAdminName(txtAdminName.getText(), admin.getName());
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated Admin Name Successfully!").show();
            } else
                new Alert(Alert.AlertType.ERROR, "Update Admin Name Failed!").show();
        }else
            new Alert(Alert.AlertType.ERROR, "Please enter admin name!").show();
    }


    @FXML
    void btnDeleteOnAction(ActionEvent event) throws IOException {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to delete?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            boolean isDeleted = adminBO.deleteAdmin(admin.getName());
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Admin Account Details Deleted!").show();
                admin=null;

                        Stage stage = new Stage();

                        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/admin/adminAccountForm.fxml"))));
                        stage.setTitle("admin");
                        stage.show();
//                OpenView.openView("loginForm",settingPane);
            }else
                new Alert(Alert.AlertType.ERROR, "Admin Account Delete Failed!").show();
        }
    }

    @FXML
    void editePasswordOnAction(MouseEvent event) {
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
            txtNewPass.setVisible(true);
            txtNewPass.setText(txtPass.getText());
            txtNewPass.setEditable(false);
        }else{
            txtPass.setVisible(true);
            txtNewPass.setVisible(false);
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

    @FXML
    void editeameOnAction(MouseEvent event) {

    }

    @FXML
    void setProfileOnAction(MouseEvent  actionEvent) {
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

    @FXML
    void txtPasswordOnAction(ActionEvent event) {
        System.out.println(txtCurrPass.getText());
        System.out.println(admin.getPassword());
        if(!txtCurrPass.getText().isEmpty() && !txtPass.getText().isEmpty() && !txtReEnter.getText().isEmpty()) {
            if (BCrypt.checkpw(txtCurrPass.getText(),admin.getPassword())) {
                if (txtPass.getText().equals(txtReEnter.getText())) {
                    String hashed = BCrypt.hashpw(txtReEnter.getText(), BCrypt.gensalt());
                    boolean isUpdated = adminBO.updatePassword(hashed, admin.getName());
                    if (isUpdated) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Updated Password Successfully!").show();
                    } else
                        new Alert(Alert.AlertType.ERROR, "Update Password Failed!").show();
                } else
                    new Alert(Alert.AlertType.ERROR, "Password doesn't match! Please re-enter").show();
            } else
                new Alert(Alert.AlertType.ERROR, "Current Password is incorrect").show();
        }else
            new Alert(Alert.AlertType.ERROR, "Please fill up all fields!").show();


    }

}










