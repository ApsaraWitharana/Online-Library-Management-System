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
@Table(name = "library")
public class Library {

    @Id
    @Column(name = "library_id")
    private String id;
    @Column(name = "library_name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "email")
    private String email;
    @Column(name = "contact")
    private String contact;


    @OneToMany(mappedBy = "library")
    private List<User> user  = new ArrayList<>();


    @OneToMany(mappedBy = "library")
    private List<UserRegistration> userRegistrations  = new ArrayList<>();


    @OneToMany(mappedBy = "library")
    private List<Admin> admins  = new ArrayList<>();


    public Library(String id) {
        this.id=id;
    }

    public Library(String id, String name, String address, String email, String contact) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.contact = contact;
    }
}
