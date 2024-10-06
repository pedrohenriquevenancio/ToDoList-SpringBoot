package com.pedrovenancio.todolist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedrovenancio.todolist.model.Task;
import com.pedrovenancio.todolist.repository.TaskRepository;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task store(Task task) {
        return taskRepository.save(task);
    }
}
