package lk.ijse.controller.member;

import javafx.application.Platform;
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
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.utile.member.NavigationMember;
import org.w3c.dom.Text;

import javax.swing.text.html.ImageView;
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
    private Text txtId;
    @FXML
    private Label lblTime;
    @FXML
    private Text text;

    @FXML
    private AnchorPane txtAncepane2;
    @FXML
    private Rectangle pane2;

    @FXML
    private Rectangle txtPane2;

    @FXML
    private ImageView setting;

    @FXML
    private AnchorPane txtAnchorPane;

    public static Image image;

    @FXML
    void settingOnAction(MouseEvent event) throws IOException {
        Parent anchorPane = FXMLLoader.load(getClass().getResource("/view/member/settingForm.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = new Stage();
        stage.setTitle("Setting Book Manage");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

    }

    @FXML
    void BookManageOnAction(MouseEvent event) throws IOException {
        NavigationMember.switchPaging(txtAnchorPane,"booksForm.fxml","Books");

    }

    @FXML
    void BorrowinBooksOnActiion(MouseEvent event) throws IOException {
        NavigationMember.switchPaging(txtAnchorPane,"bookDetailsForm.fxml","Books");

    }

    @FXML
    void LibraryManageOnAction(MouseEvent event) throws IOException {
        NavigationMember.switchPaging(txtAnchorPane,"libraryForm1.fxml","Library");

    }

    @FXML
    void LogOutIOnAction(MouseEvent event) throws IOException {
        Parent anchorPane = FXMLLoader.load(getClass().getResource("/view/member/loginForm.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = new Stage();
        stage.setTitle("Signup Book Manage");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

    }

    @FXML
    void LogOutOnAction(MouseEvent event) throws IOException {
        Parent anchorPane = FXMLLoader.load(getClass().getResource("/view/member/loginForm.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = new Stage();
        stage.setTitle("Signup Book Manage");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

        txtAnchorPane.getScene().getWindow().hide();
    }

    @FXML
    void ReturnBooksOnAction(MouseEvent event) throws IOException {
        NavigationMember.switchPaging(txtAnchorPane,"returnBookFormm.fxml","User Manage");

    }

    @FXML
    void UserManageOnActiion(MouseEvent event) throws IOException {
        NavigationMember.switchPaging(txtAnchorPane,"UserForm.fxml","User Manage");

    }

    @FXML
    void dashboardOnAction(MouseEvent event) throws IOException {
        NavigationMember.switchPaging(txtAnchorPane,"dashboardForm.fxml","Dashboard");

    }

    @FXML
    void registrationOnAction(MouseEvent event) throws IOException {
        NavigationMember.switchPaging(txtAnchorPane,"userRegistratiionForm.fxml","User Registration");

    }

    public void initialize(URL url , ResourceBundle resourceBundle){
        timenow(lblDate,lblTime);

        try {
            NavigationMember.switchPaging(txtAnchorPane,"dashboardForm.fxml","Dashboard");
        }catch (IOException e){
            throw new RuntimeException(e);
        }


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


