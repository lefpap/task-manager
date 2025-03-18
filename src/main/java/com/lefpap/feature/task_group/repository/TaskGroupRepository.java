package com.lefpap.feature.task_group.repository;


import com.lefpap.feature.task_group.model.TaskGroup;

import java.util.Optional;
import java.util.UUID;

public interface TaskGroupRepository {
    Optional<TaskGroup> findById(UUID id);
    UUID save(TaskGroup taskGroup);
    int update(TaskGroup taskGroup);
    int deleteAll();
    int deleteById(UUID id);
}
