import java.util.ArrayList;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class Player implements Runnable{

	private int playerID;
	private String name;
	private int score;
	private Snake snake;
	private ConcurrentLinkedQueue<Snake.Direction> moves;
	
	
	public Player(int id, String name) {
		this.playerID = id;
		this.name = name;
		this.score = 0;
		this.setSnake(new Snake());
		moves = new ConcurrentLinkedQueue<Snake.Direction>();
	}
	
	public void addMove(Snake.Direction direction) {
		this.moves.add(direction);
	}
	
	public void makeMove(Snake.Direction direction) {
		if (direction == Snake.Direction.UP) {
			snake.shiftBody(Snake.Direction.UP);
		} else if (direction == Snake.Direction.DOWN) {
			snake.shiftBody(Snake.Direction.DOWN);
		} else if (direction == Snake.Direction.LEFT) {
			snake.shiftBody(Snake.Direction.LEFT);
		} else {
			snake.shiftBody(Snake.Direction.RIGHT);
		}
	}
	
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

	public ConcurrentLinkedQueue<Snake.Direction> getMoves() {
		return moves;
	}

	public void setMoves(ConcurrentLinkedQueue<Snake.Direction> moves) {
		this.moves = moves;
	}


	
}
