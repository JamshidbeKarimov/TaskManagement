package uz.jk.taskmanagement.service.task;

import org.springframework.data.domain.Page;
import uz.jk.taskmanagement.domain.dto.TaskRequest;
import uz.jk.taskmanagement.domain.entity.TaskEntity;

import java.util.UUID;

public interface TaskService {
    TaskEntity save(TaskRequest taskRequest);

    TaskEntity findById(UUID id);

    Page<TaskEntity> findAll(int page, int size);

    TaskEntity update(UUID id, TaskRequest taskRequest);

    void deleteById(UUID id);
}
