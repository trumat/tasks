package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Trello {
    @JsonProperty("board")
    private String board;
    @JsonProperty("card")
    private String card;
}
