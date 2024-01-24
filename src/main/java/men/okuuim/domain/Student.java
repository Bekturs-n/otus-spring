package men.okuuim.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

// todo javadocs

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Student {
    @Id
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private List<Course> course;
    private boolean enabled;

    //role e.g.
    //todo дополнить - password, role e.g.
}
