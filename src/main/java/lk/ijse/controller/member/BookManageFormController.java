package lk.ijse.controller.member;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import lk.ijse.bo.custom.BookBO;
import lk.ijse.bo.custom.impl.BookBOImpl;
import lk.ijse.controller.member.utile.sateValidation;
import lk.ijse.dto.BookDTO;
import lk.ijse.dto.tm.BookTM;
import lk.ijse.entity.Book;
import lk.ijse.utile.AlertController;

import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BookManageFormController implements Initializable {

    @FXML
    private Button btDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colAuthor;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private Rectangle rerctan;

    @FXML
    private TableView<BookDTO> tblBooks;

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtStream;

    @FXML
    private TextField txtStatus;

    @FXML
    private TextField txtTitle;

    @FXML
    private TableColumn<?, ?> colStream;
    @FXML
    private Button btnClear;
    
    public static Image image;
    BookBO bookBO = new BookBOImpl();
    ObservableList<BookTM> obList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        assert btDelete != null : "fx:id=\"btnDelete\" was not injected: check your FXML file 'booksForm.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'booksForm.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'booksForm.fxml'.";
        assert btnUpdate != null : "fx:id=\"btnUpdate\" was not injected: check your FXML file 'booksForm.fxml'.";
        assert txtTitle != null : "fx:id=\"txtTitle\" was not injected: check your FXML file 'booksForm.fxml'.";
        assert txtId != null : "fx:id=\"txtId\" was not injected: check your FXML file 'booksForm.fxml'.";
        assert txtStream != null : "fx:id=\"txtName\" was not injected: check your FXML file 'booksForm.fxml'.";
        assert txtStatus != null : "fx:id=\"txtStatus\" was not injected: check your FXML file 'booksForm.fxml'.";
        assert txtAuthor != null : "fx:id=\"txtAuthor\" was not injected: check your FXML file 'booksForm.fxml'.";
        assert txtQty != null : "fx:id=\"txtQty\" was not injected: check your FXML file 'booksForm.fxml'.";

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
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colStream.setCellValueFactory(new PropertyValueFactory<>("stream"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblBooks.setStyle("-fx-border-color:black;");
    }


    @FXML
    void btnClearOnAction(ActionEvent event) {getClear();

    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        txtId.setDisable(false);
        txtTitle.setDisable(false);
        txtStream.setDisable(false);
        txtAuthor.setDisable(false);
        txtStatus.setDisable(false);
        txtQty.setDisable(false);
        txtId.clear();
       /// txtId.setText(generateNewId());

    }
    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        BookDTO dto = new BookDTO((txtId.getText()), txtTitle.getText(), txtStream.getText(), txtAuthor.getText(), txtStream.getText(), Integer.parseInt(txtQty.getText()));
        if (btnSave.getText().equals("Save")) {
            boolean isSaved = bookBO.saveBook(dto);
            setDataToTableView();
                if (isSaved) {
                    AlertController.confirmmessage("Process Terminated", "Book details saving successfully ");
                    System.out.println(dto);

                    txtId.setStyle("-fx-border-color: black");
                    txtAuthor.setStyle("-fx-border-color: black");
                    txtStatus.setStyle("-fx-border-color: black");
                    txtStream.setStyle("-fx-border-color: black");
                    txtQty.setStyle("-fx-border-color: black");

                } else {
                    AlertController.errormessage("Process Completed", "Book details saved unsuccessfully\n" +
                            "Please resubmit the information");
                    setDataToTableView();
                }
            }
        }





    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        BookDTO dto = new BookDTO((txtId.getText()), txtTitle.getText(), txtStream.getText(), txtAuthor.getText(), txtStream.getText(), Integer.parseInt(txtQty.getText()));
        if (btnUpdate.getText().equals("Update")){
            boolean isUpdated = bookBO.updateBook(dto);
            setDataToTableView();
            if (isUpdated) {
                AlertController.confirmmessage("Process Terminated", "Book details updated successfully "
                );
                System.out.println(dto);
            } else {
                AlertController.errormessage("Process Completed", "Book details updated unsuccessfully\n" +
                        "Please resubmit the information");
                setDataToTableView();
            }
        }
    }

    @FXML
    void btnDeleteOnAction(MouseEvent event) throws SQLException, ClassNotFoundException {



    }
    @FXML
    void btnDeleteOnActionn(ActionEvent event) {
        Book book = new Book((txtId.getText()));
        if (btDelete.getText().equals("Delete")){
            boolean isDelete =bookBO.deleteBook(book.getId());
            setDataToTableView();
            if (isDelete) {
                AlertController.confirmmessage("Process Terminated", "Book details delete successfully "
                );
                System.out.println(book);
            } else {
                AlertController.errormessage("Process Completed", "Book details delete unsuccessfully\n" +
                        "Please resubmit the information");

            }
        }
    }




    private BookDTO getDetailsInTextFields() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(txtId.getText());
        bookDTO.setTitle(txtTitle.getText());
        bookDTO.setStream(txtStream.getText());
        bookDTO.setAuthor(txtAuthor.getText());
        bookDTO.setStatus(txtStatus.getText());
        bookDTO.setQty(Integer.parseInt(txtQty.getText()));
        return bookDTO;
    }


    private boolean noEmptyValuesInTextFields() {
        String  id = (txtId.getText());
        String title = txtTitle.getText();
        String stream = txtStream.getText();
        String author = txtAuthor.getText();
        String status = txtStatus.getText();
        int qty = Integer.parseInt(txtQty.getText());

        if (!id.isEmpty() && !title.isEmpty() && !stream.isEmpty() && !author.isEmpty() && !status.isEmpty() ){
            return true;
        }else {
            return false;
        }
    }

    private void setDataToTableView() {
        ObservableList<BookDTO> bookList = bookBO.getDetailsToTableView();
        tblBooks.setItems( bookList );
    }


    private void getClear() {
        txtId.clear();
        txtTitle.clear();
        txtStream.clear();
        txtAuthor.clear();
        txtStatus.clear();
        txtQty.clear();
    }




    @FXML
    void setImageOnAction(MouseEvent actionEvent) {

        Window window = ((Node) (actionEvent.getSource())).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(window);
        actionEvent.consume();
        try {
            InputStream in = new FileInputStream(file);
            image = new Image(in);
            rerctan.setFill(new ImagePattern(image));

        } catch (FileNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }



    public void tblOnMouseClicked(MouseEvent mouseEvent) {
        TablePosition pos = tblBooks.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        // Get the data from the selected row
        ObservableList<TableColumn<BookDTO, ?>> columns = tblBooks.getColumns();

        txtId.setText(columns.get(0).getCellData(row).toString());
        txtTitle.setText(columns.get(1).getCellData(row).toString());
        txtAuthor.setText(columns.get(2).getCellData(row).toString());
        txtStream.setText(columns.get(3).getCellData(row).toString());
        txtStatus.setText(columns.get(4).getCellData(row).toString());
        txtQty.setText(columns.get(5).getCellData(row).toString());
        txtId.setStyle("-fx-text-fill: black; -fx-background-color:white; -fx-background-radius: 15");


        btnSave.setDisable(false);
        btnUpdate.setDisable(false);
        btDelete.setDisable(false);

    }


    public void txtIdOnMouseKeyTyped(KeyEvent keyEvent) {

    }

    private void btnEnable() {
        if (sateValidation.bookIdCheck(txtId.getText()) )
        {
            btnSave.setDisable(false);
            btnUpdate.setDisable(false);
            btDelete.setDisable(false);
        }
    }

    public void txtIdMouseKeyOnAction(KeyEvent keyEvent) {

        String book_id = txtId.getText();
        if (sateValidation.bookIdCheck(book_id)) {
            txtId.setStyle("-fx-text-fill: black; -fx-background-color: #ebebeb; -fx-background-radius: 15");
            btnEnable();
        } else {
            txtId.setStyle("-fx-text-fill: red; -fx-background-color: #ebebeb; -fx-background-radius: 15");
            btnSave.setDisable(true);
            btnUpdate.setDisable(true);
            btDelete.setDisable(true);
        }
    }
}


