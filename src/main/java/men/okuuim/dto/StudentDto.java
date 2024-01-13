package men.okuuim.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import men.okuuim.domain.Student;
import org.springframework.lang.Nullable;

@Getter
@Setter
public class StudentDto {

    private long id;
    private String firstName;
    private String lastName;
    private String email;

}
