import java.util.concurrent.ConcurrentHashMap;

import javax.swing.JPanel;

public class GameBoard {
	
	private ConcurrentHashMap<Integer, Cell> cells;
	private JPanel apple = new JPanel();

	private int rows;
	private int cols;

	public GameBoard(int rows, int cols) {
		
		this.cells = new ConcurrentHashMap<Integer, Cell>();
		
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
		return this.cells.get((y * rows) + x).getValue();
	}
	
	public void setValueAt(int x, int y, int value) {
		this.cells.get((y * rows) + x).setValue(value);
	}
	
	public JPanel getCellAt(int index) {
		return this.cells.get(index).getPanel();
	}
	
	public JPanel getCellAt(int x, int y) {
		return this.cells.get((y * rows) + x).getPanel();
	}
	
	public Cell getCell(int x, int y) {
		return cells.get((y * rows) + x);
	}
	
	public ConcurrentHashMap<Integer, Cell> getCells() {
		return cells;
	}
	
	private void initializeGameBoard(int size) {
		
		this.cells.clear();
		
		for (int i = 0; i < size; i++) {
			this.cells.put(i, new Cell(0));
		}
		
	}

	public JPanel getApple() {
		return apple;
	}

	public void setApple(JPanel apple) {
		this.apple = apple;
	}

}
