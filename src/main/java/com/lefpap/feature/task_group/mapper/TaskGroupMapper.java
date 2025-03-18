package com.lefpap.feature.task_group.mapper;

import com.lefpap.feature.task_group.model.TaskGroup;
import com.lefpap.feature.task_group.model.TaskGroupAggregate;
import com.lefpap.feature.task_group.model.api.ApiTaskGroup;
import com.lefpap.feature.task_group.model.api.ApiTaskGroupCreate;
import com.lefpap.feature.task_group.model.api.ApiTaskGroupUpdate;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public interface TaskGroupMapper {

    ApiTaskGroup toApiTaskGroup(TaskGroupAggregate taskGroupAggregate);

    default List<ApiTaskGroup> toApiTaskGroups(Collection<TaskGroupAggregate> taskGroupAggregate) {
        if (taskGroupAggregate == null || taskGroupAggregate.isEmpty()) {
            return Collections.emptyList();
        }

        return taskGroupAggregate.stream().map(this::toApiTaskGroup).toList();
    }

    TaskGroup fromApiTaskGroupCreate(ApiTaskGroupCreate request);
    TaskGroup applyAndGetApiTaskGroupUpdate(TaskGroup taskGroup, ApiTaskGroupUpdate request);
}
