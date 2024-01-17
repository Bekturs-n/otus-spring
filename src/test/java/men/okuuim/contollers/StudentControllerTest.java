package men.okuuim.contollers;

import men.okuuim.dto.StudentDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class StudentControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private static final Long ID = 1L;
    private String URL;

    @BeforeEach
    public void before() {
        URL = "http://localhost:" + port;
    }

    @Test
    void getStudent() {
        assertThat(restTemplate.getForObject(URL + "/students/get?id=" + ID,
                String.class)).contains("Alexandre").contains("Dumas").contains("Alexandre_Dumas");
    }

    @Test
    void getAll() {
        assertThat(restTemplate.getForObject(URL + "/students/getAll", String.class))
                .contains("Alexandre").contains("Dumas").contains("Alexandre_Dumas")
                .contains("Gogol").contains("Nikolai").contains("Gogol@gamil.com");
    }

    @Test
    void ZDelete() {
        assertThat(restTemplate.getForEntity(URL + "/students/delete?id=" + 3,
                String.class).getStatusCode()).isEqualTo(HttpStatus.OK);
        assertNull(restTemplate.getForObject(URL + "/students/get?id=" + 3,
                String.class));
    }

    @Test
    void updateBook() {
        StudentDto studentDto = StudentDto.builder().id(2).firstName("Alex").lastName("Duma").email("new@gmail.com")
                .build();
        HttpEntity<StudentDto> request = new HttpEntity<>(studentDto);
        ResponseEntity<String> response = restTemplate.postForEntity(URL + "/students/update", request,
                String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(restTemplate.getForObject(URL + "/students/get?id=" + 2,
                String.class)).contains("Alex", "new@gmail.com");
    }

    @Test
    void add() {
        StudentDto studentDto = StudentDto.builder().firstName("Alex").lastName("Duma").email("new@gmail.com").build();
        HttpEntity<StudentDto> request = new HttpEntity<>(studentDto);
        ResponseEntity<String> response = restTemplate.postForEntity(URL + "/students/add", request,
                String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(restTemplate.getForObject(URL + "/students/getAll",
                String.class)).contains("Alex", "new@gmail.com");
    }
}