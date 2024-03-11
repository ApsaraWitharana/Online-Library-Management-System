package lk.ijse.entity;

import jakarta.persistence.JoinColumn;

import lk.ijse.embedded.IssueBookPK;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "issue_book")
public class IssueBook {

//
//    @EmbeddedId  // id deke commboration ek me class ekt dagnn
//    private IssueBookPK issueBookPK;


    @Id
    @Column(name = "available")
        private String available;

    @Column(name = "issue_date")
        private LocalDate date;

    @Column(name = "day_count")
        private String day_count;


    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id",referencedColumnName = "book_id")
    private Book book;


   public IssueBook(String bId, String bName, String uId, String uName, LocalDate date, String dayCount, String available) {
    }



    public IssueBook(String bId, String uId, LocalDate date, String dayCount, String available) {
    }

    public IssueBook(String value) {

    }
}