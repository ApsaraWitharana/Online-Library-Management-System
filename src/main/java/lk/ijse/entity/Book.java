package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="book_id",nullable = false)
    private int id;

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
}
