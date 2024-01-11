package com.todolist.todolist.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "task")
public class Task {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "description")
    @NotEmpty(message = "Description cannot be empty")
    @Size(max = 100, message = "Description cannot exceed 100 characters")
    private String description;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    public Task() {
    }
    public Task(int id, String description, @NotNull Status status) {
        this.id = id;
        this.description = description;
        this.status = status;
    }
    public enum Status {
        IN_PROGRESS,
        DONE,
        PAUSED
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
