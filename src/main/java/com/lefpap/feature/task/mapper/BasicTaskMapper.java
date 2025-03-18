package com.lefpap.feature.task.mapper;

import com.lefpap.feature.task.model.Task;
import com.lefpap.feature.task.model.api.ApiTask;
import com.lefpap.feature.task.model.api.ApiTaskCreate;
import com.lefpap.feature.task.model.api.ApiTaskUpdate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BasicTaskMapper implements TaskMapper {
    @Override
    public ApiTask toApiTask(Task task) {
        if (task == null) {
            return null;
        }

        return new ApiTask(
            task.id(),
            task.groupId(),
            task.title(),
            task.description(),
            task.status()
        );
    }

    @Override
    public Task fromApiTaskCreate(ApiTaskCreate request) {
        if (request == null) {
            return null;
        }

        return Task.builder()
            .setTitle(request.title())
            .setDescription(request.description())
            .setGroupId(request.groupId())
            .build();
    }

    @Override
    public Task applyAndGetApiTaskUpdate(Task task, ApiTaskUpdate request) {
        if (task == null || request == null) {
            return null;
        }

        return task.toBuilder()
            .setGroupId(request.groupId())
            .setTitle(request.title())
            .setDescription(request.description())
            .setStatus(request.status())
            .build();
    }
}
