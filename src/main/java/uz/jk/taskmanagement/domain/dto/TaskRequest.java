package uz.jk.taskmanagement.domain.dto;


import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record TaskRequest(
        @NotNull String title,
        String description,
        LocalDate dueDate
        ) {

}
