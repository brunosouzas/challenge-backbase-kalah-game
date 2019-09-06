package com.brunosouzas.kalah.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.brunosouzas.kalah.domain.dto.NewGameDto;
import com.brunosouzas.kalah.domain.dto.MoveGameDto;
import com.brunosouzas.kalah.domain.exception.Exception;
import com.brunosouzas.kalah.service.GameService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/games")
@Api(value="Kalak", description="How to play Kalah")
@ApiResponses(value = {@ApiResponse(code = 400, message = "An error occurred", response = Exception.class)})
public class GameController {

	@Autowired
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = Objects.requireNonNull(gameService, "GameService is a required dependency.");
    }

    @ApiOperation(value = "Create a new Kalah game", response = NewGameDto.class)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Successfully created game")})
    @ResponseStatus(value = HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createGame(){
        return ResponseEntity.status(201).body(this.gameService.createGame());
    }

    @ApiOperation(value = "Make a move in a kalah game in a pit", response = MoveGameDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully moved game")})
    @RequestMapping(value = "/{gameId}/pits/{pitId}", method = RequestMethod.PUT)
    public ResponseEntity<?> move(@PathVariable("gameId") Integer gameId, @PathVariable("pitId") Integer pitId){
        return ResponseEntity.ok(gameService.moveGame(gameId, pitId));
    }
}
