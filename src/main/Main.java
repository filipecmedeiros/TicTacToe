package main;

import java.util.Scanner;

import game.Game;

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
	        	case 1: 
	        		System.out.println("Informe o nome do jogador 1:");
	        		String player1 = scan.next();
	        		System.out.println("Informe o nome do jogador 2:");
	        		String player2 = scan.next(); 
	        		newGame(player1, player2);
	        		menu = 3;
	        		break;
	        	case 2:
	        		break;
	        	case 3:
	        		break;
        	}
        	
        }while (menu>0 && menu<3);
        
        scan.close();
    }
    
    public static void newGame(String player1, String player2) {
    	Scanner scan = new Scanner(System.in);
    	Game game = new Game(player1, player2);
        game.clearBoard();
        
        while(!game.checkForWin() && !game.isBoardFull()){
            System.out.println("TELA DO JOGO - 02 JOGADORES");
            game.printBoard();
            int row;
            int col;
            do{
                System.out.println(game.getCurrentPlayer() + ", informe sua jogada (linha e coluna)");
                System.out.print("Informe a linha:");
                row = scan.nextInt()-1;
                System.out.print("Informe a coluna:");
                col = scan.nextInt()-1;
            }while (!game.placeMark(row, col));
            game.changePlayer();
        }
        
        if (game.isBoardFull() && !game.checkForWin()){
            System.out.println("VELHA!");
        }
        else{
            game.printBoard();
            game.changePlayer();
            System.out.println("O jogador " + game.getCurrentPlayer() + " venceu!");
        }
        scan.close();
    }
}