package lk.ijse.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class BookTM {
    private int id;
    private String title;
    private String stream;
    private String author;
    private String status;
    private int qty;
}
