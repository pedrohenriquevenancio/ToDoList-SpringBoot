package com.pedrovenancio.todolist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedrovenancio.todolist.model.TaskGroup;
import com.pedrovenancio.todolist.repository.TaskGroupRepository;

@Service
public class TaskGroupService {
    @Autowired
    private TaskGroupRepository taskGroupRepository;

    public List<TaskGroup> getAllTaskGroups() {
        return taskGroupRepository.findAll();
    }
}
