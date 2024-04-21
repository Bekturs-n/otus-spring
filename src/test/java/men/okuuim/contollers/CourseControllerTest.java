package men.okuuim.contollers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CourseControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private static final Long COURSE_ID = 1L;
    private String URL;

    @BeforeEach
    public void before() {
        URL = "http://localhost:" + port;
    }

    @Test
    void getCourse() {
        assertThat(restTemplate.getForObject(URL + "/course/" + COURSE_ID, String.class))
                .contains("Chemistry");
    }

    @Test
    void getCourses() {
        assertThat(restTemplate.getForObject(URL + "/course", String.class))
                .contains("Chemistry");
    }

}