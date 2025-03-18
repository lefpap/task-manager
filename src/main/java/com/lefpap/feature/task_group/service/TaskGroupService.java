package com.lefpap.feature.task_group.service;

import com.lefpap.feature.task.model.api.ApiTask;
import com.lefpap.feature.task_group.model.api.ApiTaskGroup;
import com.lefpap.feature.task_group.model.api.ApiTaskGroupCreate;
import com.lefpap.feature.task_group.model.api.ApiTaskGroupUpdate;

import java.util.List;
import java.util.UUID;

public interface TaskGroupService {
    List<ApiTaskGroup> listTaskGroups();
    List<ApiTask> listTaskGroupTasks(UUID groupId);
    ApiTaskGroup getTaskGroup(UUID groupId);
    UUID createTaskGroup(ApiTaskGroupCreate request);
    void updateTaskGroup(UUID groupId, ApiTaskGroupUpdate request);
    void deleteTaskGroup(UUID groupId);
    void deleteTaskGroupTasks(UUID groupId);
}
