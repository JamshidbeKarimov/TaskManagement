package uz.jk.taskmanagement.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.jk.taskmanagement.domain.dto.request.TaskRequest;
import uz.jk.taskmanagement.domain.dto.response.PageResponse;
import uz.jk.taskmanagement.domain.entity.TaskEntity;
import uz.jk.taskmanagement.service.task.TaskService;

import java.util.UUID;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
@Tag(name = "Task Management", description = "Operations for managing tasks")
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    @Operation(
            summary = "Create a Task",
            description = "Creates a task from input. Required fields: 'title'. Date format: 'yyyy-MM-dd'."
    )
    public TaskEntity create(@Valid @RequestBody TaskRequest taskRequest) {
        return taskService.save(taskRequest);
    }


    @GetMapping
    @Operation(
            summary = "Get all Tasks",
            description = "Returns a list of all tasks. Supports pagination."
    )
    public PageResponse<TaskEntity> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return taskService.findAll(page, size);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update a Task",
            description = "Updates a task by ID. All fields are optional. Values for status: 'OPEN', 'IN_PROGRESS', 'DONE'."
    )
    public TaskEntity update(@PathVariable UUID id, @RequestBody TaskRequest taskRequest) {
        return taskService.update(id, taskRequest);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Task by id.")
    public void delete(@PathVariable UUID id) {
        taskService.deleteById(id);
    }
}
