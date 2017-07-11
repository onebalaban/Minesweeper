package com.balaban.minesweeper;

/**
 * Created by Roman Balaban on 10.07.2017.
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
