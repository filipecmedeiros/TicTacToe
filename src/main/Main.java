package main;

import java.util.Scanner;

import game.Board;

public class Main {
    public static void main(String[] args)
    {
        int menu;
    	Scanner scan = new Scanner(System.in);
        System.out.println("Jogo da velha");
        
        do {
        	System.out.println("1 - Novo jogo");
        	System.out.println("2 - Continuar jogo");
        	System.out.println("3 - Sair");
        	menu = scan.nextInt();
        	
        	switch(menu) {
	        	case 1: newGame();
	        		break;
	        	case 2:
	        		break;
	        	case 3:
	        		break;
        	}
        	
        }while (menu>0 && menu<4);
        
        scan.close();
    }
    
    public static void newGame() {
    	Scanner scan = new Scanner(System.in);
    	Board game = new Board();
        game.initializeBoard();
        
        do{
            System.out.println("TELA DO JOGO - 02 JOGADORES");
            game.printBoard();
            int row;
            int col;
            do
            {
                System.out.println("Player " + game.getCurrentPlayerMark() + ", enter an empty row and column to place your mark!");
                row = scan.nextInt()-1;
                col = scan.nextInt()-1;
            }
            while (!game.placeMark(row, col));
            game.changePlayer();
        }
        while(!game.checkForWin() && !game.isBoardFull());
        if (game.isBoardFull() && !game.checkForWin())
        {
            System.out.println("The game was a tie!");
        }
        else
        {
            System.out.println("Current board layout:");
            game.printBoard();
            game.changePlayer();
            System.out.println(Character.toUpperCase(game.getCurrentPlayerMark()) + " Wins!");
        }
        scan.close();
    }
}