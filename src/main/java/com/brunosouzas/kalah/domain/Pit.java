package com.brunosouzas.kalah.domain;

import com.brunosouzas.kalah.domain.exception.KalahException;

import lombok.Data;

@Data
public class Pit {

    public Pit(Integer id, Integer stones) {
        this.id = id;
        this.isEndHouse = isEndHouseId();
        this.stones = this.isEndHouse ? 0 : stones;
    }

    private Integer id;
    private Integer stones;
    private Boolean isEndHouse;

    public void addStone(){
        this.stones++;
    }

    public void addStone(int stones){
        this.stones += stones;
    }

    public int capture(){
        int stones = this.stones;
        
        if (stones == 0)
            throw new KalahException("This pit don't have stones.");
        
        this.stones = 0;
        
        return stones;
    }

    private boolean isEndHouseId(){
        return id == Board.PLAYER_1_END_HOUSE || id == Board.PLAYER_2_END_HOUSE;
    }

}
