import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class Player implements Runnable{

	private int playerID;
	private String name;
	private int score;
	private Snake snake;
	private ConcurrentLinkedDeque<Snake.Direction> moves;
	
	
	public Player(int id, String name) {
		this.playerID = id;
		this.name = name;
		this.score = 0;
		this.setSnake(new Snake());
		moves = new ConcurrentLinkedDeque<Snake.Direction>();
	}
	
	public void addMove(Snake.Direction direction) {
		moves.add(direction);
	}
	
	public void makeMove(Snake.Direction direction) { 
		
		int tempX = 0;
		int tempY = 0;
		
		for (int x = 0; x < 100; x++) {
			for (int y = 0; y < 100; y++) {
				//System.out.println("x " + x + "/ y " + y);
				Cell cell = Game.board.getCell(x, y);
				//System.out.println("value of cell " + cell.getValue());
				//System.out.println("playerID " + playerID);
				if (cell.getValue() == playerID) {
					if (direction == Snake.Direction.UP) {
						if (cell.isSnakeHead()) {
							snake.addBodyPart(x, y-1, playerID, true);
							tempX = x;
							tempY = y;
							snake.removeBodyPart(cell);
						} else {
							snake.addBodyPart(tempX, tempY, playerID, false);
							tempX = x;
							tempY = y;
							snake.removeBodyPart(cell);
						}
						snake.setCurrentDirection(Snake.Direction.UP);
					} else if (direction == Snake.Direction.DOWN) {
							//snake.moveBodyPart(x, y+1, playerID, true);
					} else if (direction == Snake.Direction.LEFT) {
							//snake.moveBodyPart(x-1, y, playerID, true);
					} else if (direction == Snake.Direction.RIGHT) {
							//snake.moveBodyPart(x+1, y, playerID, true);
					}
				} 
			}
		}
		
		// remove old body
		//snake.removeBody(playerID);
		
		
	}
	
	public void shiftSnakeBody(Snake.Direction direction) { // could prob put this code into makemove
		
		
		
		
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

	public ConcurrentLinkedDeque<Snake.Direction> getMoves() {
		return moves;
	}

	public void setMoves(ConcurrentLinkedDeque<Snake.Direction> moves) {
		this.moves = moves;
	}


	
}
