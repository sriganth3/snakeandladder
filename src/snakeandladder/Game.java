package snakeandladder;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import snakeandladder.model.Board;
import snakeandladder.model.Dice;
import snakeandladder.model.Ladder;
import snakeandladder.model.Pair;
import snakeandladder.model.Player;
import snakeandladder.model.Snake;

public class Game {

	private int numberOfLadder;
	private int numberOfSnakes;
	
	private Queue<Player> players;
	private List<Snake> snakes;
	private List<Ladder> ladders;
	
	private Board board;
	private Dice dice;
	
	public Game(int numberOfLadder, int numberOfSnakes, int boardSize) throws NoSuchAlgorithmException {
		
		this.numberOfLadder = numberOfLadder;
		this.numberOfSnakes = numberOfSnakes;
		this.board = new Board(boardSize);
		
        this.snakes = new ArrayList<>();
        this.ladders = new ArrayList<>();
        this.players = new LinkedList<>();
        this.dice = new Dice(1, 6);
        initBoard();
		
	}
	
	private void initBoard() throws NoSuchAlgorithmException {
		
		List<Integer> selSet = new ArrayList<>();
		
		for(int i = 0; i < numberOfLadder; i++) {
			Pair pair = getStartAndEnd(selSet, true);
			ladders.add(new Ladder(pair.getStart(), pair.getEnd()));
		}
		
		for(int i = 0; i < numberOfSnakes; i++) {
			Pair pair = getStartAndEnd(selSet, false);
			snakes.add(new Snake(pair.getStart(), pair.getEnd()));
		}
		
	}

	private Pair getStartAndEnd(List<Integer> selSet, boolean isLadder) throws NoSuchAlgorithmException {
		while(true) {
			int start = SecureRandom.getInstanceStrong().nextInt(board.getStart(), board.getEnd() - 1);
			int end = SecureRandom.getInstanceStrong().nextInt(board.getStart(), board.getEnd() - 1);
			if((isLadder && start >= end ) || ( !isLadder && start <= end ) ||  selSet.contains(start) || selSet.contains(end)) {
				continue;
			}
			selSet.add(start);
			selSet.add(end);
			return new Pair(start, end);
		}
	}
	
	private int checkNewPosition(int newPosition, Player player) {
		for(Snake snake: snakes) {
			if(snake.getHead() == newPosition) {

				System.out.println("Player: " + player.getName() +" got bit by a snake");
				return snake.getTail();
			}
		}
		
		for(Ladder ladder: ladders) {

			if(ladder.getStart() == newPosition) {

				System.out.println("Player: " + player.getName() +" Climbed the ladder");
				return ladder.getEnd();
			}
		}
		
		return newPosition;
	}
	
	public void addPlayers(Player player) {
		this.players.add(player);
	}
	
	public void play() throws NoSuchAlgorithmException {
		while(true) {
			
			if(players.size() < 2) {
				return;
			}
			
			Player currentPlayer = players.poll();
			int val = dice.roll();
			int newPosition = val + currentPlayer.getPosition();
			
			if(newPosition == board.getEnd()) {
				System.out.println("Player :"+ currentPlayer.getName() +" Won!");
				currentPlayer.setWon(true);
			}else if(newPosition > board.getEnd()) {
				players.offer(currentPlayer);
			}else {
				currentPlayer.setPosition(checkNewPosition(newPosition, currentPlayer));
				System.out.println("Player :"+ currentPlayer.getName() +" set into new position " +currentPlayer.getPosition());
				
				players.offer(currentPlayer);
			}
			
		}
	}
	
}
