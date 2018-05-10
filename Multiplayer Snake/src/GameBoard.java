import java.util.concurrent.ConcurrentHashMap;

public class GameBoard {
	
	private ConcurrentHashMap<Integer, Integer> board;

	public GameBoard(int rows, int cols) {
		this.board = new ConcurrentHashMap<Integer, Integer>();
	}
	
	public int getValueAt(int x, int y) {
		//TODO
		return 0;
	}
	
	public void setValueAt(int x, int y) {
		//TODO
	}

}
