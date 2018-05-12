import java.util.concurrent.ConcurrentHashMap;

import javax.swing.JPanel;

public class GameBoard {
	
	private ConcurrentHashMap<Integer, Cell> board;
	
	private int rows;
	private int cols;

	public GameBoard(int rows, int cols) {
		
		this.board = new ConcurrentHashMap<Integer, Cell>();
		
		this.rows = rows;
		this.cols = cols;
		
		initializeGameBoard(rows * cols);
		
	}
	
	public int getRows() {
		return this.rows;
	}
	
	public int getCols() {
		return this.cols;
	}
	
	public int getValueAt(int x, int y) {
		
		return this.board.get((y * rows) + x).getValue();
		
	}
	
	public void setValueAt(int x, int y, int value) {
		
		this.board.get((y * rows) + x).setValue(value);
		
	}
	
	public JPanel getCellAt(int index) {
		return this.board.get(index).getPanel();
	}
	
	private void initializeGameBoard(int size) {
		
		this.board.clear();
		
		for (int i = 0; i < size; i++) {
			this.board.put(i, new Cell());
		}
		
	}

}
