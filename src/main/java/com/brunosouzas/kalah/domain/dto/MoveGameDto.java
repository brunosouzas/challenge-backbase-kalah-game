package com.brunosouzas.kalah.domain.dto;

import java.util.Map;

import lombok.Getter;

@Getter
public class MoveGameDto extends NewGameDto {

    public MoveGameDto(String id, String url, Map<Integer, String> status) {
        super(id, url);
        this.status = status;
    }

    private Map<Integer, String> status;

}
