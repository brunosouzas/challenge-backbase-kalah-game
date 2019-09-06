package com.brunosouzas.kalah.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BoardTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();


    @Test
    public void move() {
        Board board = new Board(8);

        board.move(4);

        for (int i : new int[]{1, 2, 3, 5, 6}) {
            assertEquals(String.format("Pit %s should have 7 stones", i),7, board.getGame().getPitStones(i));
        }

        assertEquals("Pit 4 should have 0 stones",0, board.getGame().getPitStones(4));
        assertEquals("Pit 7 should have 1 stones",1, board.getGame().getPitStones(7));
    }


    @Test
    public void collect() {
        Board board = new Board(10);
        for (int i : new int[]{1,2,3,4,5}) {
            board.getGame().getPits().get(i).addStone(board.getGame().getPits().get(i).getStones() * -1);
        }

        board.getGame().getPits().get(0).addStone(-5);

        board.move(1);

        assertEquals("Stones collected are invalid", 7, board.getGame().getPitStones(7));

        assertEquals("Stones remaining in player collected pit is invalid", 0, board.getGame().getPitStones(1));
        assertEquals("Stones remaining in player collected pit is invalid", 0, board.getGame().getPitStones(2));
        assertEquals("Stones remaining in opponent collected pit is invalid", 0, board.getGame().getPitStones(12));
    }
}