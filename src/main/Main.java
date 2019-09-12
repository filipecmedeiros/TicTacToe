package main;

import java.io.File;
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
	        		newGame(scan, player1, player2);
	        		break;
	        	case 2:
	        		loadGame(scan);
	        		break;
	        	case 3:
	        		break;
        	}
        }while (menu>0 && menu<3);
        
        scan.close();
    }
    
    public static void startGame(Scanner scan, Game game) {
    	boolean save = false;
    	
    	while(!game.checkForWin() && !game.isBoardFull() && !save){
            System.out.println("TELA DO JOGO - 02 JOGADORES");
            game.printBoard();
            int row;
            int col;
            do{
                System.out.println(game.getCurrentPlayer() + ", informe sua jogada (linha e coluna)");
                System.out.print("Informe a linha:");
                row = scan.nextInt()-1;
                if (row == 9) {
                	System.out.println("Informe o nome do arquivo que deseja salvar:");
                	String filename = scan.next();
                	game.save("./" + filename + ".jv");
                	save = true;
                	break;
                }
                System.out.print("Informe a coluna:");
                col = scan.nextInt()-1;
                System.out.println();
            }while (!game.placeMark(row, col));
            game.changePlayer();
        }
        
        if (game.isBoardFull() && !game.checkForWin()){
            System.out.println("VELHA!");
        }
        else{
            game.printBoard();
            game.changePlayer();
            System.out.println("Jogador(a) " + game.getCurrentPlayer() + " venceu!");
        }
    }
    
    public static void newGame(Scanner scan, String player1, String player2) {
    	Game game = new Game(player1, player2);
        game.clearBoard();
        
        startGame(scan, game);
    }
    
    public static void loadGame(Scanner scan) {
    	String filename = null;
    	boolean found = false;
    	File dir = new File(".");
		File [] files = dir.listFiles();
		do{
			for (int i=0; i<files.length; i++) { 
				if(files[i].toString().endsWith(".jv")) {
					System.out.println(files[i]);
				}
			}
	    	System.out.println("Informe o nome do arquivo para carregar:");
	    	filename = scan.next();
	    	
	    	for (int i=0; i<files.length; i++) {
	    		if (files[i].toString().equals(filename))
	    			found = true;
	    	}
	    	
    	}while(!found);
		
		Game game = Game.decode(filename);
    	startGame(scan, game);
    }
}