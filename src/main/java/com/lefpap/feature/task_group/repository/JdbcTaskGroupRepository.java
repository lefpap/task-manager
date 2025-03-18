package com.lefpap.feature.task_group.repository;


import com.lefpap.feature.task_group.model.TaskGroup;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class JdbcTaskGroupRepository implements TaskGroupRepository {

    private final JdbcClient jdbcClient;

    public JdbcTaskGroupRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<TaskGroup> findById(UUID id) {
        return jdbcClient.sql(" SELECT tg.* FROM task_groups tg WHERE tg.id = :group_id ")
            .param("group_id", id)
            .query(TaskGroup.class)
            .optional();
    }

    @Override
    public UUID save(TaskGroup taskGroup) {
        var keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("INSERT INTO task_groups (title, description) values (:title, :description) RETURNING id")
            .param("title", taskGroup.title())
            .param("description", taskGroup.description())
            .update(keyHolder);

        return keyHolder.getKeyAs(UUID.class);
    }

    @Override
    public int update(TaskGroup taskGroup) {
        if (taskGroup.id() == null) {
            throw new IllegalStateException("Can't update task group with null id");
        }

        return jdbcClient.sql("UPDATE task_groups SET title = :title, description = :description, updated_at = NOW() WHERE id = :id")
            .param("id", taskGroup.id())
            .param("title", taskGroup.title())
            .param("description", taskGroup.description())
            .update();
    }

    @Override
    public int deleteAll() {
        return jdbcClient.sql("DELETE FROM task_groups").update();
    }

    @Override
    public int deleteById(UUID id) {
        return jdbcClient.sql("DELETE FROM task_groups WHERE id = :id")
            .param("id", id)
            .update();
    }
}
