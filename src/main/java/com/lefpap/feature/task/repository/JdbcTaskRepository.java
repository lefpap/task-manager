package com.lefpap.feature.task.repository;

import com.lefpap.feature.task.model.Task;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JdbcTaskRepository implements TaskRepository {

    private final JdbcClient jdbcClient;

    public JdbcTaskRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public List<Task> findAll() {
        return jdbcClient.sql("SELECT t.* FROM tasks t")
            .query(Task.class)
            .list();
    }

    @Override
    public List<Task> findAllUngrouped() {
        return jdbcClient.sql("SELECT t.* FROM tasks t WHERE t.group_id IS NULL")
            .query(Task.class)
            .list();
    }

    @Override
    public List<Task> findByGroupId(UUID groupId) {
        return jdbcClient.sql("SELECT t.* FROM tasks t WHERE t.group_id = :group_id")
            .param("group_id", groupId)
            .query(Task.class)
            .list();
    }

    @Override
    public Optional<Task> findById(UUID id) {
        return jdbcClient.sql("SELECT t.* FROM tasks t WHERE t.id = :id")
            .param("id", id)
            .query(Task.class)
            .optional();
    }

    @Override
    public UUID save(Task task) {
        var keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("INSERT INTO tasks (title, description, group_id) values (:title, :description, :group_id) RETURNING id")
            .param("title", task.title())
            .param("description", task.description())
            .param("group_id", task.groupId())
            .update(keyHolder);

        return keyHolder.getKeyAs(UUID.class);
    }

    @Override
    public long update(Task task) {
        if (task.id() == null) {
            throw new IllegalStateException("Can't update task with null id");
        }

        return jdbcClient.sql("UPDATE tasks SET title = :title, description = :description, status = :status::\"TaskStatus\", group_id = :group_id, updated_at = NOW() WHERE id = :id")
            .param("id", task.id())
            .param("title", task.title())
            .param("description", task.description())
            .param("status", task.status().name())
            .param("group_id", task.groupId())
            .update();
    }

    @Override
    public long deleteAll() {
        return jdbcClient.sql("DELETE FROM tasks").update();
    }

    @Override
    public long deleteAllByGroupId(UUID groupId) {
        return jdbcClient.sql("DELETE FROM tasks WHERE group_id = :group_id").update();
    }

    @Override
    public long deleteById(UUID id) {
        return jdbcClient.sql("DELETE FROM tasks WHERE id = :id")
            .param("id", id)
            .update();
    }
}
