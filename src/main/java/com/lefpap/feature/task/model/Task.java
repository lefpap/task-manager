package com.lefpap.feature.task.model;

import java.time.Instant;
import java.util.UUID;

public record Task(
    UUID id,
    UUID groupId,
    String title,
    String description,
    TaskStatus status,
    Instant createdAt,
    Instant updatedAt
) {

    public static TaskBuilder builder() {
        return new TaskBuilder();
    }

    public TaskBuilder toBuilder() {
        var builder = new TaskBuilder();
        builder.setId(id);
        builder.setGroupId(groupId);
        builder.setTitle(title);
        builder.setDescription(description);
        builder.setStatus(status);
        builder.setCreatedAt(createdAt);
        builder.setUpdatedAt(updatedAt);
        return builder;
    }

    public Task copy() {
        TaskBuilder builder = this.toBuilder();
        return builder.build();
    }

    public static class TaskBuilder {
        private UUID id;
        private UUID groupId;
        private String title;
        private String description;
        private TaskStatus status;
        private Instant createdAt;
        private Instant updatedAt;

        public UUID getId() {
            return id;
        }

        public UUID getGroupId() {
            return groupId;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public TaskStatus getStatus() {
            return status;
        }

        public Instant getCreatedAt() {
            return createdAt;
        }

        public Instant getUpdatedAt() {
            return updatedAt;
        }

        public TaskBuilder setId(UUID id) {
            this.id = id;
            return this;
        }

        public TaskBuilder setGroupId(UUID groupId) {
            this.groupId = groupId;
            return this;
        }

        public TaskBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public TaskBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public TaskBuilder setStatus(TaskStatus status) {
            this.status = status;
            return this;
        }

        public TaskBuilder setCreatedAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public TaskBuilder setUpdatedAt(Instant updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Task build() {
            return new Task(
                id,
                groupId,
                title,
                description,
                status,
                createdAt,
                updatedAt
            );
        }
    }

}
