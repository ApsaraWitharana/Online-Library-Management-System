package lk.ijse.controller.member;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.BookBO;
import lk.ijse.bo.custom.IssueBookBO;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.bo.custom.impl.BookBOImpl;
import lk.ijse.bo.custom.impl.IssueBookBOImpl;
import lk.ijse.bo.custom.impl.UserBOImpl;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ReturnBookFormController implements Initializable {

    @FXML
    private Button btnCansel;

    @FXML
    private Button btnReturn;

    @FXML
    private ComboBox<String> cmbBookId;

    @FXML
    private ComboBox<String> cmbUserId;

    @FXML
    private DatePicker dpDate;

    @FXML
    private Label lblDate;

    @FXML
    private TextField txtBookName;

    @FXML
    private TextField txtUserName;

    IssueBookBO issueBookBO = (IssueBookBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.ISSUE_BOOK);
    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.USER);

    BookBO bookBO = (BookBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.BOOK);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        lblDate.setText(LocalDate.now().toString());


        setBookList();
        setUserList();

        assert btnReturn != null : "fx:id=\"btnReturn\" was not injected: check your FXML file 'returnBookForm.fxml'.";
        assert btnCansel != null : "fx:id=\"btnCansel\" was not injected: check your FXML file 'returnBookForm.fxml'.";
        assert txtBookName != null : "fx:id=\"txtBookName\" was not injected: check your FXML file 'returnBookForm.fxml'.";
        assert txtUserName != null : "fx:id=\"txtUserName\" was not injected: check your FXML file 'returnBookForm.fxml'.";
        assert cmbUserId != null : "fx:id=\"cmbUserId\" was not injected: check your FXML file 'returnBookForm.fxml'.";
        assert cmbBookId != null : "fx:id=\"cmbBookId\" was not injected: check your FXML file 'returnBookForm.fxml'.";
        assert lblDate != null : "fx:id=\"lblDate\" was not injected: check your FXML file 'returnBookForm.fxml'.";

    }

    private void setUserList() {
        List<String> itemList1 = userBO.getUserID();
        ObservableList<String> dataList = FXCollections.observableArrayList();

        for (String ids : itemList1) {
            dataList.add(String.valueOf(ids));
            System.out.println(itemList1);
        }

        cmbUserId.setItems(dataList);

    }

    private void setBookList() {
        List<String> itemList = bookBO.getBookID();
        ObservableList<String> dataList = FXCollections.observableArrayList();

        for (String ids : itemList) {
            dataList.add(String.valueOf(ids));
            System.out.println(itemList);
        }

        cmbBookId.setItems(dataList);
    }

    @FXML
    void btnCanselOnAction(ActionEvent event) {

    }

    @FXML
    void btnReturnOnActiion(ActionEvent event) {

    }


}
