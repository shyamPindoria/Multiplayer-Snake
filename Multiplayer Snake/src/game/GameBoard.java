package game;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class GameBoard {
	
	private ConcurrentHashMap<Integer, Cell> cells;
	private Integer appleIndex;

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
	
	public Cell getCell(int index) {
		return this.cells.get(index);
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
			this.cells.put(i, new Cell(0, i));
		}
		
	}
	
	public void placePlayerOnBoard(Player player) {
		Random r = new Random();
		int randomHeadX = r.nextInt(86) + 10;
		int randomHeadY = r.nextInt(86) + 10;
		if ( this.getCell(randomHeadX, randomHeadY).getValue() == 0 &&
				this.getCell(randomHeadX+1, randomHeadY).getValue() == 0 &&
				this.getCell(randomHeadX+1, randomHeadY).getValue() == 0 ) {

			player.getSnake().addBodyPart(randomHeadX, randomHeadY);
			player.getSnake().addBodyPart(randomHeadX+1, randomHeadY);
			player.getSnake().addBodyPart(randomHeadX+2, randomHeadY);

		}

		player.getSnake().setCurrentDirection(Snake.Direction.LEFT);
	}
	
	public void swapCell(Cell a, Cell b) {

		this.cells.put(a.getIndex(), b);
		this.cells.put(b.getIndex(), a);
		int tempIndex = a.getIndex();
		a.setIndex(b.getIndex());
		b.setIndex(tempIndex);
	}

	public void spawnApple() {
		// place apple in a random cell
		int randomIndex = new Random().nextInt(10000);
		
		while(this.getCell(randomIndex).getValue() != 0) {
			randomIndex = new Random().nextInt(10000);
		}
		
		this.getCell(randomIndex).setValue(5);
		this.setAppleIndex(randomIndex);
	}

	public int getAppleIndex() {
		return appleIndex;
	}

	public void setAppleIndex(int appleIndex) {
		this.appleIndex = appleIndex;
	}
}
