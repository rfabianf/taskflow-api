package com.fabian.taskflow_api.controller;

import com.fabian.taskflow_api.dto.request.TaskRequest;
import com.fabian.taskflow_api.dto.response.TaskResponse;
import com.fabian.taskflow_api.dto.response.UserResponse;
import com.fabian.taskflow_api.service.Interfaces.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse createTask(@RequestBody TaskRequest taskRequest) {
        return taskService.createTask(taskRequest);
    }

    @GetMapping
    List<TaskResponse> getAllTasks()
    {
        return taskService.getAllTasks();
    }

    @GetMapping("/me")
    List<TaskResponse> getMyTasks()
    {
        return taskService.getMyTasks();
    }
}
