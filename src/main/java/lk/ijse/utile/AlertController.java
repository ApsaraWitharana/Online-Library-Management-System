package lk.ijse.utile;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Optional;

public class AlertController {
    public static void errormessage(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);

        alert.getDialogPane().setPrefSize(300, 150); // Set the size of the alert dialog pane
        alert.getDialogPane().setStyle("-fx-background-color: grey;"); // Set the background color of the alert dialog pane
        alert.getDialogPane().setHeaderText(null); // Remove the header text from the alert dialog pane

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/image/wrongicon.png"));
        ButtonType cancelButton = new ButtonType("Ok", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(cancelButton);

        alert.showAndWait();
    }

    public static void confirmmessage(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);

        alert.getDialogPane().setPrefSize(300, 150); // Set the size of the alert dialog pane
        alert.getDialogPane().setStyle("-fx-background-color: grey;"); // Set the background color of the alert dialog pane
        alert.getDialogPane().setHeaderText(null); // Remove the header text from the alert dialog pane

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/image/check.png"));
        ButtonType cancelButton = new ButtonType("Ok", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(cancelButton);

        alert.showAndWait();
    }

    public static boolean okconfirmmessage(String msg){
        Alert alert= new Alert(Alert.AlertType.NONE);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText(msg);

        alert.getDialogPane().setPrefSize(300, 150); // Set the size of the alert dialog pane
        alert.getDialogPane().setStyle("-fx-background-color: linear-gradient(to right, #052647, #011c32); "); // Set the background color of the alert dialog pane
        alert.getDialogPane().setHeaderText(null); // Remove the header text from the alert dialog pane
        alert.getDialogPane().lookup(".content.label").setStyle("-fx-text-fill: white");

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/image/tick.png"));
        ButtonType okButton = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(okButton,cancelButton);

        Optional<ButtonType> result = alert.showAndWait();
        if(result.orElse(cancelButton) == okButton){
            return true;
        }
        return false;
    }

    public static void warningmessage(String msg){
        Alert alert= new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(msg);

        alert.getDialogPane().setPrefSize(300, 150); // Set the size of the alert dialog pane
        alert.getDialogPane().setStyle("-fx-background-color: linear-gradient(to right, #052647, #011c32); "); // Set the background color of the alert dialog pane
        alert.getDialogPane().setHeaderText(null); // Remove the header text from the alert dialog pane
        alert.getDialogPane().lookup(".content.label").setStyle("-fx-text-fill: white");

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/image/wrongicon.png"));
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(cancelButton);

        alert.showAndWait();
    }
}
