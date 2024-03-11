package lk.ijse.controller.member;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.bo.custom.impl.UserBOImpl;
import lk.ijse.dto.UserDTO;
import lk.ijse.dto.tm.UserTM;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class UserManageFormController implements Initializable {
    @FXML
    private AnchorPane ancerPane;
    @FXML
    private Button btnAddNew;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableView<UserDTO> tblUser;

    UserBO userBO = new UserBOImpl();

    private UserDTO userDTO;
    ObservableList<UserTM> obList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        assert btnDelete != null : "fx:id=\"btnDelete\" was not injected: check your FXML file 'libraryForm.fxml'.";
        assert btnUpdate != null : "fx:id=\"btnUpdate\" was not injected: check your FXML file 'libraryForm.fxml'.";
        assert colId != null : "fx:id=\"txtId\" was not injected: check your FXML file 'libraryForm.fxml'.";
        assert colName != null : "fx:id=\"txtName\" was not injected: check your FXML file 'libraryForm.fxml'.";
        assert colAddress != null : "fx:id=\"txtAddress\" was not injected: check your FXML file 'libraryForm.fxml'.";
        assert colEmail != null : "fx:id=\"txtEmail\" was not injected: check your FXML file 'libraryForm.fxml'.";
        assert colContact != null : "fx:id=\"txtContact\" was not injected: check your FXML file 'libraryForm.fxml'.";

        Image image = new Image("/image/icons8-home-64.png");

        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setPreserveRatio(true);

        //btnHome.setGraphic(imageView);
        setDataToTableView();
        setCellValueFactory();
    }

    private void setDataToTableView() {
        ObservableList<UserDTO> userList = userBO.getDetailsToTableView();
        tblUser.setItems(userList);
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));

    }


    @FXML
    void btnAddNewUserOnAction(ActionEvent stage) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/member/userRegistratiionForm.fxml"));
        ancerPane.getChildren().clear();
        ancerPane.getChildren().add(load);

    }



}
