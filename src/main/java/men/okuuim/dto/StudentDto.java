package men.okuuim.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import men.okuuim.domain.Course;

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
