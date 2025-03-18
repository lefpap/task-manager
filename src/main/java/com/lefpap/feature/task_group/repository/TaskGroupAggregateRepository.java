package com.lefpap.feature.task_group.repository;

import com.lefpap.feature.task_group.model.TaskGroupAggregate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskGroupAggregateRepository {
    List<TaskGroupAggregate> findAll();
    Optional<TaskGroupAggregate> findById(UUID id);
    Optional<TaskGroupAggregate> findByTitle(String title);
}
