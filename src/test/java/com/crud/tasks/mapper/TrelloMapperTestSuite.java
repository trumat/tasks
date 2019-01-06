package com.crud.tasks.mapper;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.dto.TrelloBoardDto;
import com.crud.tasks.dto.TrelloCardDto;
import com.crud.tasks.dto.TrelloListDto;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TrelloMapperTestSuite {
    private TrelloMapper trelloMapper = new TrelloMapper();

    @Test
    public void testMapToBoards() {
        //Given
        List<TrelloBoardDto> boardsDto = new ArrayList<>();
        List<TrelloListDto> listsDto1 = new ArrayList<>();
        List<TrelloListDto> listsDto2 = new ArrayList<>();
        TrelloListDto trelloListDto1 = new TrelloListDto("id", "name", false);
        TrelloListDto trelloListDto2 = new TrelloListDto("id", "name", true);
        listsDto1.add(trelloListDto1);
        listsDto2.add(trelloListDto2);
        TrelloBoardDto boardDto1 = new TrelloBoardDto("name", "id", listsDto1);
        TrelloBoardDto boardDto2 = new TrelloBoardDto("name", "id", listsDto2);
        boardsDto.add(boardDto1);
        boardsDto.add(boardDto2);
        //When
        List<TrelloBoard> boards = trelloMapper.mapToBoards(boardsDto);
        //Then
        assertEquals(2, boards.size());
    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        List<TrelloBoard> boards = new ArrayList<>();
        List<TrelloList> lists1 = new ArrayList<>();
        List<TrelloList> lists2 = new ArrayList<>();
        TrelloList trelloList1 = new TrelloList("id", "name", false);
        TrelloList trelloList2 = new TrelloList("id", "name", true);
        lists1.add(trelloList1);
        lists2.add(trelloList2);
        TrelloBoard board1 = new TrelloBoard("name", "id", lists1);
        TrelloBoard board2 = new TrelloBoard("name", "id", lists2);
        boards.add(board1);
        boards.add(board2);
        //When
        List<TrelloBoardDto> boardsDto = trelloMapper.mapToBoardsDto(boards);
        //Then
        assertEquals(2, boardsDto.size());
    }

    @Test
    public void testMapToList() {
        //Given
        List<TrelloListDto> listsDto = new ArrayList<>();
        TrelloListDto trelloListDto1 = new TrelloListDto("id1", "name1", true);
        TrelloListDto trelloListDto2 = new TrelloListDto("id2", "name2", false);
        listsDto.add(trelloListDto1);
        listsDto.add(trelloListDto2);
        //When
        List<TrelloList> lists = trelloMapper.mapToList(listsDto);
        //Then
        assertEquals(2, lists.size());
    }

    @Test
    public void testMapToListDto() {
        //Given
        List<TrelloList> lists = new ArrayList<>();
        TrelloList trelloList1 = new TrelloList("id1", "name1", true);
        TrelloList trelloList2 = new TrelloList("id2", "name2", false);
        lists.add(trelloList1);
        lists.add(trelloList2);
        //When
        List<TrelloListDto> listsDto = trelloMapper.mapToListDto(lists);
        //Then
        assertEquals(2, listsDto.size());
    }

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name", "description", "top", "2");
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertEquals("name", trelloCard.getName());
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("name", "description", "top", "2");
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals("name", trelloCardDto.getName());
    }
}
