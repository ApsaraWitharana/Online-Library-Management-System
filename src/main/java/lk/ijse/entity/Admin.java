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
//
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "admin")
//    private List<Library> libraries = new ArrayList<>();

}
