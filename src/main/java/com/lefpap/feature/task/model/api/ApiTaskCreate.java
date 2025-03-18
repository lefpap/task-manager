package com.lefpap.feature.task.model.api;

import java.util.UUID;

public record ApiTaskCreate(
    UUID groupId,
    String title,
    String description
) {}
