package com.balaban.minesweeper;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The class describes game logic, rules, game flow etc
 */
public class GameLogic implements UserInput {

    private BoardCell[][] board;
    GameBoardDrawer gameBoardDrawer;

    public GameLogic(BoardCell[][] board) {
        this.board = board;
        gameBoardDrawer = new GameBoardDrawer(this.board);
    }

    //fail state
    private boolean failState = false;

    //winner state
    private boolean isWinner = false;

    //number of cells to open to win
    private int numOfCellsToOpen = Launcher.rows * Launcher.columns - Launcher.numOfMines;

    //input target cell
    private int targetRow;
    private int targetColumn;
    private int targetFlag;


    /**
     * start playing game
     */
    public void play() {
        while (!failState) {
            gameBoardDrawer.drawBoard();
            ArrayList<Integer> list = getTargetCell();
            targetRow = list.get(0);
            targetColumn = list.get(1);
            targetFlag = list.get(2);
            if (targetFlag == 0 && !board[targetRow][targetColumn].isFlagged()) {
                board[targetRow][targetColumn].setOpened(true);
                if (board[targetRow][targetColumn].isMined()) {
                    failState = true;
                } else if (board[targetRow][targetColumn].getNumOfMinesAround() == 0) {
                    numOfCellsToOpen--;
                    openAroundCells(targetRow, targetColumn);
                } else {
                    numOfCellsToOpen--;
                }
            }
            //working with flag
            else if (targetFlag == 1) {
                if (!board[targetRow][targetColumn].isFlagged()) {
                    board[targetRow][targetColumn].setFlagged(true);
                } else {
                    board[targetRow][targetColumn].setFlagged(false);
                }
            }

            winnerCheck();

            if (isWinner) {
                gameBoardDrawer.winAlert();
                return;
            }
        }
        gameBoardDrawer.loseAlert();
    }

    /**
     * Check around cells, if cell is closed and not flagged, then open cell and again check around cells
     *
     * @param targetRow    position x of the cell
     * @param targetColumn position y of the cell
     */
    public void openAroundCells(int targetRow, int targetColumn) {
        for (int k = targetRow - 1; k < targetRow + 2; k++) {
            for (int l = targetColumn - 1; l < targetColumn + 2; l++) {
                try {
                    if (!board[k][l].isOpened() && !board[k][l].isFlagged()) {
                        board[k][l].setOpened(true);
                        numOfCellsToOpen--;
                        if (board[k][l].getNumOfMinesAround() == 0) {
                            openAroundCells(k, l);
                        }
                    }

                } catch (Exception e) {
                }
            }

        }
    }


    /**
     * Check winner conditions
     */
    public void winnerCheck() {
        if (numOfCellsToOpen == 0) {
            isWinner = true;
        }
    }

    @Override
    public ArrayList<Integer> getTargetCell() {
        ArrayList<Integer> list = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        boolean rowCheck = false;
        do {
            System.out.println("Enter row number:");
            try {
                int tmp = Integer.parseInt(scanner.nextLine());
                if (tmp > 0 && tmp <= Launcher.rows) {
                    list.add(tmp - 1);//minus one because board array counts from 0
                    rowCheck = true;
                } else {
                    System.out.println("Wrong number");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entered not a number");
            }
        } while (!rowCheck);

        boolean columnCheck = false;
        do {
            System.out.println("Enter column number:");
            try {
                int tmp = Integer.parseInt(scanner.nextLine());
                if (tmp > 0 && tmp <= Launcher.columns) {
                    list.add(tmp - 1);
                    columnCheck = true;
                } else {
                    System.out.println("Wrong number");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entered not a number");
            }
        } while (!columnCheck);

        boolean flagCheck = false;
        do {
            System.out.println("If you want to set or unset the flag, enter 1, \n" +
                    "if you want to open the cell, enter 0:");
            try {
                int tmp = Integer.parseInt(scanner.nextLine());
                if (tmp >= 0 && tmp <= 1) {
                    list.add(tmp);
                    flagCheck = true;
                } else {
                    System.out.println("Wrong number");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entered not a number");
            }
        } while (!flagCheck);

        return list;
    }
}
