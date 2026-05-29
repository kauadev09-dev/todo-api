package com.example.todo_api.dto;

import jakarta.validation.constraints.NotBlank;

public class TaskRequestDTO {

    @NotBlank(message = "Título é obrigatório")
    private String title;

    private String description;

    private boolean completed;

    // getters e setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String d) { this.description = d; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean c) { this.completed = c; }
}