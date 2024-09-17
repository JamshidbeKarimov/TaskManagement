package uz.jk.taskmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.jk.taskmanagement.domain.entity.TaskEntity;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<TaskEntity, UUID> {

}
