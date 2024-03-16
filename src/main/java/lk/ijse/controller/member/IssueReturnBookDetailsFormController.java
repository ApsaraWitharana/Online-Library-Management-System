package lk.ijse.controller.member;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.bo.custom.IssueBookBO;
import lk.ijse.bo.custom.impl.IssueBookBOImpl;
import lk.ijse.dto.BookDTO;
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

    @FXML
    private TableColumn<?, ?> colId;
    IssueBookBO issueBookBO = new IssueBookBOImpl();
    ObservableList<IssueBookTM> obList = FXCollections.observableArrayList();
    @FXML
    void tblOnMouseClicked(MouseEvent event) {
        TablePosition pos = tblIssueBook.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        // Get the data from the selected row
        ObservableList<TableColumn<IssueBookDTO, ?>> columns = tblIssueBook.getColumns();

        colAvailable.setText(columns.get(0).getCellData(row).toString());
        colIssueDate.setText(columns.get(1).getCellData(row).toString());
        colDays.setText(columns.get(2).getCellData(row).toString());
        colBookId.setText(columns.get(3).getCellData(row).toString());
        colUserId.setText(columns.get(4).getCellData(row).toString());
        colId.setText(columns.get(5).getCellData(row).toString());


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setDataToTableView();
        setCellValueFactory();

    }

    private void setDataToTableView() {
        ObservableList<IssueBookDTO> bookList1 = issueBookBO.getDetailsToTableView();
        tblIssueBook.setItems( bookList1 );

    }
    private void setCellValueFactory(){
        colAvailable.setCellValueFactory(new PropertyValueFactory<>("available"));
        colIssueDate.setCellValueFactory(new PropertyValueFactory<>("issue_date"));
        colDays.setCellValueFactory(new PropertyValueFactory<>("day_count"));
        colBookId.setCellValueFactory(new PropertyValueFactory<>("book_id"));
        colUserId.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        colId.setCellValueFactory(new PropertyValueFactory<>("issue_book_id"));





    }
}
