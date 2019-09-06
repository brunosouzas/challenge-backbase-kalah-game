package com.brunosouzas.kalah.service;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.brunosouzas.kalah.domain.Board;
import com.brunosouzas.kalah.domain.Game;
import com.brunosouzas.kalah.domain.Pit;
import com.brunosouzas.kalah.domain.dto.NewGameDto;
import com.brunosouzas.kalah.domain.dto.MoveGameDto;
import com.brunosouzas.kalah.repository.GameRepository;
import com.brunosouzas.kalah.utilities.KalahUtilities;

@Service
public class GameService {

    private final Environment environment;
    private final GameRepository repository;

    @Autowired
    public GameService(Environment environment, GameRepository repository) {
        this.environment = environment;
        this.repository = repository;
    }

    public NewGameDto createGame() {
        Game game = repository.newGame().getGame();
        String url = KalahUtilities.generateGameUrl(game.getId(), environment.getProperty("url"));
        
        return new NewGameDto(Integer.toString(game.getId()), url);
    }

    public MoveGameDto moveGame(@NonNull Integer gameId, @NonNull Integer pitId){
        Board board = repository.getBoard(gameId);

        board.move(pitId);

        repository.updateBoard(gameId, board);
        
        String url = KalahUtilities.generateGameUrl(board.getGame().getId(), environment.getProperty("url"));

        Map<Integer, String> status = board.getGame().getPits().stream()
                .collect(Collectors.toMap(Pit::getId, value -> Integer.toString(value.getStones())));

        return new MoveGameDto(Integer.toString(board.getGame().getId()), url, status);
    }

}
