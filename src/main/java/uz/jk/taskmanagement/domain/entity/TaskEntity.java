package uz.jk.taskmanagement.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import uz.jk.taskmanagement.domain.constants.TaskStatus;


import java.time.LocalDate;

@Entity(name = "tasks")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskEntity extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String title;
    private String description;
    private LocalDate dueDate;
    private TaskStatus taskStatus;
}
