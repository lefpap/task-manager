package com.lefpap.feature.task.service;

import com.lefpap.feature.task.model.api.ApiTask;
import com.lefpap.feature.task.model.api.ApiTaskCreate;
import com.lefpap.feature.task.model.api.ApiTaskUpdate;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    List<ApiTask> listTasks();
    List<ApiTask> listUngroupedTasks();
    ApiTask getTask(UUID taskId);
    UUID createTask(ApiTaskCreate request);
    void toggleTaskStatus(UUID taskId);
    void updateTask(UUID taskId, ApiTaskUpdate request);
    void deleteTask(UUID taskId);
}
