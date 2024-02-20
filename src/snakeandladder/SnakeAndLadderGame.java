package snakeandladder;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import snakeandladder.model.Player;

public class SnakeAndLadderGame {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter number of ladders");
		int numberOfLadders = scanner.nextInt(); 

		System.out.println("Enter number of Snakes");
		int numberOfSnakes = scanner.nextInt();
		
		System.out.print("Enter boardSize");
		int boardSize = scanner.nextInt();
		
		System.out.print("Enter players");
		int numberOfPlayers = scanner.nextInt();
		
		Game game = new Game(numberOfLadders, numberOfSnakes, boardSize );
		
		for(int i = 0; i < numberOfPlayers; i++) {
			System.out.print("Enter player number: "+ (i + 1));
			Player player = new Player(scanner.next());
			game.addPlayers(player);
			
		}
		scanner.close();
		
		game.play();
		
		
	}
}
