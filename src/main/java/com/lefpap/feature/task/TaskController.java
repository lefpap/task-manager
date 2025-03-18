package com.lefpap.feature.task;

import com.lefpap.feature.task.model.api.ApiTask;
import com.lefpap.feature.task.model.api.ApiTaskCreate;
import com.lefpap.feature.task.model.api.ApiTaskUpdate;
import com.lefpap.feature.task.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<ApiTask>> listTasks() {
        List<ApiTask> apiTasks = taskService.listTasks();
        return ResponseEntity.ok().body(apiTasks);
    }

    @GetMapping("/ungrouped")
    public ResponseEntity<List<ApiTask>> listUngroupedTasks() {
        List<ApiTask> apiTasks = taskService.listUngroupedTasks();
        return ResponseEntity.ok().body(apiTasks);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<ApiTask> findTask(@PathVariable("taskId") UUID taskId) {
        ApiTask apiTask = taskService.getTask(taskId);
        return ResponseEntity.ok().body(apiTask);
    }

    @PostMapping
    public ResponseEntity<UUID> createTask(@RequestBody ApiTaskCreate taskCreateRequest) {
        UUID taskUUID = taskService.createTask(taskCreateRequest);
        return ResponseEntity
            .created(URI.create("/api/v1/tasks/%s".formatted(taskUUID)))
            .body(taskUUID);
    }

    @PostMapping("/{taskId}/toggle")
    public ResponseEntity<Void> toggleTaskStatus(@PathVariable("taskId") UUID taskId) {
        taskService.toggleTaskStatus(taskId);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Void> updateTask(@PathVariable("taskId") UUID taskId, @RequestBody ApiTaskUpdate taskUpdateRequest) {
        taskService.updateTask(taskId, taskUpdateRequest);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable("taskId") UUID taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }
}
