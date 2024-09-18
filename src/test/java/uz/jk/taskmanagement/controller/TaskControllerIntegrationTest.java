package uz.jk.taskmanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import uz.jk.taskmanagement.domain.dto.request.TaskRequest;
import uz.jk.taskmanagement.domain.entity.TaskEntity;
import uz.jk.taskmanagement.repository.TaskRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
public class TaskControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private TaskEntity taskEntity;

    @BeforeEach
    void setUp() {
        taskEntity = new TaskEntity("Test Task", null, null, null);
        taskRepository.save(taskEntity);
    }

    @Test
    @Order(1)
    void testCreateTask() throws Exception {
        TaskRequest taskRequest = new TaskRequest("New Task", null, null, null);

        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(taskRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("New Task"));


    }

    @Test
    @Order(2)
    void testFindAllTasks() throws Exception {
        mockMvc.perform(get("/tasks")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].title").value("Test Task"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].id").value(taskEntity.getId().toString()))
                .andReturn()
                .getResponse();
    }

    @Test
    @Order(3)
    void testUpdateTask() throws Exception {
        TaskRequest taskRequest = new TaskRequest("Updated Task", null, null, null);

        mockMvc.perform(put("/tasks/" + taskEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(taskRequest)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Updated Task"));
    }

    @Test
    @Order(4)
    void testDeleteTask() throws Exception {
        mockMvc.perform(delete("/tasks/" + taskEntity.getId()))
                .andExpect(status().isOk());
    }
}
