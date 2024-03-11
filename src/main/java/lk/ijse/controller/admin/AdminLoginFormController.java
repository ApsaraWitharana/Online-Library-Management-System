package lk.ijse.controller.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminLoginFormController {

    public static   String name;
    public static   String password;

    @FXML
    private TextField txtName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    void btnLoginOnActino(ActionEvent event) throws IOException {


        name = txtName.getText();
        password = txtPassword.getText();

        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/admin/dashboardForm.fxml"))));
        stage.setTitle("Admin login dashboard");
        stage.show();

    }

}
