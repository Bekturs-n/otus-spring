package men.okuuim.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
class SecurityConfigurationTest {

    @Autowired
    private MockMvc api;

    @Test
    void emptyUrlIsOkStatus() throws Exception {
        api.perform(get("/")).andExpect(status().isOk());
    }

    @Test
    void userUrlIsUnauthorizedStatus() throws Exception {
        api.perform(get("/student")).andExpect(status().isUnauthorized());
    }

    @Test
    void courseUrlIsUnauthorizedStatus() throws Exception{
        api.perform(get("/course")).andExpect(status().isUnauthorized());
    }

    @WithMockUser(
            username = "user",
            authorities = "USER"
    )
    @Test
    void getOkStatus() throws Exception {
        api.perform(get("/student")).andExpect(status().isOk());
        api.perform(get("/course")).andExpect(status().isOk());
    }
}