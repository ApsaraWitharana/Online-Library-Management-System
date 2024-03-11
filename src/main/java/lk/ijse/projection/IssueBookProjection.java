package lk.ijse.projection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class IssueBookProjection {
    private String u_id;

    private String b_id;

    private String available;

    private LocalDate date;

    private String day_count;



}

