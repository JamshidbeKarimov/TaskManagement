package uz.jk.taskmanagement.domain.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import uz.jk.taskmanagement.domain.constants.TaskStatus;

import java.time.LocalDate;

public record TaskRequest(
        @NotBlank(message = "title cannot me empty or blank") String title,
        String description,
        LocalDate dueDate,
        TaskStatus status
        ) {

        @Override
        public String toString() {
                return "TaskRequest{" +
                        "title='" + title + '\'' +
                        ", description='" + description + '\'' +
                        ", dueDate=" + dueDate +
                        ", status=" + status +
                        '}';
        }
}
