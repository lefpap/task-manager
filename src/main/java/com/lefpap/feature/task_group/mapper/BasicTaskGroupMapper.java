package com.lefpap.feature.task_group.mapper;

import com.lefpap.feature.task_group.model.TaskGroup;
import com.lefpap.feature.task_group.model.TaskGroupAggregate;
import com.lefpap.feature.task_group.model.api.ApiTaskGroup;
import com.lefpap.feature.task_group.model.api.ApiTaskGroupCreate;
import com.lefpap.feature.task_group.model.api.ApiTaskGroupInfo;
import com.lefpap.feature.task_group.model.api.ApiTaskGroupUpdate;
import org.springframework.stereotype.Component;


@Component
public class BasicTaskGroupMapper implements TaskGroupMapper {

    @Override
    public ApiTaskGroup toApiTaskGroup(TaskGroupAggregate taskGroupAggregate) {
        if (taskGroupAggregate == null) {
            return null;
        }

        var info = new ApiTaskGroupInfo(
            taskGroupAggregate.info().totalTasks(),
            taskGroupAggregate.info().completedTasks(),
            taskGroupAggregate.info().pendingTasks()
        );

        return new ApiTaskGroup(
            taskGroupAggregate.taskGroup().id(),
            taskGroupAggregate.taskGroup().title(),
            taskGroupAggregate.taskGroup().description(),
            info
        );
    }

    @Override
    public TaskGroup fromApiTaskGroupCreate(ApiTaskGroupCreate request) {
        return TaskGroup.builder()
            .setTitle(request.title())
            .setDescription(request.description())
            .build();
    }

    @Override
    public TaskGroup applyAndGetApiTaskGroupUpdate(TaskGroup taskGroup, ApiTaskGroupUpdate request) {
        if (taskGroup == null || request == null) {
            return null;
        }

        return taskGroup.toBuilder()
            .setTitle(request.title())
            .setDescription(request.description())
            .build();
    }
}
