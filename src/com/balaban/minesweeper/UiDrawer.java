package com.balaban.minesweeper;

/**
 * The interface UiDrawer is needed to define methods to draw user interface and alerts
 */
public interface UiDrawer {

    /**
     * Draw game board
     */
    void drawBoard();

    /**
     * Draw game board after game over or win
     */
    void drawFinalBoard();

    /**
     * Display alert when game is over
     */
    void loseAlert();

    /**
     * Display alert in case of win
     */
    void winAlert();

}
