package com.fabian.taskflow_api.repository;

import com.fabian.taskflow_api.entity.Task;
import com.fabian.taskflow_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID>
{
    List<Task> findByUser(User user);
}
