package com.brunosouzas.kalah.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class GameTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    Player playerOne = new Player(Board.PLAYER_1_PITS, Board.PLAYER_1_END_HOUSE);

    @Test
    public void capture(){
        Game game = new Game(1, 4, playerOne);
        int pitId = 2;
        int stones = game.capture(pitId);

        assertEquals("Stones captured should be 4", 4, stones);
        game.getPits().stream()
            .filter(pit -> pit.getId() != pitId && pit.getId() != Board.PLAYER_1_END_HOUSE
                    && pit.getId() != Board.PLAYER_2_END_HOUSE)
            .forEach(
                pit -> assertEquals("Stones remaining should be 4", 4, pit.getStones().intValue())
        );
    }

    @Test
    public void seed(){
        Game game = new Game(1, 4, playerOne);
        int pitId = 2;
        game.seed(pitId);
        assertEquals("Stones in seed pit should be 5", 5,
                game.getPits().stream().filter(pit -> pit.getId() == pitId).findFirst().get().getStones().intValue());

        game.getPits().stream()
                .filter(pit -> pit.getId() != pitId && pit.getId() != Board.PLAYER_1_END_HOUSE
                        && pit.getId() != Board.PLAYER_2_END_HOUSE)
                .forEach(
                        pit -> assertEquals("Stones remaining should be 4", 4, pit.getStones().intValue())
                );
    }

    @Test
    public void seedCollected(){
        Game game = new Game(1, 4, playerOne);
        int pitId = 2;
        game.seedCollected(pitId, 4);
        assertEquals("Stones in seed pit should be 8", 8,
                game.getPits().stream().filter(pit -> pit.getId() == pitId).findFirst().get().getStones().intValue());

        game.getPits().stream()
                .filter(pit -> pit.getId() != pitId && pit.getId() != Board.PLAYER_1_END_HOUSE
                        && pit.getId() != Board.PLAYER_2_END_HOUSE)
                .forEach(
                        pit -> assertEquals("Stones remaining should be 4", 4, pit.getStones().intValue())
                );
    }

    @Test
    public void getPitStones() {
        Game game = new Game(1, 4, playerOne);
        assertEquals("Stones captured should be 4", 4, game.getPitStones(1));
    }

    @Test
    public void getCurrentPlayerPitIndex(){
        Game game = new Game(1, 4, playerOne);
        assertEquals("Invalid pit index", 1, game.getCurrentPlayerPitIndex(2));
        assertEquals("Invalid pit index", -1, game.getCurrentPlayerPitIndex(8));
    }
}