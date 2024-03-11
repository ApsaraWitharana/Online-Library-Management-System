package lk.ijse.embedded;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Embeddable
public class IssueBookPK implements Serializable {

    @Column(name = "book_id")
    private int book_id;

    @Column(name = "user_id")
    private int user_id;


}
