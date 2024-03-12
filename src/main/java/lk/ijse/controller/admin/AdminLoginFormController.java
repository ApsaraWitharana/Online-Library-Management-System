package lk.ijse.controller.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.dao.custom.AdminDAO;
import lk.ijse.dao.custom.impl.AdminDAOImpl;

import java.io.IOException;

public class AdminLoginFormController {

    public static   String name;
    public static   String password;

    @FXML
    private TextField txtName;

    @FXML
    private PasswordField txtPassword;

    AdminDAO adminDAO = (AdminDAO) new AdminDAOImpl();
    @FXML
    void btnLoginOnActino(ActionEvent event) throws IOException {


//        name = txtName.getText();
//        password = txtPassword.getText();
//
//        Stage stage = new Stage();
//        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/admin/dashboardForm.fxml"))));
//        stage.setTitle("Admin login dashboard");
//        stage.show();

        boolean isCorrectUser = adminDAO.isCurrectUser(txtName.getText(),txtPassword.getText());

        System.out.println("admin is  save");

        if (isCorrectUser) {
            Parent root = FXMLLoader.load(getClass().getResource("/view/admin/dashboardForm.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();

        } else {
            //new Alert(Alert.AlertType.WARNING,"Wrong Password").show();
            txtPassword.setStyle("-fx-border-color: red;");


        }
    }

}
