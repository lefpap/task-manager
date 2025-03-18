package com.lefpap.feature.task_group.repository;

import com.lefpap.feature.task_group.mapper.TaskGroupAggregateRowMapper;
import com.lefpap.feature.task_group.model.TaskGroupAggregate;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JdbcTaskGroupAggregateRepository implements TaskGroupAggregateRepository {

    private final JdbcClient jdbcClient;
    private final TaskGroupAggregateRowMapper rowMapper;

    public JdbcTaskGroupAggregateRepository(JdbcClient jdbcClient, TaskGroupAggregateRowMapper rowMapper) {
        this.jdbcClient = jdbcClient;
        this.rowMapper = rowMapper;
    }

    @Override
    public List<TaskGroupAggregate> findAll() {
        return jdbcClient.sql("""
        SELECT
            tg.id,
            tg.title,
            tg.description,
            tg.created_at,
            tg.updated_at,
            COUNT(t.id) AS total_tasks,
            SUM(CASE WHEN t.status = 'COMPLETED' THEN 1 ELSE 0 END) AS completed_tasks
        FROM task_groups tg
        LEFT JOIN tasks t ON t.group_id = tg.id
        GROUP BY tg.id
        """).query(rowMapper).list();
    }

    @Override
    public Optional<TaskGroupAggregate> findById(UUID id) {
        return jdbcClient.sql("""
        SELECT
            tg.id,
            tg.title,
            tg.description,
            tg.created_at,
            tg.updated_at,
            COUNT(t.id) AS total_tasks,
            SUM(CASE WHEN t.status = 'COMPLETED' THEN 1 ELSE 0 END) AS completed_tasks
        FROM task_groups tg
        LEFT JOIN tasks t ON t.group_id = tg.id
        WHERE tg.id = :group_id
        GROUP BY tg.id
        """)
            .param("group_id", id)
            .query(rowMapper)
            .optional();
    }

    @Override
    public Optional<TaskGroupAggregate> findByTitle(String title) {
        return jdbcClient.sql("""
        SELECT
            tg.id,
            tg.title,
            tg.description,
            tg.created_at,
            tg.updated_at,
            COUNT(t.id) AS total_tasks,
            SUM(CASE WHEN t.status = 'COMPLETED' THEN 1 ELSE 0 END) AS completed_tasks
        FROM task_groups tg
        LEFT JOIN tasks t ON t.group_id = tg.id
        WHERE tg.title = :title
        GROUP BY tg.id
        """)
            .param("title", title)
            .query(rowMapper)
            .optional();
    }
}
