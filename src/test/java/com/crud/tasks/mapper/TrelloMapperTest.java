package com.crud.tasks.mapper;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.dto.TrelloBoardDto;
import com.crud.tasks.dto.TrelloListDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTest {
    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void testMapToBoards() {
//        //Given
//        List<TrelloBoardDto> boardsDto = new ArrayList<>();
//        List<TrelloListDto> listsDto1 = new ArrayList<>();
//        List<TrelloListDto> listsDto2 = new ArrayList<>();
//        TrelloListDto trelloListDto1 = new TrelloListDto("id", "name", false);
//        TrelloListDto trelloListDto2 = new TrelloListDto("id", "name", true);
//        listsDto1.add(trelloListDto1);
//        listsDto2.add(trelloListDto2);
//        TrelloBoardDto boardDto1 = new TrelloBoardDto("name", "id", listsDto1);
//        TrelloBoardDto boardDto2 = new TrelloBoardDto("name", "id", listsDto2);
//        boardsDto.add(boardDto1);
//        boardsDto.add(boardDto2);
//        //When
//        List<TrelloBoard> boards = trelloMapper.mapToBoards(boardsDto);
//        //Then
//        assertEquals(2, boards.size());
    }
}
