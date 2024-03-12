package lk.ijse.dto;

import lk.ijse.entity.Book;
import lk.ijse.entity.IssueBook;
import lk.ijse.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@NoArgsConstructor
@Data
@ToString
public class IssueBookDTO {
    
    private String u_id;
    
    private String b_id;
    
    private String available;

    private LocalDate date;

    private String day_count;

 

    public IssueBookDTO(String text, String text1, String text2, String text3) {
    }

    public IssueBookDTO(String text, String text1, String text2, String text3, String value, String value1) {
    }

    public IssueBookDTO(String u_id, String b_id, String available, LocalDate date, String day_count) {
        this.u_id = u_id;
        this.b_id = b_id;
        this.available = available;
        this.date = date;
        this.day_count = day_count;
    }

    public IssueBookDTO(String b_id, String u_id, String available, String day_count, LocalDate date) {
        this.b_id = b_id;
        this.u_id = u_id;
        this.available = available;
        this.day_count = day_count;
        this.date = date;

    }

    public IssueBook toEntity() {

        IssueBook issueBook = new IssueBook();
        issueBook.setDate(this.date);
        issueBook.setAvailable(this.available);
        issueBook.setDay_count(this.day_count);

        User user = new User();
        user.setId(this.u_id);
        issueBook.setUser(user);

        Book book = new Book();
        book.setId(this.b_id);
        issueBook.setBook(book);


        return issueBook;

    }
}


