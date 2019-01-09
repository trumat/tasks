package com.crud.tasks.controller;

import com.crud.tasks.dto.CreatedTrelloCardDto;
import com.crud.tasks.dto.TrelloBoardDto;
import com.crud.tasks.dto.TrelloCardDto;
import com.crud.tasks.dto.TrelloListDto;
import com.crud.tasks.trello.facade.TrelloFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TrelloControllerTestSuite {
    @InjectMocks
    private TrelloController trelloController;
    @Mock
    private TrelloFacade trelloFacade;

    @Test
    public void testGetTrelloBoards() {
        //Given
        List<TrelloBoardDto> boards = new ArrayList<>();
        List<TrelloListDto> lists1 = new ArrayList<>();
        List<TrelloListDto> lists2 = new ArrayList<>();
        TrelloListDto trelloListDto1 = new TrelloListDto("id1", "name1", true);
        TrelloListDto trelloListDto2 = new TrelloListDto("id2", "name2", false);
        lists1.add(trelloListDto1);
        lists2.add(trelloListDto2);
        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("name1", "id1", lists1);
        TrelloBoardDto trelloBoardDto2 = new TrelloBoardDto("name2", "id2", lists2);
        boards.add(trelloBoardDto1);
        boards.add(trelloBoardDto2);
        when(trelloFacade.fetchTrelloBoards()).thenReturn(boards);

        //When
        List<TrelloBoardDto> fetchedBoards = trelloController.getTrelloBoards();

        //Then
        assertEquals(boards, fetchedBoards);
    }

    @Test
    public void testCreateTrelloCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name", "description", "pos", "1");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("id", "name", "url");
        when(trelloFacade.createCard(trelloCardDto)).thenReturn(createdTrelloCardDto);

        //When
        CreatedTrelloCardDto returnedCard = trelloController.createTrelloCard(trelloCardDto);

        //Then
        assertEquals(createdTrelloCardDto, returnedCard);
    }
}