package game;

import java.util.Random;

public class Game {

    private char[][] board;
    private String [] players;
    private char currentPlayer;

    public Game(String player1, String player2) {
        board = new char[3][3];
        players = new String [2];
        players[0] = player1;
        players[1] = player2;
        
        Random r = new Random();
        if (r.nextInt(2) == 0) {
        	System.out.println(players[0] + " começa!");
        	currentPlayer = 'x';
        }
        else {
        	System.out.println(players[1] + " começa!");
        	currentPlayer = 'o';
        }
        clearBoard();
    }

    public String getPlayers (int i) {
    	return this.players[i];
    }
    
    public String getCurrentPlayer(){
        if (this.getCurrentPlayerMark() == 'x') {
        	return this.getPlayers(0) + " (x)";
        }
        else{
        	return this.getPlayers(1) + " (o)";
        }
    }
    
    public char getCurrentPlayerMark()
    {
        return currentPlayer;
    }

    public void clearBoard() {
    	for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public void printBoard() {
        int i, j;

        for (i=0; i < 3; i++) {
            for (j = 0; j < 2; j++) {
                if (board[i][j] == ' ' && i<2)
                	System.out.print("_|");
                else
                	System.out.print(board[i][j] + "|");
            }
            if (board[i][j] == ' ' && i<2)
            	System.out.print("_");
            else
            	System.out.print(board[i][j]);
            System.out.println();
        }
    }

    public boolean isBoardFull() {
        boolean isFull = true;

        for (int i = 0; i<3; i++) {
            for (int j = 0; j<3; j++) {
                if (board[i][j] == ' ') {
                    isFull = false;
                }
            }
        }
        return isFull;
    }

    public boolean checkForWin() {
        return (checkRowsForWin() || 
        		checkColumnsForWin() || 
        		checkDiagonalsForWin());
    }

    private boolean checkRowsForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[i][0], board[i][1], board[i][2]) == true) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumnsForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[0][i], board[1][i], board[2][i]) == true) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonalsForWin() {
        return ((checkRowCol(board[0][0], board[1][1], board[2][2]) == true) || (checkRowCol(board[0][2], board[1][1], board[2][0]) == true));
    }

    private boolean checkRowCol(char c1, char c2, char c3) {
        return ((c1 != ' ') && (c1 == c2) && (c2 == c3));
    }

    public void changePlayer() {
        if (currentPlayer == 'x') {
            currentPlayer = 'o';
        }
        else {
            currentPlayer = 'x';
        }
    }

    public boolean placeMark(int row, int col) {
        if ((row >= 0) && (row < 3)) {
            if ((col >= 0) && (col < 3)) {
                if (board[row][col] == ' ') {
                    board[row][col] = currentPlayer;
                    return true;
                }
            }
        }
        return false;
    }
}