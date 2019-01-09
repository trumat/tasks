package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloList;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TrelloValidatorTestSuite {
    private TrelloValidator trelloValidator = new TrelloValidator();

    @Test
    public void validateCardTest() {
        //Given
        TrelloCard trelloCard = new TrelloCard("test_card", "description", "pos", "1");

        //When
        trelloValidator.validateCard(trelloCard);

        //Then
        //do nothing
    }

    @Test
    public void validateCardProper() {
        //Given
        TrelloCard trelloCard = new TrelloCard("name", "description", "pos", "1");

        //When
        trelloValidator.validateCard(trelloCard);

        //Then
        //do nothing
    }

    @Test
    public void shouldReturnOneBoard() {
        //Given
        List<TrelloList> lists1 = new ArrayList<>();
        TrelloList list1 = new TrelloList("id1", "name1", true);
        lists1.add(list1);

        List<TrelloList> lists2 = new ArrayList<>();
        TrelloList list2 = new TrelloList("id2", "name2", false);
        lists2.add(list2);

        TrelloBoard trelloBoard1 = new TrelloBoard("1", "test", lists1);
        TrelloBoard trelloBoard2 = new TrelloBoard("2", "board", lists2);
        List<TrelloBoard> boards = new ArrayList<>();
        boards.add(trelloBoard1);
        boards.add(trelloBoard2);

        //When
        List<TrelloBoard> filteredBoards = trelloValidator.validateTrelloBoards(boards);

        //Then
        assertEquals(1, filteredBoards.size());
    }
}