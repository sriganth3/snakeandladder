package snakeandladder.model;

public class Player {

	private String name;
	
	private int position;
	
	private boolean won;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public boolean isWon() {
		return won;
	}

	public void setWon(boolean won) {
		this.won = won;
	}

	public Player(String name) {
		super();
		this.name = name;
		this.won = false;
		this.position = 0;
	}
	
	
}
