package com.balaban.minesweeper;

/**
 * The class contains variables needed to describe each cell on a gameboard. Cell states are opened/closed,
 * flagged-unflagged, mined/not mined. Also each cell has a quantity of cell with mines around.
 */
public class BoardCell {

    private boolean isOpened = false;
    private boolean isFlagged = false;
    private boolean isMined = false;
    private int numOfMinesAround = 0;

    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }

    public boolean isMined() {
        return isMined;
    }

    public void setMined(boolean mined) {
        isMined = mined;
    }

    public int getNumOfMinesAround() {
        return numOfMinesAround;
    }

    public void setNumOfMinesAround(int numOfMinesAround) {
        this.numOfMinesAround = numOfMinesAround;
    }
}
