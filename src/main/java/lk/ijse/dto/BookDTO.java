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

    private String id;
    private String title;
    private String stream;
    private String author;
    private String status;
    private int qty;




    public BookDTO(String id) {
      this.id = id;
    }




    public Book toEntity() {

        Book book = new Book();
        book.setId((this.id));
        book.setTitle(this.title);
        book.setStream(this.stream);
        book.setAuthor(this.author);
        book.setStatus(this.status);
        book.setQty(this.qty);
        return book;
    }


}
