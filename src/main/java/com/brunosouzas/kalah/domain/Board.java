package com.brunosouzas.kalah.domain;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.brunosouzas.kalah.domain.exception.KalahException;

import lombok.Getter;

@Getter
public class Board {

    public static Integer NUMBER_OF_STONES = 6;
    public static Integer NUMBER_OF_PITS = 14;
    public static Integer PLAYER_1_END_HOUSE = 7;
    public static Integer PLAYER_2_END_HOUSE = 14;
    public static List<Integer> PLAYER_1_PITS = Arrays.asList(1, 2, 3, 4, 5, 6, PLAYER_1_END_HOUSE);
    public static List<Integer> PLAYER_2_PITS = Arrays.asList(8, 9, 10, 11, 12, 13, PLAYER_2_END_HOUSE);
	
    private Game game;
    private Player playerOne;
    private Player playerTwo;
    
    public Board(int id) {
        this.playerOne = new Player(PLAYER_1_PITS, PLAYER_1_END_HOUSE);
        this.playerTwo = new Player(PLAYER_2_PITS, PLAYER_2_END_HOUSE);
        this.game = new Game(id, NUMBER_OF_STONES, this.playerOne);
    }

    public void move(int pitId) {

    	this.playerCanPlay(pitId);

        int stones = game.capture(pitId);

        for (int i = 0; i < stones; i++) {
            pitId = getNextPit(pitId);
            game.seed(pitId);
        }

        this.collectSeed(pitId);
        this.nextRound(pitId);
        game.endGame();
    }

    private void playerCanPlay(final int pitId) {
        if (game.getCurrentPlayer().getAllowedPits().stream().noneMatch(i -> i == pitId))
            throw new KalahException("The PIT selected is not valid for this player.");

        if (game.getCurrentPlayer().getHouseId() == pitId)
            throw new KalahException("The PIT is the End House.");

        if (game.getIsEndGame())
            throw new KalahException("This game has ended.");
    }

    private int getNextPit(int pitId) {
        if (pitId == game.getCurrentPlayer().getHouseId())
            return game.getCurrentPlayer().getAllowedPits().get(0);
        else
            return ++pitId;
    }

    private void collectSeed(int pitId) {
        if (pitId == game.getCurrentPlayer().getHouseId())
            return;

        if (game.getPitStones(pitId) > 1 || game.getPitStones(pitId) == 0)
            return;

        int opponentPitId = getOpponentPitId(pitId);

        if (game.getPitStones(opponentPitId) < 1)
            return;

        game.seedCollected(game.getCurrentPlayer().getHouseId(), (game.capture(opponentPitId) + game.capture(pitId)));
    }

    private void nextRound(int pitId) {
        if (pitId != game.getCurrentPlayer().getHouseId())
            game.setCurrentPlayer(getOpponent());
    }

    private Player getOpponent() {
        if (game.getCurrentPlayer() == playerOne)
            return playerTwo;
        else
            return playerOne;
    }

    private int getOpponentPitId(int pitId) {
        int pitIndex = getPitIndex(pitId);

        return getOpponent().getAllowedPits().stream()
                .filter(pit -> !pit.equals(getOpponent().getHouseId()))
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList()).get(pitIndex);
    }

    private int getPitIndex(int pitId) {
        int index = game.getCurrentPlayerPitIndex(pitId);
        if (index == -1)
            throw new KalahException("No founded pit index");
        return index;
    }
}
