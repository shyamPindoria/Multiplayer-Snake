import java.util.ArrayList;
import java.util.Stack;

public abstract class Player implements Runnable{

	private int playerID;
	private String name;
	private int score;
	private Snake snake;
	private Stack<Snake.Direction> moves;
	
	
	public Player(int id, String name) {
		this.playerID = id;
		this.name = name;
		this.score = 0;
		this.setSnake(new Snake());
		moves = new Stack<Snake.Direction>();
	}
	
	public abstract void makeMove();
	
	public String getName() {
		return this.name;
	}
	
	public int getScore() {
		return this.score;
	}

	public int getPlayerID() {
		return playerID;
	}

	public void scored(int score) {
		this.score += score;
	}
	
	@Override
	public boolean equals (Object o) {
		
		if (o.getClass().equals(Player.class) && ((Player)o).getPlayerID() == this.playerID) {
			return true;
		}
		return false;
		
	}
	
	@Override
	public String toString() {
		return this.name + ": " + this.score;
	}

	public Snake getSnake() {
		return snake;
	}

	public void setSnake(Snake snake) {
		this.snake = snake;
	}

	public Stack<Snake.Direction> getMoves() {
		return moves;
	}

	public void setMoves(Stack<Snake.Direction> moves) {
		this.moves = moves;
	}


	
}
