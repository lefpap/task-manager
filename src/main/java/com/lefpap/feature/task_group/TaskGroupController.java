package com.lefpap.feature.task_group;

import com.lefpap.feature.task.model.api.ApiTask;
import com.lefpap.feature.task_group.model.api.ApiTaskGroup;
import com.lefpap.feature.task_group.model.api.ApiTaskGroupCreate;
import com.lefpap.feature.task_group.model.api.ApiTaskGroupUpdate;
import com.lefpap.feature.task_group.service.TaskGroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/task-groups")
public class TaskGroupController {

  private final TaskGroupService taskGroupService;

    public TaskGroupController(TaskGroupService taskGroupService) {
        this.taskGroupService = taskGroupService;
    }

    @GetMapping
    public ResponseEntity<List<ApiTaskGroup>> listTaskGroups() {
        List<ApiTaskGroup> apiTaskGroups = taskGroupService.listTaskGroups();
        return ResponseEntity.ok(apiTaskGroups);
    }

    @GetMapping("/{groupId}/tasks")
    public ResponseEntity<List<ApiTask>> listTaskGroupTasks(@PathVariable("groupId") UUID groupId) {
        List<ApiTask> apiTasks = taskGroupService.listTaskGroupTasks(groupId);
        return ResponseEntity.ok().body(apiTasks);
    }

    @GetMapping("/{groupId}")
    public ResponseEntity<ApiTaskGroup> findTaskGroup(@PathVariable("groupId") UUID groupId) {
        ApiTaskGroup apiTaskGroup = taskGroupService.getTaskGroup(groupId);
        return ResponseEntity.ok(apiTaskGroup);
    }

    @PostMapping
    public ResponseEntity<UUID> createTaskGroup(@RequestBody ApiTaskGroupCreate request) {
        UUID groupUUID = taskGroupService.createTaskGroup(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(groupUUID);
    }

    @PutMapping("/{groupId}")
    public ResponseEntity<Void> updateTaskGroup(@PathVariable("groupId") UUID groupId, @RequestBody ApiTaskGroupUpdate request) {
        taskGroupService.updateTaskGroup(groupId, request);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{groupId}")
    public ResponseEntity<Void> deleteTaskGroup(@PathVariable("groupId") UUID groupId) {
        taskGroupService.deleteTaskGroup(groupId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{groupId}/tasks")
    public ResponseEntity<Void> deleteTaskGroupTasks(@PathVariable("groupId") UUID groupId) {
        taskGroupService.deleteTaskGroupTasks(groupId);
        return ResponseEntity.noContent().build();
    }
}
