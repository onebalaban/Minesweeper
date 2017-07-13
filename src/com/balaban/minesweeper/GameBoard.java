package com.balaban.minesweeper;

/**
 * The class initializes game board and bombs positions
 */
public class GameBoard {

    /**
     * initialize game board and bombs positions
     *
     * @param rows       number of rows at the board
     * @param columns    number of columns at the board
     * @param numOfMines number of mines at hte board
     */
    public void initialize(int rows, int columns, int numOfMines) {

        //make 2D array
        BoardCell[][] board = new BoardCell[rows][columns];

        //initialize the board
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = new BoardCell();
            }
        }


        //set up bombs
        for (int i = 0; i < numOfMines; i++) {
            int r = (int) (Math.random() * rows);
            int c = (int) (Math.random() * columns);
            if (board[r][c].isMined()) {
                i--;
            } else {
                board[r][c].setMined(true);
            }
        }

        //set up number of mines around each cell
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {

                //if cell is not mined, then check if there any mines in 3x3 square and count mines
                if (!board[i][j].isMined()) {
                    for (int k = i - 1; k < i + 2; k++) {
                        for (int l = j - 1; l < j + 2; l++) {
                            try {
                                if (board[k][l].isMined()) {
                                    board[i][j].setNumOfMinesAround(board[i][j].getNumOfMinesAround() + 1);
                                }
                            } catch (Exception e) {
                            }
                        }

                    }
                }


            }
        }


        //initialize game logic engine and pass the board
        GameLogic gameLogic = new GameLogic(board);
        gameLogic.play();

    }


}
