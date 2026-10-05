package com.fabian.taskflow_api.service.Impl;

import com.fabian.taskflow_api.dto.request.TaskRequest;
import com.fabian.taskflow_api.dto.response.TaskResponse;
import com.fabian.taskflow_api.dto.response.UserResponse;
import com.fabian.taskflow_api.entity.Status;
import com.fabian.taskflow_api.entity.Task;
import com.fabian.taskflow_api.entity.User;
import com.fabian.taskflow_api.repository.TaskRepository;
import com.fabian.taskflow_api.repository.UserRepository;
import com.fabian.taskflow_api.service.Interfaces.TaskService;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskSeviceImpl implements TaskService {
    private final TaskRepository  taskRepository;
    private final UserRepository userRepository;

    public TaskSeviceImpl(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }


    @Override
    @Transactional
    public TaskResponse createTask(TaskRequest request) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email).orElseThrow();

        Task task = new Task();
        task.setDescripcionTarea(request.getDescripcionTarea());
        task.setNombreTarea(request.getNombreTarea());
        task.setPrioridad(request.getPrioridad());
        task.setFechaTarea(LocalDate.now());
        task.setStatus(Status.BACKLOG);
        task.setUser(user);

        taskRepository.save(task);

        return new TaskResponse(
        task.getNombreTarea(),
        task.getDescripcionTarea(),
        task.getFechaTarea(),
        task.getPrioridad(),
        task.getStatus(),
        task.getUser().getEmail());
    }

    public List<TaskResponse> getAllTasks()
    {
        return taskRepository.findAll().stream()
                .map(task -> new TaskResponse(
                        task.getNombreTarea(),
                        task.getDescripcionTarea(),
                        task.getFechaTarea(),
                        task.getPrioridad(),
                        task.getStatus(),
                        task.getUser().getEmail())).toList();
    }

    public List<TaskResponse> getMyTasks()
    {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email).orElseThrow();

        return taskRepository.findByUser(user).stream()
                .map(task -> new TaskResponse(
                        task.getNombreTarea(),
                        task.getDescripcionTarea(),
                        task.getFechaTarea(),
                        task.getPrioridad(),
                        task.getStatus(),
                        task.getUser().getEmail())).toList();
    }
}
