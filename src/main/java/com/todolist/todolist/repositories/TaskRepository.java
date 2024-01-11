package com.todolist.todolist.repositories;

import com.todolist.todolist.models.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {
    Page<Task> findAll(Pageable pageable);
}
