package lk.ijse.dto;

import lk.ijse.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class BookDTO  {

    private int id;
    private String title;
    private String stream;
    private String author;
    private String status;
    private int qty;
}
