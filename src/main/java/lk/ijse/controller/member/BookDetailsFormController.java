package lk.ijse.controller.member;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.custom.BookBO;
import lk.ijse.bo.custom.impl.BookBOImpl;
import lk.ijse.dto.BookDTO;
import lk.ijse.dto.tm.BookTM;

import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;



public class BookDetailsFormController implements Initializable {

    @FXML
    private AnchorPane ancerbookD;

    @FXML
    private Button btnIssueBook;

    @FXML
    private Button btnReturnBook;

    @FXML
    private TableColumn<?, ?> colAuthor;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colStream;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private Group grpGetStByResId;

    @FXML
    private TableView<BookDTO> tblBooks;

    @FXML
    private TextField txtEnterResId;
    @FXML
    private ImageView txtSearchh;

    @FXML
    private ImageView txtSearch;


    BookBO bookBO = new BookBOImpl();
    ObservableList<BookTM> obList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        setDataToTableView();
        setCellValueFactory();
    }



    private void setDataToTableView() {
        ObservableList<BookDTO> bookList = bookBO.getDetailsToTableView();
        tblBooks.setItems( bookList );
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colStream.setCellValueFactory(new PropertyValueFactory<>("stream"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));

        tblBooks.setStyle("-fx-background-color: black;-fx-border-radius: 5px");

    }
    @FXML
    void btnIsssuOnAction(ActionEvent event) throws IOException {
        Parent anchorPane = FXMLLoader.load(getClass().getResource("/view/member/issueBookForm.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = new Stage();
        stage.setTitle("Return Book Manage");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void btnReturnOnAction(ActionEvent event) throws IOException {
        Parent anchorPane = FXMLLoader.load(getClass().getResource("/view/member/returnBookForm.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = new Stage();
        stage.setTitle("Return Book Manage");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

    }

    @FXML
    void icnSearchOnMouseClicked(MouseEvent event) {


    }

    @FXML
    void BtnSearchOnAction(MouseEvent event) throws IOException {
        List<String> bookList = bookBO.getBookID();
        boolean isValid = false;
        for (String book_id : bookList) {
            if(txtEnterResId.getText().equals(book_id))
            isValid = true;
        }
        if(isValid) {
            tblBooks.getItems().clear();
            BookTM bookTM = new BookTM();
            obList.add(bookTM);
          //tblBooks.setItems(obList);
            setDataToTableView();
            setCellValueFactory();
        }else
            new Alert(Alert.AlertType.ERROR, "Book ID mismatched!").show();

    }

    @FXML
    void btnSearchOnAction(MouseEvent event) {
        List<String> bookList = bookBO.getBookID();
        boolean isValid = false;
        for (String book_id : bookList) {
            if(txtEnterResId.getText().equals(book_id))
                isValid = true;
        }
        if(isValid) {
            tblBooks.getItems().clear();
            BookTM bookTM = new BookTM();
            obList.add(bookTM);
//            tblBooks.setItems(obList);
            setDataToTableView();
            setCellValueFactory();
        }else
            new Alert(Alert.AlertType.ERROR, "Book ID mismatched!").show();
    }
    @FXML
    void tblOnMouseClicked(MouseEvent event) {
        TablePosition pos = tblBooks.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        // Get the data from the selected row
        ObservableList<TableColumn<BookDTO, ?>> columns = tblBooks.getColumns();



    }


}
