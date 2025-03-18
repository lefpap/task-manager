package com.lefpap.feature.task_group.model.api;

public record ApiTaskGroupInfo(
   Long totalTasks,
   Long completedTasks,
   Long pendingTasks
) {}
