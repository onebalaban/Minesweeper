package com.balaban.minesweeper;

/**
 * The class to draw user interface
 */
public class GameBoardDrawer implements UiDrawer {

    private BoardCell[][] board;

    public GameBoardDrawer(BoardCell[][] board) {
        this.board = board;
    }

    @Override
    public void drawBoard() {
        System.out.println("");
        System.out.println("");
        System.out.print("     ");
        // print column numbers
        for (int i = 0; i < Launcher.columns; i++) {
            System.out.print(" " + (i + 1) + " ");
        }
        System.out.println("");
        System.out.println("");
        //print board and row numbers
        for (int i = 0; i < Launcher.rows; i++) {
            //print row numbers
            if (i < 9) {
                System.out.print(" " + (i + 1) + "   ");
            } else {
                System.out.print(" " + (i + 1) + "  ");
            }
            //print board
            for (int j = 0; j < Launcher.columns; j++) {
                if (board[i][j].isFlagged()) {
                    System.out.print(" ⚐ ");
                } else if (!board[i][j].isOpened()) {
                    System.out.print(" □ ");
                } else if (board[i][j].isMined()) {
                    System.out.print(" ✹ ");
                } else {
                    System.out.print(" " + board[i][j].getNumOfMinesAround() + " ");
                }
            }
            System.out.println("");
        }
        System.out.println("");
        System.out.println("");

    }

    @Override
    public void drawFinalBoard() {
        System.out.println("");
        System.out.println("");
        System.out.print("     ");
        // print column numbers
        for (int i = 0; i < Launcher.columns; i++) {
            System.out.print(" " + (i + 1) + " ");
        }
        System.out.println("");
        System.out.println("");
        //print board and row numbers
        for (int i = 0; i < Launcher.rows; i++) {
            //print row numbers
            if (i < 9) {
                System.out.print(" " + (i + 1) + "   ");
            } else {
                System.out.print(" " + (i + 1) + "  ");
            }
            //print board
            for (int j = 0; j < Launcher.columns; j++) {
                if (board[i][j].isMined()) {
                    System.out.print(" ✹ ");
                } else {
                    System.out.print(" □ ");
                }
            }
            System.out.println("");
        }
        System.out.println("");
        System.out.println("");
    }

    @Override
    public void loseAlert() {
        System.out.println("");
        System.out.println("");
        System.out.println("Game over");
        drawFinalBoard();
    }

    @Override
    public void winAlert() {
        System.out.println("");
        System.out.println("");
        System.out.println("You win!");
        drawFinalBoard();
    }
}
