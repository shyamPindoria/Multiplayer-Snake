import java.util.ArrayList;

public class Snake {
	// could use this to tell where a snakes body is. the int values will be the
	// index in the cells map
	ArrayList<Cell> snakeBody;

	private int snakeID;

	Direction currentDirection;

	public enum Direction {
		RIGHT, LEFT, UP, DOWN;
	}

	public Snake(int snakeID) {
		snakeBody = new ArrayList<Cell>();
		this.snakeID = snakeID;
	}

	public void setCurrentDirection(Direction direction) {
		currentDirection = direction;
	}

	public void addBodyPart(int x, int y) {

		if (this.snakeBody.isEmpty()) {
			Game.board.getCell(x, y).setValue(6);
		} else {
			Game.board.getCell(x, y).setValue(snakeID);
		}

		this.snakeBody.add(Game.board.getCell(x, y));

	}

	public void died() {
		for (Cell cell : this.snakeBody) {
			cell.setValue(0);
		}

		Game.removePlayer(snakeID);

	}

	public Cell getHead() {
		return this.snakeBody.get(0);
	}


	public void move() {

		boolean collision = false;

		Cell temp = this.getHead();
		for (int i = 0; i < this.snakeBody.size(); i++) {

			if (i == 0) {

				int x = temp.getX();
				int y = temp.getY();

				if (this.currentDirection == Direction.RIGHT) {
					if (x == 99) { // test for collision against wall
						collision = true;
					} else if (testSnakeCollision(Game.board.getCell(this.snakeBody.get(i).getIndex() + 1).getValue()) == true) {
						collision = true;
					} else {
						temp = Game.board.getCell(this.snakeBody.get(i).getIndex() + 1);
					}
				}
				if (this.currentDirection == Direction.LEFT) {
					if (x == 0) {
						collision = true;
					} else if (testSnakeCollision(Game.board.getCell(this.snakeBody.get(i).getIndex() - 1).getValue()) == true) {
						collision = true;
					} else {
						temp = Game.board.getCell(this.snakeBody.get(i).getIndex() - 1);
					}
				}
				if (this.currentDirection == Direction.UP) {
					if (y == 0) {
						collision = true;
					} else if (testSnakeCollision(Game.board.getCell(this.snakeBody.get(i).getIndex() - Game.board.getRows()).getValue())) {
						collision = true;
					} else {
						temp = Game.board.getCell(this.snakeBody.get(i).getIndex() - Game.board.getRows());
					}
				}
				if (this.currentDirection == Direction.DOWN) {
					if (y == 99) {
						collision = true;
					} else if (testSnakeCollision(Game.board.getCell(this.snakeBody.get(i).getIndex() + Game.board.getRows()).getValue())) {
						collision = true;
					} else {
						temp = Game.board.getCell(this.snakeBody.get(i).getIndex() + Game.board.getRows());
					}
				}
			}
			if (!collision) {
				Game.board.swapCell(temp, this.snakeBody.get(i));
			} else {
				died();
				System.out.println("Died");
			}

		}
	}

	// test for collision against other player. NOT against wall
	public boolean testSnakeCollision(int value) {
		// test against human players (value 1-4), snake head (value 6), and simulated player (value 7-107)
		if ((value > 0 && value < 5) || value == 6 || (value >= 7 && value <= 107)) {
			System.out.println("about to hit");
			return true;
		}

		return false;
	}

}
