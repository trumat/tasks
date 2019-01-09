package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import com.crud.tasks.dto.CreatedTrelloCardDto;
import com.crud.tasks.dto.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import com.crud.tasks.trello.config.AdminConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTestSuite {
    @InjectMocks
    private TrelloService trelloService;
    @Mock
    private AdminConfig adminConfig;
    @Mock
    private TrelloClient trelloClient;
    @Mock
    private SimpleEmailService emailService;

    @Test
    public void testCreateTrelloCardHappyPath() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name", "description", "top", "1");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("id", "name", "url");
        Mail mail = new Mail("test@test.com", "Tasks: New Trello card", "New card: name has been created on your account");
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdTrelloCardDto);
        when(adminConfig.getAdminMail()).thenReturn("test@test.com");

        //When
        CreatedTrelloCardDto returnedCard = trelloService.createTrelloCard(trelloCardDto);

        //Then
        assertEquals(createdTrelloCardDto, returnedCard);
        verify(emailService).send(mail);
    }

    @Test
    public void testCreateTrelloCardNullCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name", "description", "top", "1");
        Mail mail = new Mail("test@test.com", "Tasks: New Trello card", "New card: name has been created on your account");
        //when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdTrelloCardDto);
        when(adminConfig.getAdminMail()).thenReturn("test@test.com");

        //When
        CreatedTrelloCardDto returnedCard = trelloService.createTrelloCard(trelloCardDto);

        //Then
        assertEquals(null, returnedCard);
        verify(emailService, times(0)).send(mail);
    }
}