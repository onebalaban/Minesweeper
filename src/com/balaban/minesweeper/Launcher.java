package com.balaban.minesweeper;

/**
 * Created by Roman Balaban on 10.07.2017.
 */
public class Launcher {

    static int rows = 10;
    static int columns = 10;
    static int numOfMines = 10;

    public static void main(String[] args) {
        GameBoard gameBoard = new GameBoard();
        gameBoard.play(rows,columns,numOfMines);
    }
}
