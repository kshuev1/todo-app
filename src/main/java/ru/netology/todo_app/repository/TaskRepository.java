package ru.netology.todo_app.repository;

import ru.netology.todo_app.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    Task save(Task task);

    List<Task> findAll();

    Optional<Task> findById(Long id);

    void deleteById(Long id);
}
