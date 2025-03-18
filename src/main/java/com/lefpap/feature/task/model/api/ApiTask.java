package com.lefpap.feature.task.model.api;

import com.lefpap.feature.task.model.TaskStatus;

import java.util.UUID;

public record ApiTask(
    UUID id,
    UUID groupId,
    String title,
    String description,
    TaskStatus status
) {}
