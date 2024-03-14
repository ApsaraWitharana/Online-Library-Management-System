package lk.ijse.controller.member;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.bo.custom.IssueBookBO;
import lk.ijse.bo.custom.impl.IssueBookBOImpl;
import lk.ijse.dto.IssueBookDTO;
import lk.ijse.dto.tm.IssueBookTM;

import java.net.URL;
import java.util.ResourceBundle;

public class IssueReturnBookDetailsFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colAvailable;

    @FXML
    private TableColumn<?, ?> colBookId;

    @FXML
    private TableColumn<?, ?> colDays;

    @FXML
    private TableColumn<?, ?> colIssueDate;

    @FXML
    private TableColumn<?, ?> colIsueDate;

    @FXML
    private TableColumn<?, ?> colReturnDate;

    @FXML
    private TableColumn<?, ?> colUserId;

    @FXML
    private TableView<IssueBookDTO> tblIssueBook;

    @FXML
    private TableView<?> tblReturnBook;

    IssueBookBO issueBookBO = new IssueBookBOImpl();
    ObservableList<IssueBookTM> obList = FXCollections.observableArrayList();
    @FXML
    void tblOnMouseClicked(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setDataToTableView();
        setCellValueFactory();

    }

    private void setDataToTableView() {
        ObservableList<IssueBookDTO> bookList = issueBookBO.getDetailsToTableView();
        tblIssueBook.setItems( bookList );

    }
    private void setCellValueFactory(){
        colBookId.setCellValueFactory(new PropertyValueFactory<>("book_id"));
        colUserId.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        colAvailable.setCellValueFactory(new PropertyValueFactory<>("available"));
        colIssueDate.setCellValueFactory(new PropertyValueFactory<>("issue_date"));
        colDays.setCellValueFactory(new PropertyValueFactory<>("day_count"));




    }
}
