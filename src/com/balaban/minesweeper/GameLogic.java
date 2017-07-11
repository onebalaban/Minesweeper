package com.balaban.minesweeper;

/**
 * Created by Roman Balaban on 11.07.2017.
 */
public class GameLogic {

    private BoardCell[][] board;

    public GameLogic(BoardCell[][] board){
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
    private int numOfCellsToOpen = Launcher.rows*Launcher.columns - Launcher.numOfMines;

    //input target cell
    private int targetRow;
    private int targetColumn;
    private boolean targetFlag;

    public void play(){
        targetRow = getTargetRow();
        targetColumn = getTargetColumn();
        targetFlag = getTargetFlag();
        if (!targetFlag) {
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
        else {if (!board[targetRow][targetColumn].isFlagged()) {
            board[targetRow][targetColumn].setFlagged(true);
        }
        else {
            board[targetRow][targetColumn].setFlagged(false);
        }
        }

        winnerCheck();

        if (isWinner){
            System.out.println("You win!");
        }

    }

    // here we can implement the method to get user defined cell - pass the value from CLI, mouse or even voice recognition
    public int getTargetRow(){
        return targetRow;
    }

    public int getTargetColumn(){
        return targetColumn;
    }

    public boolean getTargetFlag(){
        return false;
    }

    //check around cells, if cell is closed and not flagged, then open cell and again check around cells
    public void openAroundCells(int targetRow, int targetColumn){
        for (int k = targetRow-1; k < targetRow+2; k++) {
            for (int l = targetColumn-1; l < targetColumn+2; l++) {
                try{
                    if (!board[k][l].isOpened() && !board[k][l].isFlagged()) {
                        board[k][l].setOpened(true);
                        numOfCellsToOpen--;
                        if (board[k][l].getNumOfMinesAround() == 0){
                            openAroundCells(k,l);
                        }
                    }

                } catch (Exception e){
                    continue;
                }
            }

        }
    }

    //check winner conditions
    public void winnerCheck(){
        if (numOfCellsToOpen == 0){
            isWinner = true;
        }
    }



}
