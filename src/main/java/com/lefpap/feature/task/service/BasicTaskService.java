package com.lefpap.feature.task.service;

import com.lefpap.feature.task.mapper.TaskMapper;
import com.lefpap.feature.task.model.Task;
import com.lefpap.feature.task.model.TaskStatus;
import com.lefpap.feature.task.model.api.ApiTask;
import com.lefpap.feature.task.model.api.ApiTaskCreate;
import com.lefpap.feature.task.model.api.ApiTaskUpdate;
import com.lefpap.feature.task.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BasicTaskService implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public BasicTaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    public List<ApiTask> listTasks() {
        List<Task> tasks = taskRepository.findAll();
        return taskMapper.toApiTasks(tasks);
    }

    @Override
    public List<ApiTask> listUngroupedTasks() {
        List<Task> ungroupedTasks = taskRepository.findAllUngrouped();
        return taskMapper.toApiTasks(ungroupedTasks);
    }

    @Override
    public ApiTask getTask(UUID taskId) {
        Task task = taskRepository.findById(taskId)
            .orElseThrow();

        return taskMapper.toApiTask(task);
    }

    @Override
    public UUID createTask(ApiTaskCreate request) {
        Task task = taskMapper.fromApiTaskCreate(request);
        return taskRepository.save(task);
    }

    @Override
    public void toggleTaskStatus(UUID taskId) {
        Task updatedTask = taskRepository.findById(taskId)
            .map(Task::toBuilder)
            .map(taskBuilder -> taskBuilder.setStatus(TaskStatus.COMPLETED.equals(taskBuilder.getStatus())
                ? TaskStatus.PENDING
                : TaskStatus.COMPLETED)
            .build())
            .orElseThrow();

        taskRepository.update(updatedTask);
    }

    @Override
    public void updateTask(UUID taskId, ApiTaskUpdate request) {
        Task updatedTask = taskRepository.findById(taskId)
            .map(task -> taskMapper.applyAndGetApiTaskUpdate(task, request))
            .orElseThrow();

        taskRepository.update(updatedTask);
    }

    @Override
    public void deleteTask(UUID taskId) {
        taskRepository.deleteById(taskId);
    }
}
