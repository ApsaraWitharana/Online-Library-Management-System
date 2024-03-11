package lk.ijse.controller.member;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lk.ijse.bo.custom.BookBO;
import lk.ijse.bo.custom.impl.BookBOImpl;
import lk.ijse.dto.BookDTO;
import lk.ijse.dto.tm.BookTM;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> colAuther;

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
    private TableView<BookDTO> tblBook;

    BookBO bookBO = new BookBOImpl();
    ObservableList<BookTM> obList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
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
        ObservableList<BookDTO> bookList = bookBO.getDetailsToTableView();
        tblBook.setItems( bookList );
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colStream.setCellValueFactory(new PropertyValueFactory<>("stream"));
        colAuther.setCellValueFactory(new PropertyValueFactory<>("author"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));

        tblBook.setStyle("-fx-background-color: black;-fx-border-radius: 5px");

    }

}

