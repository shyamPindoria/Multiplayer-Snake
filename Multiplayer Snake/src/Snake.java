import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class Snake {

	private ArrayList<JPanel> snakeBody;
	private Direction direction;

	public enum Direction {
	    RIGHT, 
	    LEFT, 
	    UP, 
	    DOWN;
	}
	
	public Snake() {
		snakeBody = new ArrayList<JPanel>();
	}

	
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public void assignStartPosition(GameBoard board, int x, int y, int value) {
		board.setValueAt(x, y, value);
	}
	
	public ArrayList<JPanel> getSnakeBody() {
		return snakeBody;
	}
	
	public void addBodyPart(JPanel bodyPart) {
		snakeBody.add(bodyPart);
	}
	
	public void setColor() {
		for(JPanel bodyPart : snakeBody) {
			bodyPart.setBackground(Color.BLACK);
		}
	}
	
    public Color generateRandomColor() {
        Random random = new Random();
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        return new Color(r,g,b);
    }
    
    public void shiftBody(Snake.Direction direction) {
    	for (JPanel bodyPart : snakeBody) {
    		System.out.println(bodyPart.getX());
    		
    	}
    	
    	
    }
	
}
