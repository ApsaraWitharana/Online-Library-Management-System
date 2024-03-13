package lk.ijse.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class IssueBookTM {

    private String id;

    private String u_id;

    private String u_name;

    private String b_id;

    private String b_name;

    private String available;

    private LocalDate date;

    private String day_count;

    public IssueBookTM(String value, String value1, String text, String text1, LocalDate value2, String text2, String text3) {
    }
}
