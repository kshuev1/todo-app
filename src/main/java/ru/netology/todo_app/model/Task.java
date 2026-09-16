package ru.netology.todo_app.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Task {

    private Long id;

    @NotBlank(message = "Название задачи не может быть пустым")
    @Size(max = 255, message = "Название задачи не должно превышать 255 символов")
    private String title;

    private boolean completed;

    public Task() {
    }

    public Task(Long id, String title, boolean completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}