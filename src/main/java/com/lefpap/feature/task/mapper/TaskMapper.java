package com.lefpap.feature.task.mapper;

import com.lefpap.feature.task.model.Task;
import com.lefpap.feature.task.model.api.ApiTask;
import com.lefpap.feature.task.model.api.ApiTaskCreate;
import com.lefpap.feature.task.model.api.ApiTaskUpdate;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public interface TaskMapper {

    ApiTask toApiTask(Task task);

    default List<ApiTask> toApiTasks(Collection<Task> tasks) {
        if (tasks == null || tasks.isEmpty()) {
            return Collections.emptyList();
        }

        return tasks.stream().map(this::toApiTask).toList();
    }

    Task fromApiTaskCreate(ApiTaskCreate request);
    Task applyAndGetApiTaskUpdate(Task task, ApiTaskUpdate request);
}
