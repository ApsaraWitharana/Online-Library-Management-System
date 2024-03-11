package lk.ijse.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

public class UserTM {
    private String id;

    private String name;

    private String address;

    private String email;

    private String contact;

    private String gender;

    private String userName;
    private String password;
}
