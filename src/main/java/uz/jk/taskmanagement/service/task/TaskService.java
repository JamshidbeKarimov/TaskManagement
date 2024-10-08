package uz.jk.taskmanagement.service.task;

import uz.jk.taskmanagement.domain.dto.request.TaskRequest;
import uz.jk.taskmanagement.domain.dto.response.PageResponse;
import uz.jk.taskmanagement.domain.entity.TaskEntity;

import java.util.UUID;

public interface TaskService {
    TaskEntity save(TaskRequest taskRequest);

    TaskEntity findById(UUID id);

    PageResponse<TaskEntity> findAll(int page, int size);

    TaskEntity update(UUID id, TaskRequest taskRequest);

    void deleteById(UUID id);
}
