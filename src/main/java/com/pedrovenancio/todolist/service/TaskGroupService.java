package com.pedrovenancio.todolist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pedrovenancio.todolist.model.TaskGroup;
import com.pedrovenancio.todolist.repository.TaskGroupRepository;

@Service
public class TaskGroupService {
    @Autowired
    private TaskGroupRepository taskGroupRepository;

    public List<TaskGroup> getAll() {
        return taskGroupRepository.findAll();
    }

    public TaskGroup store(TaskGroup taskGroup) {
        return taskGroupRepository.save(taskGroup);
    }

    public ResponseEntity<TaskGroup> update(Long id, TaskGroup taskGroup) {
        return taskGroupRepository.findById(id)
        .map(g -> {
            g.setName(taskGroup.getName());
            g.setColor(taskGroup.getColor());
            TaskGroup updatedGroup = taskGroupRepository.save(g);
            return ResponseEntity.ok(updatedGroup);
        }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Void> delete(Long id) {
        return taskGroupRepository.findById(id)
        .map(g -> {
            taskGroupRepository.delete(g);
            return ResponseEntity.ok().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
