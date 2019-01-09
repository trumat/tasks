package com.crud.tasks.scheduler;

import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import com.crud.tasks.trello.config.AdminConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class EmailSchedulerTestSuite {
    @InjectMocks
    private EmailScheduler emailScheduler;
    @Mock
    private SimpleEmailService emailService;
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private AdminConfig adminConfig;

    @Test
    public void sendInformationEmailOneTask() {
        //Given
        Mail mail = new Mail("test@test.com", "Tasks: once a day email", "Currently in the database you have 1 task");
        when(adminConfig.getAdminMail()).thenReturn("test@test.com");
        when(taskRepository.count()).thenReturn(1L);

        //When
        emailScheduler.sendInformationEmail();

        //Then
        verify(emailService).send(mail);
    }

    @Test
    public void sendInformationEmailTwoTasks() {
        //Given
        Mail mail = new Mail("test@test.com", "Tasks: once a day email", "Currently in the database you have 2 tasks");
        when(adminConfig.getAdminMail()).thenReturn("test@test.com");
        when(taskRepository.count()).thenReturn(2L);

        //When
        emailScheduler.sendInformationEmail();

        //Then
        verify(emailService).send(mail);
    }
}