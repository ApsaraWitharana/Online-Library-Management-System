package lk.ijse.controller.member;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.bo.custom.impl.UserBOImpl;
import lk.ijse.controller.member.utile.sateValidation;
import lk.ijse.dto.UserDTO;
import lk.ijse.dto.tm.UserTM;
import lk.ijse.entity.User;
import lk.ijse.utile.AlertController;

import java.awt.event.KeyEvent;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserRegistrationFormController implements Initializable {

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private RadioButton rdFemale;

    @FXML
    private RadioButton rdMale;

    @FXML
    private RadioButton rdOther;

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
    public ToggleGroup toggleGroup;
    UserBO userBO = new UserBOImpl();
    ObservableList<UserTM> obList = FXCollections.observableArrayList();
    private UserDTO userDTO;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if(userDTO!=null){
            setRegisterForm();
        }
    }

    public void btnEnable() {

        if (sateValidation.userIdCheck(txtId.getText()) && sateValidation.userNameCheck(txtName.getText()) && sateValidation.AddressCheck(txtAddress.getText()))
         {
            btnSave.setDisable(false);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }
    @FXML
    void txtIdOnMouseKeyTyped(KeyEvent event) {


    }
    @FXML
    void txtNameOnMouseKeyTyped(KeyEvent event) {


    }

    public void txtNameOnMouseKeyTyped(javafx.scene.input.KeyEvent keyEvent) {

        String user_name = txtName.getText();
        if (sateValidation.userNameCheck(user_name)) {
            txtName.setStyle("-fx-text-fill: black; -fx-background-color: #ebebeb; -fx-background-radius: 15");
            btnEnable();
        } else {
            txtName.setStyle("-fx-text-fill: red; -fx-background-color: #ebebeb; -fx-background-radius: 15");
            btnSave.setDisable(true);
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);
        }
    }
    public void txtIdOnMouseKeyTyped(javafx.scene.input.KeyEvent keyEvent) {
        String user_id = txtId.getText();
        if (sateValidation.userIdCheck(user_id)) {
            txtId.setStyle("-fx-text-fill: black; -fx-background-color: #ebebeb; -fx-background-radius: 15");
            btnEnable();
        } else {
            txtId.setStyle("-fx-text-fill: red; -fx-background-color: #ebebeb; -fx-background-radius: 15");
            btnSave.setDisable(true);
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);
        }
    }
    public void txtAddressOnMouseAction(javafx.scene.input.KeyEvent keyEvent) {
        String address = txtAddress.getText();
        if (sateValidation.AddressCheck(address)) {
            txtAddress.setStyle("-fx-text-fill: black; -fx-background-color: #ebebeb; -fx-background-radius: 15");
            btnEnable();
        } else {
            txtAddress.setStyle("-fx-text-fill: red; -fx-background-color: #ebebeb; -fx-background-radius: 15");
            btnSave.setDisable(true);
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);
        }
    }
    private void setRegisterForm() {
        switch (userDTO.getGender()){
            case "Male" : rdMale.setSelected(true);
                break;
            case "Female" : rdFemale.setSelected(true);
                break;
            case "Other" : rdOther.setSelected(true);
                break;
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        getClear();
    }

    private void getClear() {
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
        txtEmail.clear();
        txtContact.clear();
        toggleGroup.selectToggle(null);
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        String gender = "";
        if (rdMale.isSelected())
            gender = "Male";
        else if (rdFemale.isSelected())
            gender = "Female";
        else if (rdOther.isSelected())
            gender = "Other";

        UserDTO dto = new UserDTO(txtId.getText(),txtName.getText(),txtAddress.getText(),txtEmail.getText(),txtContact.getText(),
               gender );
        if (btnSave.getText().equals("Save")) {
            boolean isSaved = userBO.saveUser(dto);

            if (isSaved) {
                AlertController.confirmmessage("Process Terminated", "User details saving successfully ");
                System.out.println(dto);


                txtId.setStyle("-fx-background-radius: 20");
                txtName.setStyle("-fx-border-color: black;-fx-background-radius: 10");
                txtAddress.setStyle("-fx-border-color: black;-fx-background-radius: 10");
                txtEmail.setStyle("-fx-border-color: black;-fx-background-radius: 10");
                txtContact.setStyle("-fx-border-color: black;-fx-background-radius: 10");

            } else {
                AlertController.errormessage("Process Completed", "User details saved unsuccessfully\n" +
                        "Please resubmit the information");

            }
        }
    }

    @FXML
    void btnDeleteOnActiono(ActionEvent event) {
        String gender = "";
        if (rdMale.isSelected())
            gender = "Male";
        else if (rdFemale.isSelected())
            gender = "Female";
        else if (rdOther.isSelected())
            gender = "Other";
        User user = new User((txtId.getText()));
        UserDTO dto = new UserDTO(txtId.getText(),txtName.getText(),txtAddress.getText(),txtEmail.getText(),txtContact.getText(),gender);
        if (btnDelete.getText().equals("Delete")){
            boolean isDelete = userBO.deleteUser(user.getId());
            if (isDelete) {
                AlertController.confirmmessage("Process Terminated", " User details updated successfully "
                );
                System.out.println(dto);
            } else {
                AlertController.errormessage("Process Completed", "User details updated unsuccessfully\n" +
                        "Please resubmit the information");

            }
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        String gender = "";
        if (rdMale.isSelected())
            gender = "Male";
        else if (rdFemale.isSelected())
            gender = "Female";
        else if (rdOther.isSelected())
            gender = "Other";
        UserDTO dto = new UserDTO(txtId.getText(),txtName.getText(),txtAddress.getText(),txtEmail.getText(),txtContact.getText(),gender);
        if (btnUpdate.getText().equals("Update")){
            boolean isUpdated = userBO.updateUser(dto);

            if (isUpdated) {
                AlertController.confirmmessage("Process Terminated", " User details updated successfully "
                );
                System.out.println(dto);
            } else {
                AlertController.errormessage("Process Completed", "User details updated unsuccessfully\n" +
                        "Please resubmit the information");

            }
        }
    }



}
