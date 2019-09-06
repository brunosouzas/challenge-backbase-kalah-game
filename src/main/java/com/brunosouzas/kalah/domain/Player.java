package com.brunosouzas.kalah.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Player {

    private List<Integer> allowedPits;
    private Integer houseId;

}
