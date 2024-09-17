package uz.jk.taskmanagement.service.task;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import uz.jk.taskmanagement.domain.constants.TaskStatus;
import uz.jk.taskmanagement.domain.dto.TaskRequest;
import uz.jk.taskmanagement.domain.dto.response.PageResponse;
import uz.jk.taskmanagement.domain.entity.TaskEntity;
import uz.jk.taskmanagement.domain.exception.DataNotfoundException;
import uz.jk.taskmanagement.repository.TaskRepository;


import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public TaskEntity save(TaskRequest taskRequest) {
        TaskEntity taskEntity = TaskEntity.builder()
                .title(taskRequest.title())
                .description(taskRequest.description())
                .dueDate(taskRequest.dueDate())
                .taskStatus(TaskStatus.OPEN)
                .build();
        taskRepository.save(taskEntity);
        return taskEntity;
    }

    @Override
    public TaskEntity findById(UUID id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new DataNotfoundException("task not found"));
    }

    @Override
    public PageResponse<TaskEntity> findAll(int page, int size) {
        Page<TaskEntity> pageResult = taskRepository.findAll(PageRequest.of(page, size));

        return PageResponse.<TaskEntity>builder()
                .page(pageResult.getNumber())
                .size(pageResult.getSize())
                .totalElements(pageResult.getTotalElements())
                .totalPages(pageResult.getTotalPages())
                .content(pageResult.getContent())
                .build();
    }

    @Override
    public TaskEntity update(UUID id, TaskRequest taskRequest) {
        TaskEntity taskEntity = findById(id);

        if (taskRequest.title() != null) {
            taskEntity.setTitle(taskRequest.title());
        }

        if (taskRequest.description() != null) {
            taskEntity.setDescription(taskRequest.description());
        }

        if (taskRequest.dueDate() != null) {
            taskEntity.setDueDate(taskRequest.dueDate());
        }

        if (taskRequest.status() != null) {
            taskEntity.setTaskStatus(taskRequest.status());
        }

        return taskRepository.save(taskEntity);
    }

    @Override
    public void deleteById(UUID id) {
        taskRepository.deleteById(id);
    }
}
