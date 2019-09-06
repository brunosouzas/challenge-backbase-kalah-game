package com.brunosouzas.kalah.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.env.Environment;

import com.brunosouzas.kalah.domain.Board;
import com.brunosouzas.kalah.domain.dto.NewGameDto;
import com.brunosouzas.kalah.domain.dto.MoveGameDto;
import com.brunosouzas.kalah.repository.GameRepository;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest {
	
	private static final String URL = "http://localhost:8080/games/1";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock private Environment environment;
    @Mock private GameRepository repository;

    @InjectMocks
    public GameService gameService;


    @Test
    public void create(){

        Board board = new Board(1);
        Mockito.when(repository.newGame()).thenReturn(board);
        
        Mockito.when(environment.getProperty("url")).thenReturn(URL);

        NewGameDto response = gameService.createGame();
        assertEquals("Id was not was expected", "1", response.getId());
        assertEquals("Uri was not was expected", "http://localhost:8080/games/1", response.getUrl());
    }


    @Test
    public void move(){
        int  gameId = 1;
        Board board = new Board(gameId);
        Mockito.when(repository.getBoard(gameId)).thenReturn(board);
        Mockito.doNothing().when(repository).updateBoard(anyInt(), any());
        
        Mockito.when(environment.getProperty("url")).thenReturn(URL);

        MoveGameDto response = gameService.moveGame(gameId, 1);

        assertEquals("Id was not was expected", "1", response.getId());
        assertEquals("Uri was not was expected", "http://localhost:8080/games/1", response.getUrl());
        assertEquals("Status at 1 should be 0","0", response.getStatus().get(1));

    }
}