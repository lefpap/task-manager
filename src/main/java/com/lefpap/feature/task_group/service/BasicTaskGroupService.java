package com.lefpap.feature.task_group.service;

import com.lefpap.feature.task.mapper.TaskMapper;
import com.lefpap.feature.task.model.Task;
import com.lefpap.feature.task.model.api.ApiTask;
import com.lefpap.feature.task.repository.TaskRepository;
import com.lefpap.feature.task_group.mapper.TaskGroupMapper;
import com.lefpap.feature.task_group.model.TaskGroup;
import com.lefpap.feature.task_group.model.TaskGroupAggregate;
import com.lefpap.feature.task_group.model.api.ApiTaskGroup;
import com.lefpap.feature.task_group.model.api.ApiTaskGroupCreate;
import com.lefpap.feature.task_group.model.api.ApiTaskGroupUpdate;
import com.lefpap.feature.task_group.repository.TaskGroupAggregateRepository;
import com.lefpap.feature.task_group.repository.TaskGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BasicTaskGroupService implements TaskGroupService {

    private final TaskGroupRepository taskGroupRepository;
    private final TaskGroupAggregateRepository taskGroupAggregateRepository;
    private final TaskRepository taskRepository;
    private final TaskGroupMapper taskGroupMapper;
    private final TaskMapper taskMapper;

    public BasicTaskGroupService(
        TaskGroupRepository taskGroupRepository,
        TaskGroupAggregateRepository taskGroupAggregateRepository,
        TaskRepository taskRepository,
        TaskGroupMapper taskGroupMapper,
        TaskMapper taskMapper
    ) {
        this.taskGroupRepository = taskGroupRepository;
        this.taskGroupAggregateRepository = taskGroupAggregateRepository;
        this.taskRepository = taskRepository;
        this.taskGroupMapper = taskGroupMapper;
        this.taskMapper = taskMapper;
    }

    @Override
    public List<ApiTaskGroup> listTaskGroups() {
        List<TaskGroupAggregate> taskGroupAggregates = taskGroupAggregateRepository.findAll();
        return taskGroupMapper.toApiTaskGroups(taskGroupAggregates);
    }

    @Override
    public List<ApiTask> listTaskGroupTasks(UUID groupId) {
        List<Task> groupedTasks = taskRepository.findByGroupId(groupId);
        return taskMapper.toApiTasks(groupedTasks);
    }

    @Override
    public ApiTaskGroup getTaskGroup(UUID groupId) {
        TaskGroupAggregate taskGroupAggregate = taskGroupAggregateRepository.findById(groupId).orElseThrow();
        return taskGroupMapper.toApiTaskGroup(taskGroupAggregate);
    }

    @Override
    public UUID createTaskGroup(ApiTaskGroupCreate request) {
        TaskGroup taskGroup = taskGroupMapper.fromApiTaskGroupCreate(request);
        return taskGroupRepository.save(taskGroup);
    }

    @Override
    public void updateTaskGroup(UUID groupId, ApiTaskGroupUpdate request) {
        TaskGroup updatedTaskGroup = taskGroupRepository.findById(groupId)
            .map(taskGroup -> taskGroupMapper.applyAndGetApiTaskGroupUpdate(taskGroup, request))
            .orElseThrow();

        taskGroupRepository.update(updatedTaskGroup);
    }

    @Override
    public void deleteTaskGroup(UUID groupId) {
        taskGroupRepository.deleteById(groupId);
    }

    @Override
    public void deleteTaskGroupTasks(UUID groupId) {
        taskRepository.deleteAllByGroupId(groupId);
    }
}
