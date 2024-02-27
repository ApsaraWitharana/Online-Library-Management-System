package lk.ijse.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import lk.ijse.utile.Navigation;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;



public class HomeFormController implements Initializable {
    @FXML
    private Circle circle;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private AnchorPane txtAnchorPane;
    public static Image image;

    @FXML
    void BookManageOnAction(MouseEvent event) throws IOException {
        Navigation.switchPaging(txtAnchorPane,"booksForm.fxml","Books");

    }

    @FXML
    void BorrowinBooksOnActiion(MouseEvent event) {

    }

    @FXML
    void LibraryManageOnAction(MouseEvent event) {

    }

    @FXML
    void LogOutIOnAction(MouseEvent event) {

    }

    @FXML
    void LogOutOnAction(MouseEvent event) {

    }

    @FXML
    void ReturnBooksOnAction(MouseEvent event) {

    }

    @FXML
    void UserManageOnActiion(MouseEvent event) {

    }

    @FXML
    void dashboardOnAction(MouseEvent event) throws IOException {
        Navigation.switchPaging(txtAnchorPane,"dashboardForm.fxml","Dashboard");

    }

    @FXML
    void registrationOnAction(MouseEvent event) {

    }

    public void initialize(URL url , ResourceBundle resourceBundle){
        timenow(lblDate,lblTime);

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
    }


