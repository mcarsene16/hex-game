package com.supinfo.ait.gameMechanics;

import com.supinfo.ait.players.Player;
import com.supinfo.ait.hexBoards.GameBoard;

public interface Runner {

    public static final int FAIR_TURN = 0;
    public static final int RANDOM_TURN = 1;

    public static final String[] GAME_LIST = {"Regular", "Random Turn"};
    public static final int[] GAME_CODES = {FAIR_TURN, RANDOM_TURN};

    public GameBoard getBoard();

    public Player getPlayerGreen();

    public Player getPlayerYellow();

    public void stopGame();

    public SeasonMechanics getSeasonPicker();

    public String getCommentary();
}
