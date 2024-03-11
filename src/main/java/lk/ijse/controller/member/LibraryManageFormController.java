package lk.ijse.controller.member;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.ijse.bo.custom.LibraryBO;
import lk.ijse.bo.custom.impl.LibraryBOImpl;
import lk.ijse.controller.member.utile.sateValidation;
import lk.ijse.dto.LibraryDTO;
import lk.ijse.dto.tm.LibraryTM;
import lk.ijse.entity.Library;
import lk.ijse.utile.AlertController;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LibraryManageFormController implements Initializable {

    @FXML
    private Button btnClear;

    @FXML
    private Button btDelete;

    @FXML
    private Button btnSave;

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
    private TableView<LibraryDTO> tblLibrary;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    LibraryBO libraryBO = new LibraryBOImpl();
    ObservableList<LibraryTM> obList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        assert btDelete != null : "fx:id=\"btnDelete\" was not injected: check your FXML file 'libraryForm1.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'libraryForm1.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'libraryForm1.fxml'.";
        assert btnUpdate != null : "fx:id=\"btnUpdate\" was not injected: check your FXML file 'libraryForm1.fxml'.";
        assert txtId != null : "fx:id=\"txtId\" was not injected: check your FXML file 'libraryForm1.fxml'.";
        assert txtName != null : "fx:id=\"txtName\" was not injected: check your FXML file 'libraryForm1.fxml'.";
        assert txtAddress != null : "fx:id=\"txtAddress\" was not injected: check your FXML file 'libraryForm1.fxml'.";
        assert txtEmail != null : "fx:id=\"txtEmail\" was not injected: check your FXML file 'libraryForm1.fxml'.";
        assert txtContact != null : "fx:id=\"txtContact\" was not injected: check your FXML file 'libraryForm1.fxml'.";

        Image image = new Image("/image/icons8-home-64.png");

        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setPreserveRatio(true);

        //btnHome.setGraphic(imageView);
        setDataToTableView();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        getClear();
    }

    private void getClear() {

        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtEmail.clear();
        txtContact.clear();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        Library library = new Library((txtId.getText()));
        if (btDelete.getText().equals("Delete")) {
            boolean isDelete = libraryBO.deleteLibrary(library.getId());
            setDataToTableView();
            if (isDelete) {
                AlertController.confirmmessage("Process Terminated", "Library details delete successfully "
                );
                System.out.println(library);
            } else {
                AlertController.errormessage("Process Completed", "Library details delete unsuccessfully\n" +
                        "Please resubmit the information");

            }
        }
    }
    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        LibraryDTO dto = new LibraryDTO(txtId.getText(),txtName.getText(),txtAddress.getText(),txtContact.getText(),txtEmail.getText());
        if (btnSave.getText().equals("Save")) {
            boolean isSaved = libraryBO.saveLibrary(dto);
            setDataToTableView();
            if (isSaved) {
                AlertController.confirmmessage("Process Terminated", "Library details saving successfully ");
                System.out.println(dto);

                txtId.setStyle("-fx-border-color: black");
                txtName.setStyle("-fx-border-color: black");
                txtAddress.setStyle("-fx-border-color: black");
                txtEmail.setStyle("-fx-border-color: black");
                txtContact.setStyle("-fx-border-color: black");

            } else {
                AlertController.errormessage("Process Completed", "Library details saved unsuccessfully\n" +
                        "Please resubmit the information");
                setDataToTableView();
            }
        }
    }

    private void setDataToTableView() {
        ObservableList<LibraryDTO> libraryList = libraryBO.getDetailsToTableView();
        tblLibrary.setItems(libraryList);
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        LibraryDTO dto = new LibraryDTO(txtId.getText(),txtName.getText(),txtAddress.getText(),txtEmail.getText(),txtContact.getText());
        if (btnUpdate.getText().equals("Update")){
            boolean isUpdated = libraryBO.updateLibrary(dto);
            setDataToTableView();
            if (isUpdated) {
                AlertController.confirmmessage("Process Terminated", " Library details updated successfully "
                );
                System.out.println(dto);
            } else {
                AlertController.errormessage("Process Completed", "Library details updated unsuccessfully\n" +
                        "Please resubmit the information");
                setDataToTableView();
            }
        }
    }

    @FXML
    void tblOnMouseAction(MouseEvent event) {

        TablePosition pos = tblLibrary.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        // Get the data from the selected row
        ObservableList<TableColumn<LibraryDTO, ?>> columns = tblLibrary.getColumns();

        txtId.setText(columns.get(0).getCellData(row).toString());
        txtName.setText(columns.get(1).getCellData(row).toString());
        txtAddress.setText(columns.get(2).getCellData(row).toString());
        txtEmail.setText(columns.get(3).getCellData(row).toString());
        txtContact.setText(columns.get(4).getCellData(row).toString());
        txtId.setStyle("-fx-text-fill: black; -fx-background-color:white; -fx-background-radius: 15");


        btnSave.setDisable(false);
        btnUpdate.setDisable(false);
        btDelete.setDisable(false);
    }


    public void txtIdOnMouseKeyTyped(KeyEvent keyEvent) {
        String library_id = txtId.getText();
        if (sateValidation.libraryIdCheck(library_id)) {
            txtId.setStyle("-fx-text-fill: black; -fx-background-color: #ebebeb; -fx-background-radius: 15");
            btnEnable();
        } else {
            txtId.setStyle("-fx-text-fill: red; -fx-background-color: #ebebeb; -fx-background-radius: 15");
            btnSave.setDisable(true);
            btnUpdate.setDisable(true);
            btDelete.setDisable(true);
        }
    }

    private void btnEnable() {
        if (sateValidation.libraryIdCheck(txtId.getText()))
        {
            btnSave.setDisable(false);
            btnUpdate.setDisable(false);
            btDelete.setDisable(false);
        }
    }
}
