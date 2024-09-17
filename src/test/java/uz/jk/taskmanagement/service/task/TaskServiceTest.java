package uz.jk.taskmanagement.service.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import uz.jk.taskmanagement.domain.constants.TaskStatus;
import uz.jk.taskmanagement.domain.dto.request.TaskRequest;
import uz.jk.taskmanagement.domain.dto.response.PageResponse;
import uz.jk.taskmanagement.domain.entity.TaskEntity;
import uz.jk.taskmanagement.domain.exception.DataNotfoundException;
import uz.jk.taskmanagement.repository.TaskRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TaskServiceTest {
    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    private final TaskEntity taskEntity = TaskEntity.builder()
            .title("task")
            .description("this is a task from OSON group")
            .dueDate(LocalDate.of(2024, 1, 1))
            .taskStatus(TaskStatus.OPEN)
            .build();

    private final TaskRequest taskRequest =
            new TaskRequest(
                    "task",
                    "this is a task from OSON group",
                    LocalDate.of(2024, 1, 1),
                    null);


    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations
                .openMocks(this);
//                .close();
    }


    @Test
    void saveTaskTest() {
        when(taskRepository.save(any(TaskEntity.class))).thenReturn(taskEntity);

        TaskEntity result = taskService.save(taskRequest);

        assertEquals(taskRequest.title(), result.getTitle());
    }

    @Test
    void findByIdTest() {
        UUID id = UUID.randomUUID();
        taskEntity.setId(id);

        when(taskRepository.findById(id)).thenReturn(Optional.of(taskEntity));

        TaskEntity result = taskService.findById(id);

        assertEquals(id, result.getId());
        assertEquals("task", result.getTitle());
        verify(taskRepository, times(1)).findById(id);
    }

    @Test
    void findByIdNotFoundTest() {
        UUID id = UUID.randomUUID();

        when(taskRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DataNotfoundException.class, () -> taskService.findById(id));
        verify(taskRepository, times(1)).findById(id);
    }

    @Test
    void findAll() {
        TaskEntity task1 = TaskEntity.builder().title("Task 1").build();
        TaskEntity task2 = TaskEntity.builder().title("Task 2").build();
        Page<TaskEntity> page = new PageImpl<>(Arrays.asList(task1, task2));

        when(taskRepository.findAll(PageRequest.of(0, 2))).thenReturn(page);

        PageResponse<TaskEntity> result = taskService.findAll(0, 2);

        assertNotNull(result);
        assertEquals(2, result.getContent().size());
        assertEquals(2, result.getTotalElements());
        verify(taskRepository, times(1)).findAll(PageRequest.of(0, 2));
    }

    @Test
    void update() {
        UUID id = UUID.randomUUID();
        taskEntity.setId(id);
        TaskRequest taskRequest = new TaskRequest("New Title", "New Description", LocalDate.of(2024, 1, 10), null);


        when(taskRepository.findById(id)).thenReturn(Optional.of(taskEntity));
        when(taskRepository.save(taskEntity)).thenReturn(taskEntity);

        TaskEntity result = taskService.update(id, taskRequest);

        assertNotNull(result);
        assertEquals("New Title", result.getTitle());
        assertEquals(TaskStatus.OPEN, result.getTaskStatus());
        verify(taskRepository, times(1)).findById(id);
        verify(taskRepository, times(1)).save(taskEntity);
    }

    @Test
    void deleteById() {
        UUID taskId = UUID.randomUUID();
        doNothing().when(taskRepository).deleteById(taskId);

        taskService.deleteById(taskId);

        verify(taskRepository, times(1)).deleteById(taskId);
    }
}