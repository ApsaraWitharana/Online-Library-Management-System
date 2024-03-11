package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "admin")
public class Admin {

    @Id
    @Column(name = "admin_name")
    private String name;

    @Column(name = "admin_password")
    private String password;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "admin")
//    private List<Book> books = new ArrayList<>();

    @ManyToOne
    @jakarta.persistence.JoinColumn(name = "book_id",referencedColumnName = "book_id")
    private Book book;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "admin")
//    private List<Library> libraries = new ArrayList<>();

    @ManyToOne
    @jakarta.persistence.JoinColumn(name = "library_id",referencedColumnName = "library_id")
    private Library library;

}
