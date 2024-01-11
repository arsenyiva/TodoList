package com.todolist.todolist.services;

import com.todolist.todolist.models.Task;
import com.todolist.todolist.repositories.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Page<Task> getAllTasks(PageRequest pageRequest) {
        return taskRepository.findAll(pageRequest);
    }

    public Task getTaskById(int id) {
        Optional<Task> foundTask = taskRepository.findById(id);
        return foundTask.orElse(null);
    }
    public long getTotalTasks() {
        return taskRepository.count();
    }
    @Transactional
    public void save(Task task) {
        taskRepository.save(task);
    }

    @Transactional
    public void update(int id,Task updatedTusk){
        updatedTusk.setId(id);
        taskRepository.save(updatedTusk);
    }
    @Transactional
    public void delete(int id) {
        taskRepository.deleteById(id);
    }
}
