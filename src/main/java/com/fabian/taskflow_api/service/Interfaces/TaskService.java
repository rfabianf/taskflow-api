package com.fabian.taskflow_api.service.Interfaces;

import com.fabian.taskflow_api.dto.request.TaskRequest;
import com.fabian.taskflow_api.dto.response.TaskResponse;

import java.util.List;

public interface TaskService {
    TaskResponse createTask(TaskRequest request);
    List<TaskResponse> getAllTasks();
    List<TaskResponse> getMyTasks();
}
