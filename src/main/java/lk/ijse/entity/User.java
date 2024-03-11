package lk.ijse.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "user_id")
    private String id;
    @Column(name = "user_name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "email")
    private String email;
    @Column(name = "contact")
    private String contact;
    @Column(name = "gender")
    private String gender;


    @Column(name = "u_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "library_id") //forigkey ekk
    private Library library;

    public User(String id, String name, String address, String email, String contact, String gender) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.contact = contact;
        this.gender = gender;
    }

    public User(String password, String name) {
        this.password = password;
        this.name = name;
    }

    public User(String id) {
        this.id = id;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "user")
    private List<IssueBook> issueBook = new ArrayList<>();


    @OneToMany(mappedBy = "user")
    private List<UserRegistration> userRegistrations = new ArrayList<>();

    public User(String id, String name, String address, String email, String contact, String gender, String userName, String password) {
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.password = password;
        this.email = email;

    }
}
