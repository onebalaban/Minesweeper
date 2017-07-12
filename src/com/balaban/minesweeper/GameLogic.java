package com.balaban.minesweeper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Roman Balaban on 11.07.2017.
 */
public class GameLogic implements UiDrawer, UserInput {

    private BoardCell[][] board;

    public GameLogic(BoardCell[][] board) {
        this.board = board;
    }

    //fail state
    private boolean failState = false;

    public boolean getFailState() {
        return failState;
    }

    //winner state
    private boolean isWinner = false;

    //number of cells to open to win
    private int numOfCellsToOpen = Launcher.rows * Launcher.columns - Launcher.numOfMines;

    //input target cell
    private int targetRow;
    private int targetColumn;
    private int targetFlag;

    public void play() {
        while (!failState) {
            drawBoard();
            ArrayList<Integer> list = getTargetCell();
            targetRow = list.get(0);
            targetColumn = list.get(1);
            targetFlag = list.get(2);
            if (targetFlag == 0) {
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
            else {
                if (!board[targetRow][targetColumn].isFlagged()) {
                    board[targetRow][targetColumn].setFlagged(true);
                } else {
                    board[targetRow][targetColumn].setFlagged(false);
                }
            }

            winnerCheck();

            if (isWinner) {
                winAlert();
                return;
            }
        }
        loseAlert();
    }

    //check around cells, if cell is closed and not flagged, then open cell and again check around cells
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
                    continue;
                }
            }

        }
    }

    //check winner conditions
    public void winnerCheck() {
        if (numOfCellsToOpen == 0) {
            isWinner = true;
        }
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

    @Override
    public ArrayList<Integer> getTargetCell() {
        ArrayList<Integer> list = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        boolean rowCheck = false;
        do {
            System.out.println("Enter row number:");
            try {
                int tmp = Integer.parseInt(scanner.nextLine());
                if (tmp > 0 && tmp <= Launcher.rows){
                    list.add(tmp-1);//minus one because board array counts from 0
                    rowCheck = true;
                } else {
                    System.out.println("Wrong number");
                }
            } catch (NumberFormatException e){
                System.out.println("Entered not a number");
                continue;
            }
        } while (!rowCheck);

        boolean columnCheck = false;
        do {
            System.out.println("Enter column number:");
            try {
                int tmp = Integer.parseInt(scanner.nextLine());
                if (tmp > 0 && tmp <= Launcher.columns){
                    list.add(tmp-1);
                    columnCheck = true;
                } else {
                    System.out.println("Wrong number");
                }
            } catch (NumberFormatException e){
                System.out.println("Entered not a number");
                continue;
            }
        } while (!columnCheck);

        boolean flagCheck = false;
        do {
            System.out.println("If you want to set or unset the flag, enter 1, else enter 0:");
            try {
                int tmp = Integer.parseInt(scanner.nextLine());
                if (tmp >= 0 && tmp <= 1){
                    list.add(tmp);
                    flagCheck = true;
                } else {
                    System.out.println("Wrong number");
                }
            } catch (NumberFormatException e){
                System.out.println("Entered not a number");
                continue;
            }
        } while (!flagCheck);

        //System.out.println("Enter row number:");
        //list.add(Integer.parseInt(scanner.nextLine()) - 1); //minus one because board array counts from 0
        //System.out.println("Enter column number:");
        //list.add(Integer.parseInt(scanner.nextLine()) - 1);
        //System.out.println("If you want to set or unset the flag, enter 1, else enter 0:");
        //list.add(Integer.parseInt(scanner.nextLine()));

        return list;
    }
}
