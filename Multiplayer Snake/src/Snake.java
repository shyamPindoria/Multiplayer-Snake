import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Random;


public class Snake {
	// could use this to tell where a snakes body is. the int values will be the index in the cells map
	//ArrayList<Integer> snakeBody; 
	
	Direction currentDirection;

	public enum Direction {
	    RIGHT, 
	    LEFT, 
	    UP, 
	    DOWN;
	}
	
	public Snake() {
		
	}
	
	public void setCurrentDirection(Direction direction) {
		currentDirection = direction;
	}
	
	public Direction getCurrentDirection() {
		return currentDirection;
	}
	
	public void addBodyPart(int x, int y, int playerID, boolean isSnakeHead) {
		Game.board.getCell(x, y).setValue(playerID);
		if (isSnakeHead) {
			Game.board.getCell(x, y).setSnakeHead(true);
		}
	}
	
	public void removeBodyPart(Cell bodyPart) {
		bodyPart.setValue(0);
		if (bodyPart.isSnakeHead()) {
			bodyPart.setSnakeHead(false);
		}
	}
	
    public void removeBody(int playerID) {
    	Iterator<Entry<Integer, Cell>> cells = Game.board.getCells().entrySet().iterator();
    	while (cells.hasNext()) {
    		Entry<Integer, Cell> entry = cells.next();
    		Cell cell = entry.getValue();
    		if (cell.getValue() == playerID) {
        		cell.setValue(0);
        		if (cell.isSnakeHead()) {
        			cell.setSnakeHead(false);
        		}
    		}

    	}
    }
    
    public Cell getHead() {
    	Iterator<Entry<Integer, Cell>> entries = Game.board.getCells().entrySet().iterator();
    	while (entries.hasNext()) {
    		Entry<Integer, Cell> entry = entries.next();
    		Cell cell = entry.getValue();
    		if (cell.isSnakeHead()) {
    			return cell;
    		}
    	}
    	return null;
    }
    
}

// LOOP THROUGH MAP USING THIS...
//Iterator<Entry<Integer, Cell>> entries = Game.board.getCells().entrySet().iterator();
//while (entries.hasNext()) {
//	Entry<Integer, Cell> entry = entries.next();
//	
//}