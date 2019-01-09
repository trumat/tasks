package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.dto.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaskControllerTestSuite {
    @InjectMocks
    private TaskController taskController;
    @Mock
    private TaskMapper taskMapper;
    @Mock
    private DbService service;

    @Test
    public void getTasks() {
        //Given
        Task task = new Task(1L, "title", "content");
        TaskDto taskDto = new TaskDto(1L, "title", "content");
        List<Task> tasks = new ArrayList<>();
        List<TaskDto> taskDtos = new ArrayList<>();
        tasks.add(task);
        taskDtos.add(taskDto);
        when(service.getAllTasks()).thenReturn(tasks);
        when(taskMapper.mapToTaskDtoList(tasks)).thenReturn(taskDtos);

        //When
        List<TaskDto> fetchedTasks = taskController.getTasks();

        //Then
        assertEquals(taskDtos, fetchedTasks);
    }

    @Test
    public void getTaskHappyPath() throws TaskNotFoundException {
        //Given
        TaskDto taskDto = new TaskDto(1L, "title", "content");
        Task task = new Task(1L, "title", "content");
        Optional<Task> taskOptional = Optional.of(task);
        when(service.getTask(1L)).thenReturn(taskOptional);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        //When
        TaskDto fetchedTask = taskController.getTask(1L);

        //Then
        assertEquals(taskDto, fetchedTask);
    }

    @Test(expected = TaskNotFoundException.class)
    public void getTaskNotFound() throws TaskNotFoundException{
        //Given
        TaskDto taskDto = new TaskDto(1L, "title", "content");
        Task task = new Task(1L, "title", "content");
        Optional<Task> taskOptional = Optional.ofNullable(null);
        when(service.getTask(1L)).thenReturn(taskOptional);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        //When
        TaskDto fetchedTask = taskController.getTask(1L);

        //Then
        //do nothing
    }

    @Test
    public void updateTask() {
        //Given
        Task task = new Task(1L, "title", "content");
        TaskDto taskDto = new TaskDto(1L, "title", "content");
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(service.saveTask(task)).thenReturn(task);

        //When
        TaskDto updatedTask = taskController.updateTask(taskDto);

        //Then
        assertEquals(taskDto, updatedTask);
    }
}