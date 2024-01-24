package men.okuuim.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import men.okuuim.domain.Course;
import men.okuuim.domain.Student;
import org.springframework.lang.Nullable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<Course> courseList;

}
