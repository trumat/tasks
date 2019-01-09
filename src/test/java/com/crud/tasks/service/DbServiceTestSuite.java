package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTestSuite {
    @InjectMocks
    private DbService dbService;
    @Mock
    private TaskRepository repository;

    @Test
    public void getAllTasks() {
        //Given
        List<Task> tasks = new ArrayList<>();
        Task task = new Task(1L, "title", "content");
        tasks.add(task);
        when(repository.findAll()).thenReturn(tasks);

        //When
        List<Task> fetchedTasks = dbService.getAllTasks();

        //Then
        assertEquals(1, fetchedTasks.size());
    }

    @Test
    public void getTask() {
        //Given
        Optional<Task> taskOptional = Optional.of(new Task(1L, "title", "content"));
        when(repository.findById(1L)).thenReturn(taskOptional);

        //When
        Optional<Task> fetchedTask = dbService.getTask(1L);

        //Then
        assertEquals(taskOptional, fetchedTask);
    }

    @Test
    public void saveTask() {
        //Given
        Task task = new Task(1L, "title", "content");
        when(repository.save(task)).thenReturn(task);

        //When
        Task savedTask = dbService.saveTask(task);

        //Then
        assertEquals(task, savedTask);
    }
}