package com.brunosouzas.kalah.repository;

import com.brunosouzas.kalah.domain.Board;

public interface GameRepository {
	
    Board getBoard(int gameId);

    void updateBoard(int gameId, Board board);

    Board newGame();
}
