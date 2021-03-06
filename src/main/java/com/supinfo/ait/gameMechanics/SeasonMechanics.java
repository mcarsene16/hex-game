package com.supinfo.ait.gameMechanics;

import com.supinfo.ait.hexBoards.Board;
import com.supinfo.ait.utilis.HexGameConstantes;
import java.awt.Color;

public class SeasonMechanics {

    private static final int[] oneSeasonPattern = {0, 0};
    private static final int[] twoSeasonPattern = {0, 0, 1, 1};
    private static final int[] threeSeasonPattern = {0, 2, 1, 0, 2, 1};
    private static final Color thinGreen = new Color(128, 255, 128);
    private static final Color thinMagenta = new Color(255, 128, 255);
    private static final Color thinYellow = new Color(255, 255, 128);
    private static final Color[] oneSeasonColours = {Color.WHITE};
    private static final Color[] twoSeasonColours = {thinYellow, thinMagenta};
    private static final Color[] threeSeasonColours = {thinGreen, thinYellow, thinMagenta};
    public static final int MAX_SUPPORTED_SEASON_SIZE = 3;
    public static final int MIN_SUPPORTED_SEASON_SIZE = 1;
    public static final int DEFAULT_SEASON_SIZE = 1;
    private int[] seasonPattern;
    private Color[] seasonColours;
    private int seasonCount;
    private int greenPosition;
    private int yellowPosition;
    private int[] greenSeasonPattern;
    private int[] yellowSeasonPattern;
    private boolean greenThinking;
    private boolean yellowThinking;

    public SeasonMechanics(int numberOfSeasons) {

        this.seasonCount = numberOfSeasons;
        switch (seasonCount) {
            case 1:
                seasonPattern = oneSeasonPattern;
                seasonColours = oneSeasonColours;
                break;
            case 2:
                seasonPattern = twoSeasonPattern;
                seasonColours = twoSeasonColours;
                break;
            case 3:
                seasonPattern = threeSeasonPattern;
                seasonColours = threeSeasonColours;
                break;
            default:
                System.err.println("invalid season count");
                break;

        }

        greenSeasonPattern = getSubPattern(Board.GREEN);
        yellowSeasonPattern = getSubPattern(Board.YELLOW);

        greenPosition = 0;
        yellowPosition = 0;
    }

    public int getSeasonCount() {
        return seasonCount;
    }

    public int getAdvanceSeason(int player, int advance) {
        int pos = (getCurrentPos(player) + advance) % seasonCount;
        return getSubPattern(player)[pos];
    }

    public int getCurrentSeason(int player) {
        int season = 0;
        switch (player) {
            case Board.GREEN:
                season = greenSeasonPattern[greenPosition];
                break;
            case Board.YELLOW:
                season = yellowSeasonPattern[yellowPosition];
                break;
        }
        return season;
    }

    public int getCurrentPos(int player) {
        int pos = 0;
        switch (player) {
            case Board.GREEN:
                pos = greenPosition;
                break;
            case Board.YELLOW:
                pos = yellowPosition;
                break;
        }
        return pos;
    }

    public void increment(int player) {
        switch (player) {
            case Board.YELLOW:
                yellowPosition = (yellowPosition + 1) % yellowSeasonPattern.length;
                break;
            case Board.GREEN:
                greenPosition = (greenPosition + 1) % greenSeasonPattern.length;
                break;
        }
    }

    public boolean isThinking(int player) {
        switch (player) {
            case Board.YELLOW:
                return yellowThinking;
            case Board.GREEN:
                return greenThinking;
            default:
                System.err.println("player id " + player + " does not exist!");
                return false;
        }
    }

    public void thinkingPlayer(int player) {
        switch (player) {
            case Board.YELLOW:
                System.out.println(HexGameConstantes.YELLOW_PLAYER_NAME + " is thinking");
                yellowThinking = true;
                greenThinking = false;
                break;
            case Board.GREEN:
                System.out.println(HexGameConstantes.GREEN_PLAYER_NAME + " is thinking");
                yellowThinking = false;
                greenThinking = true;
                break;
            default:
                System.out.println("what!!!!");
                break;
        }
    }

    public Color[] getColourArray() {
        return seasonColours;
    }

    public int getNextSeason(int currentSeason, int player) {
        int returnSeason = 0;
        int[] tempSeasons = getSubPattern(player);
        for (int i = 0; i < tempSeasons.length; i++) {
            if (tempSeasons[i] == currentSeason) {
                int nextPos = (i + 1) % tempSeasons.length;
                returnSeason = tempSeasons[nextPos];
            }
        }
        return returnSeason;
    }

    public int[] getSubPattern(int player) {
        int[] sublist = new int[seasonPattern.length / 2];
        int startPos;
        if (player == Board.GREEN) {
            startPos = 0;
        } else {
            startPos = 1;
        }
        for (int i = 0; i < sublist.length; i++) {
            sublist[i] = seasonPattern[(i) * 2 + startPos];
        }
        return sublist;
    }
}
