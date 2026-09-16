package ru.netology.todo_app.repository;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.todo_app.model.Task;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskRepositoryTest {

    private InMemoryTaskRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryTaskRepository();
    }

    @Test
    void shouldSaveTask() {
        Task task = new Task(null, "Test task", false);

        Task saved = repository.save(task);

        assertNotNull(saved.getId());
        assertEquals("Test task", saved.getTitle());
    }

    @Test
    void shouldFindAllTasks() {
        repository.save(new Task(null, "Task 1", false));
        repository.save(new Task(null, "Task 2", true));

        List<Task> tasks = repository.findAll();

        assertEquals(2, tasks.size());
    }

    @Test
    void shouldFindTaskById() {
        Task saved = repository.save(
                new Task(null, "Task", false)
        );

        assertTrue(repository.findById(saved.getId()).isPresent());
    }

    @Test
    void shouldDeleteTask() {
        Task saved = repository.save(
                new Task(null, "Task", false)
        );

        repository.deleteById(saved.getId());

        assertTrue(repository.findById(saved.getId()).isEmpty());
    }
}