package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@NoArgsConstructor
@Data
@ToString
public class UserDTO {
    private String id;

    private String name;

    private String address;

    private String email;

    private String contact;

    private String gender;

    private String userName;
    private String password;

    public UserDTO(String text, String text1, String text2, String text3, String text4) {
    }

    public UserDTO(String id, String name, String address, String email, String contact, String gender) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.contact = contact;
        this.gender = gender;
    }

    public UserDTO(String id, String name, String address, String email, String contact, String gender, String userName, String password) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.contact = contact;
        this.gender = gender;
        this.userName = userName;
        this.password = password;
    }

    public UserDTO(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;

    }

    public UserDTO(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
