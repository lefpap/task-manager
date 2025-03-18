package com.lefpap.feature.task_group.model;

public record TaskGroupInfo(
    Long totalTasks,
    Long completedTasks,
    Long pendingTasks
) {}
