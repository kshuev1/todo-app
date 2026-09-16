package ru.netology.todo_app.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.netology.todo_app.exception.TaskNotFoundException;
import ru.netology.todo_app.model.Task;
import ru.netology.todo_app.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    @Mock
    private TaskRepository repository;

    private TaskService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new TaskService(repository);
    }

    @Test
    void shouldCreateTask() {
        Task task = new Task(null, "Test", false);
        Task savedTask = new Task(1L, "Test", false);

        when(repository.save(task)).thenReturn(savedTask);

        Task result = service.create(task);

        assertEquals(1L, result.getId());
        verify(repository).save(task);
    }

    @Test
    void shouldFindAllTasks() {
        List<Task> tasks = List.of(
                new Task(1L, "Task 1", false),
                new Task(2L, "Task 2", true)
        );

        when(repository.findAll()).thenReturn(tasks);

        assertEquals(2, service.findAll().size());
    }

    @Test
    void shouldUpdateTask() {
        Task existing = new Task(1L, "Old", false);
        Task updated = new Task(null, "New", true);

        when(repository.findById(1L))
                .thenReturn(Optional.of(existing));

        when(repository.save(existing))
                .thenReturn(existing);

        Task result = service.update(1L, updated);

        assertEquals("New", result.getTitle());
        assertTrue(result.isCompleted());
    }

    @Test
    void shouldThrowWhenUpdatingMissingTask() {
        when(repository.findById(999L))
                .thenReturn(Optional.empty());

        assertThrows(
                TaskNotFoundException.class,
                () -> service.update(
                        999L,
                        new Task(null, "Test", false)
                )
        );
    }

    @Test
    void shouldDeleteTask() {
        Task task = new Task(1L, "Task", false);

        when(repository.findById(1L))
                .thenReturn(Optional.of(task));

        service.delete(1L);

        verify(repository).deleteById(1L);
    }

    @Test
    void shouldThrowWhenDeletingMissingTask() {
        when(repository.findById(999L))
                .thenReturn(Optional.empty());

        assertThrows(
                TaskNotFoundException.class,
                () -> service.delete(999L)
        );
    }
}
