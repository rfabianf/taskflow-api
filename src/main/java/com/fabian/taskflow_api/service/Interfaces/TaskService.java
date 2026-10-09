package com.fabian.taskflow_api.service.Interfaces;

import com.fabian.taskflow_api.dto.request.TaskRequest;
import com.fabian.taskflow_api.dto.response.TaskResponse;
import com.fabian.taskflow_api.entity.Status;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    TaskResponse createTask(TaskRequest request);
    List<TaskResponse> getAllTasks();
    List<TaskResponse> getMyTasks();
    TaskResponse updateTask(UUID idTask, TaskRequest request);
}
