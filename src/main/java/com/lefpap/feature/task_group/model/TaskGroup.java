package com.lefpap.feature.task_group.model;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public record TaskGroup(
    UUID id,
    String title,
    String description,
    Instant createdAt,
    Instant updatedAt
) {

    public TaskGroup {
        if (Objects.isNull(title) || title.isBlank()) {
            throw new IllegalStateException("The title can't be null");
        }
    }

    public static TaskGroupBuilder builder() {
        return new TaskGroupBuilder();
    }

    public TaskGroupBuilder toBuilder() {
        var builder = new TaskGroupBuilder();
        builder.setId(id);
        builder.setTitle(title);
        builder.setDescription(description);
        builder.setCreatedAt(createdAt);
        builder.setUpdatedAt(updatedAt);
        return builder;
    }

    public TaskGroup copy() {
        var builder = this.toBuilder();
        return builder.build();
    }

    public static class TaskGroupBuilder {
        private UUID id;
        private String title;
        private String description;
        private Instant createdAt;
        private Instant updatedAt;

        public UUID getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public Instant getCreatedAt() {
            return createdAt;
        }

        public Instant getUpdatedAt() {
            return updatedAt;
        }


        public TaskGroupBuilder setId(UUID id) {
            this.id = id;
            return this;
        }

        public TaskGroupBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public TaskGroupBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public TaskGroupBuilder setCreatedAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public TaskGroupBuilder setUpdatedAt(Instant updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public TaskGroup build() {
            return new TaskGroup(
                id,
                title,
                description,
                createdAt,
                updatedAt
            );
        }
    }
}
