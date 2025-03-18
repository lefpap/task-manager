package com.lefpap.feature.task_group.model.api;

import java.util.UUID;

public record ApiTaskGroup(
    UUID id,
    String title,
    String description,
    ApiTaskGroupInfo info
) {}
