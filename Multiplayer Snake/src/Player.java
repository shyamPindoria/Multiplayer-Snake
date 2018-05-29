public abstract class Player implements Runnable{

	private int playerID;
	private String name;
	private int score;
	private Snake snake;
	
	
	public Player(int id, String name) {
		this.playerID = id;
		this.name = name;
		this.score = 0;
		this.setSnake(new Snake(id));
	}
	
	public void addMove(Snake.Direction direction) {
		
		if (this.snake.currentDirection == Snake.Direction.UP) {
			if (direction != Snake.Direction.DOWN) {
				this.snake.setCurrentDirection(direction);
			}
		} else if (this.snake.currentDirection == Snake.Direction.DOWN) {
			if (direction != Snake.Direction.UP) {
				this.snake.setCurrentDirection(direction);
			}
		} else if (this.snake.currentDirection == Snake.Direction.LEFT) {
			if (direction != Snake.Direction.RIGHT) {
				this.snake.setCurrentDirection(direction);
			}
		} else if (this.snake.currentDirection == Snake.Direction.RIGHT) {
			if (direction != Snake.Direction.LEFT) {
				this.snake.setCurrentDirection(direction);
			}
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
	
}
