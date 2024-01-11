package com.todolist.todolist.controllers;

import com.todolist.todolist.models.Task;
import com.todolist.todolist.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping()
    public String index(Model model, @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int size) {
        Page<Task> tasksPage = taskService.getAllTasks(PageRequest.of(page, size));
        model.addAttribute("tasks", tasksPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("totalPages", tasksPage.getTotalPages());
        return "tasks/index";
    }
    @PostMapping
    public String create(@ModelAttribute("task")  @Valid Task task,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "tasks/index";

        taskService.save(task);
        return "redirect:/tasks";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id")int id) {
        taskService.delete(id);
        return "redirect:/tasks";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable int id) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "tasks/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("task")  @Valid Task task,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "tasks/edit";

        taskService.update(id, task);
        return "redirect:/tasks";
    }
}