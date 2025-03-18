package com.lefpap.feature.task.repository;

import com.lefpap.feature.task.model.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepository {
    List<Task> findAll();
    List<Task> findAllUngrouped();
    List<Task> findByGroupId(UUID groupId);
    Optional<Task> findById(UUID id);
    UUID save(Task task);
    long update(Task task);
    long deleteAll();
    long deleteAllByGroupId(UUID groupId);
    long deleteById(UUID id);
}
