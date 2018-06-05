package game;
import javax.swing.JPanel;

public class Cell {

	private JPanel panel;
	private int value;
	
	private int index;
	
	public Cell(int value, int index) {
		this.panel = new JPanel();
		this.setValue(value);
		this.setIndex(index);
	}
	
	public int getIndex() {
		return this.index;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
	
	public int getX() {
		
		return index % Game.board.getRows();
		
	}
	
	public int getY() {
		
		return index / Game.board.getRows();
		
	}

	public JPanel getPanel() {
		return panel;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
		if (value <= 6) {
			this.panel.setBackground(Game.COLORS[value]);
		} else {
			this.panel.setBackground(Game.COLORS[7]);
		}
	}

}
