package com.balaban.minesweeper;

/**
 * The class for game launch and setup game preferences
 */
public class Launcher {

    static int rows = 10;
    static int columns = 10;
    static int numOfMines = 10;

    public static void main(String[] args) {
        GameBoard gameBoard = new GameBoard();
        gameBoard.initialize(rows, columns, numOfMines);
    }
}
