package lk.ijse.controller.member;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.BookBO;
import lk.ijse.bo.custom.IssueBookBO;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.bo.custom.impl.BookBOImpl;
import lk.ijse.bo.custom.impl.IssueBookBOImpl;
import lk.ijse.bo.custom.impl.UserBOImpl;
import lk.ijse.dto.IssueBookDTO;
import lk.ijse.dto.tm.IssueBookTM;
import lk.ijse.entity.IssueBook;
import lk.ijse.projection.IssueBookProjection;
import lk.ijse.utile.AlertController;


import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class IssueBookFormController implements Initializable {

    @FXML
    private Button btnCansel;

    @FXML
    private Button btnIssue;

    @FXML
    private ComboBox<String> cmbBookId;

    @FXML
    private ComboBox<String> cmbUserId;

    @FXML
    private DatePicker dpDate;

    @FXML
    private TextField txtBookName;
    @FXML
    private Button btnSave;
    @FXML
    private TextField txtDayCount;
    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtAvailable;

    @FXML
    private Label lblIssueId;

    IssueBookBO issueBookBO = (IssueBookBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.ISSUE_BOOK);
    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.USER);

    BookBO bookBO = (BookBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.BOOK);
    IssueBookProjection issueBookProjection = new IssueBookProjection();
    private ObservableList<IssueBookTM> obList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        IssueBookDTO issueBookDTO = new IssueBookDTO();
        issueBookDTO.setDate(dpDate.getValue());

        setIssueId();
        setBookList();
        setUserList();


        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'issueBookForm.fxml'.";
        assert btnCansel != null : "fx:id=\"btnCansel\" was not injected: check your FXML file 'issueBookForm.fxml'.";
        assert txtBookName != null : "fx:id=\"txtBookName\" was not injected: check your FXML file 'issueBookForm.fxml'.";
        assert txtAvailable != null : "fx:id=\"txtAvailable\" was not injected: check your FXML file 'issueBookForm.fxml'.";
        assert txtDayCount != null : "fx:id=\"txtDayCount\" was not injected: check your FXML file 'issueBookForm.fxml'.";
        assert txtUserName != null : "fx:id=\"txtUserName\" was not injected: check your FXML file 'issueBookForm.fxml'.";
        assert cmbUserId != null : "fx:id=\"cmbUserId\" was not injected: check your FXML file 'issueBookForm.fxml'.";
        assert cmbBookId != null : "fx:id=\"cmbBookId\" was not injected: check your FXML file 'issueBookForm.fxml'.";

    }

    private void setIssueId() {
        String id = String.valueOf(issueBookBO.getIssueId());
        String numericPart = id.replaceAll("\\D+","");

        if (numericPart.isEmpty())
            lblIssueId.setText("I001");

        else
            lblIssueId.setText("I00"+(Integer.parseInt(numericPart)+1));



    }

    @FXML
    void btnCanselOnAction(ActionEvent event) {

//        IssueBook issueBook = new IssueBook((cmbBookId.getValue()));
//        if (btnCansel.getText().equals("Delete")) {
//            boolean isDelete = issueBookBO.deleteIssueBook(String.valueOf(issueBook.getBook()));
//            if (isDelete) {
//                AlertController.confirmmessage("Process Terminated", "IssueBook details delete successfully "
//                );
//                System.out.println(issueBook);
//            } else {
//                AlertController.errormessage("Process Completed", "IssueBook details delete unsuccessfully\n" +
//                        "Please resubmit the information");
//
//            }
//        }


        boolean noEmptyFields = noEmptyValuesInTextFields();
        IssueBookDTO issueBookDTO = getDetailsInTextFields();
        if (noEmptyFields) {
            IssueBook issueBook = issueBookBO.getIssueBookAvailabilty(issueBookDTO);
            if (issueBook != null) {
                boolean result = AlertController.okconfirmmessage("Are you sure you want to delete issuebook");
                if (result) {
                    boolean success = issueBookBO.deleteIssueBook(String.valueOf(issueBookDTO));
                    if (success) {
                        AlertController.confirmmessage("Process Terminated", "Issue details delete successfully ");
                      clearTxtFields();
                    } else {
                        AlertController.errormessage("Process Completed", "Issue details delete unsuccessfully\n" +
                                "Please resubmit the information");

                    }
                }
            }
        }
    }


    private void clearTxtFields() {
        lblIssueId.setText("");
        dpDate.setValue(null);
        txtAvailable.setText("");
        txtDayCount.setText("");
        cmbBookId.setValue("");
        cmbUserId.setValue("");

        btnCansel.setDisable(true);
    }

    private IssueBookDTO getDetailsInTextFields() {
        IssueBookDTO issueBookDTO = new IssueBookDTO();
        issueBookDTO.setId(lblIssueId.getId());
        issueBookDTO.setDate(dpDate.getValue());
        issueBookDTO.setAvailable(txtAvailable.getText());
        issueBookDTO.setU_id(cmbUserId.getValue());
        issueBookDTO.setB_id(cmbBookId.getValue());
        issueBookDTO.setDay_count(txtDayCount.getText());
        return issueBookDTO;
    }

    private boolean noEmptyValuesInTextFields() {

        String id = lblIssueId.getId();
        String u_id = cmbUserId.getValue();
        String b_id = cmbBookId.getValue();
        String available = txtAvailable.getText();
        String issue_date = String.valueOf(dpDate.getValue());
        String day_count = txtDayCount.getText();
        if (!u_id.isEmpty() && b_id.isEmpty() && available !=null && issue_date !=null && day_count !=null && id !=null){
            return true;
        }else {
            return false;
        }

    }


    @FXML
    void btnSaveOnAvtion(ActionEvent event) throws SQLException, ClassNotFoundException {
        IssueBookDTO dto = new IssueBookDTO(lblIssueId.getId(),cmbBookId.getValue(),cmbUserId.getValue(),txtAvailable.getText(),txtDayCount.getText(), String.valueOf(dpDate.getValue()));

        if (btnSave.getText().equals("Save")) {
            boolean isSave = issueBookBO.issueIssueBook(dto);
            if (isSave) {
                AlertController.confirmmessage("Process Terminated", "Issue details saving successfully ");
                System.out.println(dto);
            } else {
                AlertController.errormessage("Process Completed", "Issue details saved unsuccessfully\n" +
                        "Please resubmit the information");

            }
        }

//        boolean noEmptyFields = noEmptyValuesInTextFields();
//        if (noEmptyFields) {
//            IssueBookDTO issueBookDTO = getDetailsInTextFields();
//            boolean success = issueBookBO.saveIssueBook(issueBookDTO);
//            if (success) {
//                AlertController.confirmmessage("Process Terminated", "Issue details saving successfully ");
//                clearTxtFields();
//            } else {
//                AlertController.errormessage("Process Completed", "Issue details saved unsuccessfully\n" +
//                        "Please resubmit the information");
//
//            }
//        }

    }


    @FXML
    void cmbBookIdOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

    }

    @FXML
    void cmbUserIdOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

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

}