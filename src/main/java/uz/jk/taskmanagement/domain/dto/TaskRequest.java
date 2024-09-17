package uz.jk.taskmanagement.domain.dto;


import jakarta.validation.constraints.NotNull;
import uz.jk.taskmanagement.domain.constants.TaskStatus;

import java.time.LocalDate;

public record TaskRequest(
        @NotNull String title,
        String description,
        LocalDate dueDate,
        TaskStatus status
        ) {

}
