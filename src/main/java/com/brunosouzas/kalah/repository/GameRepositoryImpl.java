package com.brunosouzas.kalah.repository;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.brunosouzas.kalah.domain.Board;
import com.brunosouzas.kalah.domain.exception.KalahException;

@Service
public class GameRepositoryImpl implements GameRepository {

    public static Map<Integer, Board> gameDB = new HashMap<>();

    public GameRepositoryImpl() {
        gameDB.clear();
    }

    @Override
    public Board getBoard(int gameId){
        if(gameDB.containsKey(gameId))
            return gameDB.get(gameId);
        throw new KalahException(String.format("Game not founded.", gameId));
    }

    @Override
    public void updateBoard(int gameId, Board board){
        if(!gameDB.containsKey(gameId))
            throw new KalahException(String.format("Game not founded.", gameId));
        gameDB.put(gameId, board);
    }

    @Override
    public Board newGame(){
        int maxKey = gameDB.keySet().stream().max(Comparator.naturalOrder()).orElse(0) + 1;
        Board board = new Board(maxKey);
        gameDB.put(maxKey, board);
        return board;
    }
}
