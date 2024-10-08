package com.pedrovenancio.todolist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pedrovenancio.todolist.model.Task;
import com.pedrovenancio.todolist.model.TaskGroup;
import com.pedrovenancio.todolist.repository.TaskGroupRepository;
import com.pedrovenancio.todolist.repository.TaskRepository;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskGroupRepository groupRepository;

    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseGet(() -> {
            return null;
        });
    }

    public Task store(Task task) {
        return taskRepository.save(task);
    }

    public ResponseEntity<Task> update(Long id, Task task) {
        return taskRepository.findById(id)
        .map(t -> {
            t.setTitle(task.getTitle());
            t.setDescription(task.getDescription());
            t.setCompleted(task.isCompleted());
            Task updatedTask = taskRepository.save(t);
            return ResponseEntity.ok(updatedTask);
        }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Void> delete(Long id) {
        return taskRepository.findById(id)
        .map(t -> {
            taskRepository.delete(t);
            return ResponseEntity.ok().<Void>build();
        })
        .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Task> setComplete(Long id) {
        return taskRepository.findById(id)
        .map(t -> {
            t.setCompleted(!t.isCompleted());
            Task updatedTask = taskRepository.save(t);
            return ResponseEntity.ok(updatedTask);
        }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Task> setGroup(Long task_id, Long group_id) {
        TaskGroup taskGroup = groupRepository.findById(group_id)
        .orElseThrow(() -> new IllegalArgumentException("Group not found."));

        return taskRepository.findById(task_id)
        .map(t -> {
            t.setTaskGroup(taskGroup);
            Task updatedTask = taskRepository.save(t);
            return ResponseEntity.ok(updatedTask);
        }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Task> unsetGroup(Long id) {
        return taskRepository.findById(id)
        .map(t -> {
            t.setTaskGroup(null);
            Task updatedTask = taskRepository.save(t);
            return ResponseEntity.ok(updatedTask);
        }).orElse(ResponseEntity.notFound().build());
    }
}
