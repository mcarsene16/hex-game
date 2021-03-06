package com.supinfo.ait.hexBoards;

public interface Board {

    public static final int BLANK = 0;
    public static final int GREEN = 1;
    public static final int YELLOW = 2;
    public static final int MAX_SUPPORTED_BOARD_SIZE = 99;
    public static final int MIN_SUPPORTED_BOARD_SIZE = 1;
    public static final int DEFAULT_BOARD_SIZE = 7;

    public int getSize();

    public int get(int x, int y);

    public String getName();

    public void setName(String name);

    public boolean hasChanged();

    public void changeNoted();
}
