package lk.ijse.controller.member;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ReturnBookFormController implements Initializable {

    @FXML
    private Button btnCansel;

    @FXML
    private Button btnReturn;

    @FXML
    private ComboBox<?> cmbBookId;

    @FXML
    private ComboBox<?> cmbUserId;

    @FXML
    private DatePicker dpDate;

    @FXML
    private Label lblDate;

    @FXML
    private TextField txtBookName;

    @FXML
    private TextField txtUserName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        lblDate.setText(LocalDate.now().toString());
    }

    @FXML
    void btnCanselOnAction(ActionEvent event) {

    }

    @FXML
    void btnReturnOnActiion(ActionEvent event) {

    }


}
