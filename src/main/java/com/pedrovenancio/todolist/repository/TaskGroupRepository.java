package com.pedrovenancio.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pedrovenancio.todolist.model.TaskGroup;

@Repository
public interface TaskGroupRepository extends JpaRepository<TaskGroup, Long> {}
