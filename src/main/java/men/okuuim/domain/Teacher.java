package men.okuuim.domain;


import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
public class Teacher {

    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private List<Course> course;
}
