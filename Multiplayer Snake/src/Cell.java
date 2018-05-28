import java.awt.Color;

import javax.swing.JPanel;

public class Cell {

	// need to continually update these three variables
	private JPanel panel;
	private int value;
	private boolean snakeHead;
	
	private boolean appleOccupied;
	private boolean snakeOccupied;
	
	public Cell(int value) {
		this.setPanel(new JPanel());
		this.setValue(value);
		setSnakeHead(false);
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
		if (value <= 6) {
			this.panel.setBackground(Game.COLORS[value]);
		} else {
			this.panel.setBackground(Game.COLORS[6]);
		}
	}

	public boolean isSnakeOccupied() {
		return snakeOccupied;
	}

	public void setSnakeOccupied(boolean snakeOccupied) {
		this.snakeOccupied = snakeOccupied;
	}

	public boolean isAppleOccupied() {
		return appleOccupied;
	}

	public void setAppleOccupied(boolean appleOccupied) {
		this.appleOccupied = appleOccupied;
	}

	public boolean isSnakeHead() {
		return snakeHead;
	}

	public void setSnakeHead(boolean snakeHead) {
		if (snakeHead) this.panel.setBackground(Game.COLORS[7]);
		this.snakeHead = snakeHead;
	}
	


}
