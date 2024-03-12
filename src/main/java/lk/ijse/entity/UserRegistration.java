package lk.ijse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "user_registration")
public class UserRegistration {

    @Id
    @Column(name = "user_name")
    private String name;
    @Column(name = "library_name")
    private String l_name;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "user_id"
            ,insertable = false,updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "library_id",referencedColumnName = "library_id",insertable = false,updatable = false)
    private Library library;

}
