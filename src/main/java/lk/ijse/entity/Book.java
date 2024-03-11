package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @ToString
    @Entity
    @Table(name = "books")
 public class Book {

    @Id
  //  @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="book_id")
    private String id;

    @Column(name = "book_title")
    private String title;

    @Column(name = "stream")
    private String stream;

    @Column(name = "author")
    private String author;

    @Column(name = "status")
    private String status;

    @Column(name = "qty")
    private int qty;

//
//        @CreationTimestamp
//        @Column(name = "book_added")
//        private Timestamp addedDate;

        @ManyToOne
        @JoinColumn(name = "library_id") //forigkey ekk
        private Library library;


        @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "book")
        private List<IssueBook> issueBook = new ArrayList<>();



        public Book(String id) {
        this.id =id;
    }

        public Book(String id, String title, String stream, String author, String status, int qty) {
            this.id = id;
            this.title = title;
            this.stream = stream;
            this.author = author;
            this.status = status;
            this.qty = qty;
        }
    }
