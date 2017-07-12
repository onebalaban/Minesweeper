package com.balaban.minesweeper;

/**
 * Created by Roman Balaban on 12.07.2017.
 */
public interface UiDrawer {

    public void drawBoard();

    public void drawFinalBoard();

    public void loseAlert();

    public void winAlert();
}
