package com.brunosouzas.kalah.repository;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.brunosouzas.kalah.domain.Board;

public class GameRepositoryTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private GameRepositoryImpl database;

    @Before
    public void setUp(){
        database = new GameRepositoryImpl();
    }

    @Test
    public void getBoard() {
        database.newGame();
        database.newGame();

        Board board = database.getBoard(1);
        assertEquals("Game id should be 1.",1, board.getGame().getId().intValue());

        board = database.getBoard(2);
        assertEquals("Game id should be 2.",2, board.getGame().getId().intValue());
    }

    @Test
    public void updateBoard() {
        Board board1 = database.newGame();
        Board board2 = database.newGame();
        database.updateBoard(board1.getGame().getId(), board1);
        database.updateBoard(board2.getGame().getId(), board2);

        assertEquals("DatabaseFake boards size should be 2.", 2, GameRepositoryImpl.gameDB.size());
    }

    @Test
    public void newGame() {
        Board board = database.newGame();
        assertEquals("Game id should be 1.",1, board.getGame().getId().intValue());
    }
}