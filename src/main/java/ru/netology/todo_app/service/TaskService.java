package ru.netology.todo_app.service;


import org.springframework.stereotype.Service;
import ru.netology.todo_app.exception.TaskNotFoundException;
import ru.netology.todo_app.model.Task;
import ru.netology.todo_app.repository.TaskRepository;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Task create(Task task) {
        task.setId(null);
        return repository.save(task);
    }

    public List<Task> findAll() {
        return repository.findAll();
    }

    public Task update(Long id, Task updatedTask) {
        Task existingTask = repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setCompleted(updatedTask.isCompleted());

        return repository.save(existingTask);
    }

    public void delete(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        repository.deleteById(id);
    }
}