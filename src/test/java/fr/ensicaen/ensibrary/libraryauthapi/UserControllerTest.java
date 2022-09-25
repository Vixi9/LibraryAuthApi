package fr.ensicaen.ensibrary.libraryauthapi;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @WithMockUser(authorities = "ROLE_USER")
    @Test
    public void userOnUserPrincipal() throws Exception {
        mockMvc.perform(get("/user/principal")).andExpect(status().isOk());
    }

    @WithMockUser(authorities = "ROLE_ADMIN")
    @Test
    public void adminOnUserPrincipal() throws Exception {
        mockMvc.perform(get("/user/principal")).andExpect(status().isOk());
    }

    @WithMockUser(authorities = "ROLE_USER")
    @Test
    public void userOnUserGetAll() throws Exception {
        mockMvc.perform(get("/user/")).andExpect(status().isForbidden());
    }

    @WithMockUser(authorities = "ROLE_AGENT")
    @Test
    public void agentOnUserGetAll() throws Exception {
        mockMvc.perform(get("/user/")).andExpect(status().isOk());
    }

    @WithMockUser(authorities = "ROLE_ADMIN")
    @Test
    public void adminOnUserGetAll() throws Exception {
        mockMvc.perform(get("/user/")).andExpect(status().isOk());
    }
}
