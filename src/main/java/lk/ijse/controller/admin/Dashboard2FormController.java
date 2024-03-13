package lk.ijse.controller.admin;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.utile.member.NavigationMember;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class Dashboard2FormController implements Initializable {

    @FXML
    private Circle circle;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private AnchorPane txtAnchorPane;

    @FXML
    private AnchorPane txtAnchorPane1;
    public static Image image;

    @FXML
    void btnBookManageOnAction(ActionEvent event) throws IOException {
       NavigationMember.switchPaging(txtAnchorPane1,"booksForm.fxml","Books");

    }

    @FXML
    void btnBrrowanBookOnAction(ActionEvent event) throws IOException {
       NavigationMember.switchPaging(txtAnchorPane1,"bookDetailsForm.fxml","Books");

    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) throws IOException {

        Parent anchorPane = FXMLLoader.load(getClass().getResource("/view/admin/adminAccountForm.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = new Stage();
        stage.setTitle("Setting book manage");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
        //NavigationMember.switchPaging(txtAnchorPane1,"adminAccountForm.fxml","Dashboard");

    }

    @FXML
    void btnImageOnAction(MouseEvent actionEvent) {
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
    void btnLibraryOnAction(ActionEvent event) throws IOException {
        NavigationMember.switchPaging(txtAnchorPane1,"libraryForm1.fxml","Library");

    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) throws IOException {

        Parent anchorPane = FXMLLoader.load(getClass().getResource("/view/admin/adminloginForm.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = new Stage();
        stage.setTitle("Signup Book Manage");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

        txtAnchorPane1.getScene().getWindow().hide();
    }

    @FXML
    void btnRegistationOnAction(ActionEvent event) throws IOException {
        NavigationMember.switchPaging(txtAnchorPane1,"userRegistratiionForm.fxml","User Registration");

    }

    @FXML
    void btnReturnOnActino(ActionEvent event) throws IOException {
        NavigationMember.switchPaging(txtAnchorPane1,"returnBookFormm.fxml","Return Manage");

    }

    @FXML
    void btnUserOnAction(ActionEvent event) throws IOException {
       NavigationMember.switchPaging(txtAnchorPane1,"UserForm.fxml","User Manage");

    }

    private void timenow(Label time,Label date) {
        Thread thread = new Thread(() ->{
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
            SimpleDateFormat sdf1 = new SimpleDateFormat("MMM, dd, yyy");
            while (true){
                try {
                    Thread.sleep(1000);
                }catch (Exception e){
                    System.out.println(e);
                }
                final String timenow = sdf.format(new Date());
                String timenow1 = sdf1.format(new Date());

                Platform.runLater(() ->{
                    time.setText(timenow);
                    date.setText(timenow1);

                });
            }
        });
        thread.start();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        timenow(lblDate,lblTime);
    }
}
