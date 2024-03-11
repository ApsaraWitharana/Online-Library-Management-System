package lk.ijse.utile.admin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdminAppInitializer extends Application {
    public static void main(String[] args) {

     launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/admin/adminloginForm.fxml"));
        stage.setScene(new Scene(root));

        stage.show();
    }

}
