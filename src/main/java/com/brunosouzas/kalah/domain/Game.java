package com.brunosouzas.kalah.domain;

import java.util.ArrayList;
import java.util.List;

import com.brunosouzas.kalah.domain.exception.KalahException;

import lombok.Data;

@Data
public class Game {

    public Game(Integer id, Integer stones, Player currentPlayer) {
        this.id = id;
        this.isEndGame = false;
        this.currentPlayer = currentPlayer;
        initialize(stones);
    }

    private Integer id;
    private List<Pit> pits;
    private Player currentPlayer;
    private Boolean isEndGame;

    public int capture(int pitId){
        return getPit(pitId).capture();
    }

    public void seed(int pitId){
        getPit(pitId).addStone();
    }

    public void seedCollected(int pitId, int stones){
        getPit(pitId).addStone(stones);
    }

    public int getPitStones(int pitId){
        return getPit(pitId).getStones();
    }

    public void endGame(){
        this.isEndGame = getPits().stream()
                .filter(pit -> getCurrentPlayer().getAllowedPits().stream()
                .anyMatch(i -> i == pit.getId() && i != getCurrentPlayer().getHouseId()))
                .allMatch(pit -> pit.getStones() == 0);
    }

    public int getCurrentPlayerPitIndex(int pitId){
        return getCurrentPlayer().getAllowedPits().indexOf(pitId);
    }

    private Pit getPit(int pitId){
        return pits.stream()
                .filter(pit -> pit.getId() == pitId)
                .findFirst()
                .orElseThrow(() -> new KalahException("Pit not founded"));
    }

    private void initialize(int stones) {
        pits = new ArrayList<>();

        for (int index = 1; index<= Board.NUMBER_OF_PITS; index++){
            pits.add(new Pit(index, stones));
        }
    }
}
