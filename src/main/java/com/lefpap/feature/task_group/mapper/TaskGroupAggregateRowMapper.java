package com.lefpap.feature.task_group.mapper;

import com.lefpap.feature.task_group.model.TaskGroup;
import com.lefpap.feature.task_group.model.TaskGroupAggregate;
import com.lefpap.feature.task_group.model.TaskGroupInfo;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class TaskGroupAggregateRowMapper implements RowMapper<TaskGroupAggregate> {

    @Override
    public TaskGroupAggregate mapRow(ResultSet rs, int rowNum) throws SQLException {
        var groupId = (UUID) rs.getObject("id");
        var title = rs.getString("title");
        var description = rs.getString("description");
        var createdAt = rs.getTimestamp("created_at").toInstant();
        var updatedAt = rs.getTimestamp("updated_at").toInstant();
        var taskGroup = new TaskGroup(groupId, title, description, createdAt, updatedAt);

        var totalTasks = rs.getLong("total_tasks");
        var completedTasks = rs.getLong("completed_tasks");
        var pendingTasks = totalTasks - completedTasks;
        var info = new TaskGroupInfo(totalTasks, completedTasks, pendingTasks);

        return new TaskGroupAggregate(taskGroup, info);
    }
}
