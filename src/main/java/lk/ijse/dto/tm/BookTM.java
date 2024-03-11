package lk.ijse.dto.tm;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class BookTM {
    private String id;
    private String title;
    private String stream;
    private String author;
    private String status;
    private int qty;
    private Button button;
    private String name;

    public BookTM(int id, String title, String stream, String author, String status, int qty) {
    }


    public BookTM(String id, String title, String stream, String author, String status, int qty) {
    }
}
