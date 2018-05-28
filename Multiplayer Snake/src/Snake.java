import java.util.ArrayList;


public class Snake {
	// could use this to tell where a snakes body is. the int values will be the index in the cells map
	ArrayList<Cell> snakeBody;
	
	private int snakeID;
	
	Direction currentDirection;

	public enum Direction {
	    RIGHT, 
	    LEFT, 
	    UP, 
	    DOWN;
	}
	
	public Snake(int snakeID) {
		snakeBody = new ArrayList<Cell> ();
		this.snakeID = snakeID;
	}
	
	public void setCurrentDirection(Direction direction) {
		currentDirection = direction;
	}
	
	public Direction getCurrentDirection() {
		return currentDirection;
	}
	
	public void addBodyPart(int x, int y) {
		
		if (this.snakeBody.isEmpty()) {
			Game.board.getCell(x, y).setValue(7);
			
		} else {
			Game.board.getCell(x, y).setValue(snakeID);
		}
		
		this.snakeBody.add(Game.board.getCell(x, y));
		
	}
	
	public void died() {
		for (Cell cell : this.snakeBody) {
			cell.setValue(0);
		}
	}
    
    public Cell getHead() {
    	return this.snakeBody.get(0);
    }
    
    public void move() {
    	
    	Cell temp = this.getHead();
    	for (int i = 0; i < this.snakeBody.size(); i++) {
    		
    		if (i == 0) {
    			
    			if (this.currentDirection == Direction.RIGHT) {
    				temp =  Game.board.getCell(this.snakeBody.get(i).getIndex() + 1);
    			}
    			if (this.currentDirection == Direction.LEFT) {
    				temp =  Game.board.getCell(this.snakeBody.get(i).getIndex() - 1);
    			}
    			if (this.currentDirection == Direction.UP) {
    				temp =  Game.board.getCell(this.snakeBody.get(i).getIndex() - 100);
    			}
    			if (this.currentDirection == Direction.DOWN) {
    				temp =  Game.board.getCell(this.snakeBody.get(i).getIndex() + 100);
    			}
    			
    			Game.board.swapCell(temp, this.snakeBody.get(i));
    			
    		} else {
    			
    			Game.board.swapCell(temp, this.snakeBody.get(i));
    			
    		}
    	}
    }
    
}



// LOOP THROUGH MAP USING THIS...
//Iterator<Entry<Integer, Cell>> entries = Game.board.getCells().entrySet().iterator();
//while (entries.hasNext()) {
//	Entry<Integer, Cell> entry = entries.next();
//	
//}