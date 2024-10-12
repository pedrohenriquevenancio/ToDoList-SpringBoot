package com.pedrovenancio.todolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedrovenancio.todolist.model.TaskGroup;
import com.pedrovenancio.todolist.service.TaskGroupService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/task-group")
public class TaskGroupController {
    @Autowired
    private TaskGroupService taskGroupService;

    @GetMapping("/")
    public List<TaskGroup> getAllGroups() {
        return taskGroupService.getAll();
    }

    @PostMapping("/")
    public TaskGroup store(@Valid @RequestBody TaskGroup taskGroup) {
        return taskGroupService.store(taskGroup);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskGroup> update(@PathVariable Long id, @Valid @RequestBody TaskGroup taskGroup) {
        return taskGroupService.update(id, taskGroup);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return taskGroupService.delete(id);
    }

    @PostMapping("/test")
    public TaskGroup[] test() {
        String[] colors = {"#FF6347", "#FFD700", "#FF8C00", "#FFA07A", "#FF69B4","#8A2BE2"};
        TaskGroup[] tasksGroups = new TaskGroup[5];
        for (int i = 0; i < 5; i++) {
            TaskGroup task = new TaskGroup();
            task.setName("Task Group " + (i + 1));
            task.setColor(colors[i % colors.length]);
            tasksGroups[i] = taskGroupService.store(task);
        }
        return tasksGroups;
    }
}
