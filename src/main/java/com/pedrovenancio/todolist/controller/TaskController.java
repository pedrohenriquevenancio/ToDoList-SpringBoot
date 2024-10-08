package com.pedrovenancio.todolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedrovenancio.todolist.model.Task;
import com.pedrovenancio.todolist.service.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/")
    public List<Task> getAllTasks() {
        return taskService.getAll();
    }

    @PostMapping("/store")
    public Task store(@Valid @RequestBody Task task) {
        return taskService.store(task);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Task> update(@PathVariable Long id, @Valid @RequestBody Task task) {
        return taskService.update(id, task);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return taskService.delete(id);
    }

    @PutMapping("/set-complete/{id}")
    public ResponseEntity<Task> setComplete(@PathVariable Long id) {
        return taskService.setComplete(id);
    }

    @PutMapping("/set-group/{task_id}/{group_id}")
    public ResponseEntity<Task> setTaskGroup(@PathVariable Long task_id,@PathVariable Long group_id) {
        return taskService.setGroup(task_id, group_id);
    }

    @PutMapping("/unset-group/{id}")
    public ResponseEntity<Task> unsetTaskGroup(@PathVariable Long id) {
        return taskService.unsetGroup(id);
    }
}
