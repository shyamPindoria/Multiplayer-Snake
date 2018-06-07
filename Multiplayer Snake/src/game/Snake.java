package game;
import java.util.ArrayList;
import java.util.Random;

public class Snake {
	// could use this to tell where a snakes body is. the int values will be the
	// index in the cells map
	private ArrayList<Cell> snakeBody;

	private int snakeID;

	private Direction currentDirection;

	public enum Direction {
		RIGHT, LEFT, UP, DOWN;
	}

	public Snake(int snakeID) {
		snakeBody = new ArrayList<Cell>();
		this.snakeID = snakeID;
	}

	public Direction getCurrentDirection() {
		return this.currentDirection;
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

		if (temp.getIndex() == Game.board.getAppleIndex()) {
			System.out.println("EATING FOOOOOD!");

			// Grow
			int x = this.snakeBody.get(this.snakeBody.size()-1).getX();
			int y = this.snakeBody.get(this.snakeBody.size()-1).getY();
			
			switch (this.currentDirection) {
			case UP: 
				this.addBodyPart(x, y+1);
				break;
			case RIGHT:
				this.addBodyPart(x-1, y);
				break;
			case DOWN:
				this.addBodyPart(x, y-1);
				break;
			case LEFT:
				this.addBodyPart(x+1, y);
				break;
			}

			Game.board.spawnApple();
			
			if (this.snakeID <= 4) {
				Game.humanPlayers.get(snakeID).scored(5);
			}

		}

		for (int i = 0; i < this.snakeBody.size(); i++) {

			if (i == 0) { // check snake head for collision

				int x = temp.getX();
				int y = temp.getY();

				if (this.currentDirection == Direction.RIGHT) {

					if (x == 99) { // test for collision against wall - right wall
						collision = true;
					} else if (testSnakeCollision(
							Game.board.getCell(this.snakeBody.get(i).getIndex() + 1).getValue()) == true) {
						collision = true;
					} else {
						temp = Game.board.getCell(this.snakeBody.get(i).getIndex() + 1);
					}
				}
				if (this.currentDirection == Direction.LEFT) {
					if (x == 0) {
						collision = true;
					} else if (testSnakeCollision(
							Game.board.getCell(this.snakeBody.get(i).getIndex() - 1).getValue()) == true) {
						collision = true;
					} else {
						temp = Game.board.getCell(this.snakeBody.get(i).getIndex() - 1);
					}
				}
				if (this.currentDirection == Direction.UP) {
					if (y == 0) {
						collision = true;
					} else if (testSnakeCollision(
							Game.board.getCell(this.snakeBody.get(i).getIndex() - Game.board.getRows()).getValue())) {
						collision = true;
					} else {
						temp = Game.board.getCell(this.snakeBody.get(i).getIndex() - Game.board.getRows());
					}
				}
				if (this.currentDirection == Direction.DOWN) {
					if (y == 99) {
						collision = true;
					} else if (testSnakeCollision(
							Game.board.getCell(this.snakeBody.get(i).getIndex() + Game.board.getRows()).getValue())) {
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
			}
		}
	}

	// test for collision against other player. NOT against wall
	public boolean testSnakeCollision(int value) {
		// test against human players (value 1-4), snake head (value 6), and simulated
		// player (value 7-107)
		if ((value > 0 && value < 5) || value == 6 || (value >= 7 && value <= 107)) {
			return true;
		}

		return false;
	}

}
