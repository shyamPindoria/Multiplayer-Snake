package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import game.Cell;
import game.GameBoard;

public class GameBoardTest {

	@Test
	public void testCreateGameBoard() {
		GameBoard board = new GameBoard(10, 10);
		assertEquals("GameBoard rows set incorrectly.", 10, board.getRows());
		assertEquals("GameBoard columns set incorrectly.", 10, board.getCols());
	}
	
	@Test
	public void testGetCell() {
		GameBoard board = new GameBoard(10, 10);
		assertEquals("GameBoard getCell(int x, int y) or getCell(int index) not working.", board.getCell(1) , board.getCell(1, 0));
	}
	
	@Test
	public void testSwapCell() {
		GameBoard board = new GameBoard(10, 10);
		Cell a = board.getCell(5);
		Cell b = board.getCell(6);
		board.swapCell(a, b);
		assertEquals("GameBoard did not swap cells correctly.", a, board.getCell(6));
		assertEquals("GameBoard did not swap cells correctly.", b, board.getCell(5));
	}
	
	@Test
	public void testGetSetApple() {
		GameBoard board = new GameBoard(10, 10);
		board.setAppleIndex(5);
		assertEquals("GameBoard apple index not set or getAppleIndex() not implemented correctly.", 5, board.getAppleIndex());
	}
	

}
