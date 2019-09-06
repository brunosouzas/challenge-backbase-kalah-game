package com.brunosouzas.kalah.controller;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.brunosouzas.kalah.domain.dto.NewGameDto;
import com.brunosouzas.kalah.domain.dto.MoveGameDto;
import com.brunosouzas.kalah.service.GameService;

@RunWith(MockitoJUnitRunner.class)
public class GameControllerTest {


    @Mock private GameService gameService;
    @InjectMocks public GameController controller;


    @Test
    public void create(){
        NewGameDto dto = new NewGameDto("123", "http://localhost:8080/games/1");
        Mockito.when(gameService.createGame()).thenReturn(dto);

        ResponseEntity<?> response = controller.createGame();

        assertEquals("HttpStatus should be 201", 201, response.getStatusCodeValue());
        assertEquals("Body was not as expected", dto, response.getBody());
    }

    @Test
    public void move(){
        Map<Integer, String> status = new HashMap<>();
        status.put(1,"1");
        MoveGameDto dto = new MoveGameDto("123", "http://localhost:8080/games/1", status);
        Mockito.when(gameService.moveGame(25, 1)).thenReturn(dto);

        ResponseEntity<?> response = controller.move(25,1);

        assertEquals("HttpStatus should be 200", 200, response.getStatusCodeValue());
        assertEquals("Body was not as expected", dto, response.getBody());
    }

}