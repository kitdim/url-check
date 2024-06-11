package kit.org.app.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import kit.org.app.dto.url.CreateUrl;
import kit.org.app.model.Url;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.junit.jupiter.Container;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@Transactional
public class UrlControllerApiTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Container
    private static PostgreSQLContainer<?> database = new PostgreSQLContainer<>("postgres")
            .withDatabaseName("dbname")
            .withUsername("sa")
            .withPassword("sa")
            .withInitScript("init.sql");

    @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", database::getJdbcUrl);
        registry.add("spring.datasource.username", database::getUsername);
        registry.add("spring.datasource.password", database::getPassword);
    }

    @Test
    @DisplayName("Test find all urls")
    public void testGetAll() throws Exception {
        MockHttpServletResponse response = mockMvc
                .perform(get("/api/urls"))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_JSON.toString());
    }

    @Test
    @DisplayName("Test find by id")
    public void testGetUrlById() throws Exception {
        MockHttpServletResponse response = mockMvc
                .perform(get("/api/urls/1"))
                .andReturn()
                .getResponse();
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_JSON.toString());
        assertThat(response.getContentAsString()).contains("TEST.com");
    }

    @Test
    @DisplayName("Test create url")
    public void testCreate() throws Exception {
        CreateUrl testUrl = new CreateUrl();
        testUrl.setUrl("https://test.com");
        MockHttpServletRequestBuilder postRequest = post("/api/urls")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testUrl));
        mockMvc.perform(postRequest).andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Test update by id")
    public void testUpdate() throws Exception {
        HashMap<String, String> data = new HashMap<>();
        data.put("name", "https://test2.com");

        MockHttpServletRequestBuilder putRequest = put("/api/urls/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(data));
        MockHttpServletResponse response = mockMvc.perform(putRequest)
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();
        assertThat(response.getContentAsString()).contains("https://test2.com");
    }

    @Test
    @DisplayName("Test delete by id")
    public void testDelete() throws Exception {
        MockHttpServletResponse response = mockMvc
                .perform(delete("/api/urls/1"))
                .andReturn()
                .getResponse();
        assertThat(response.getStatus()).isEqualTo(204);
    }
}
