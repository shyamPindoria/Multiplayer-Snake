package tests;

import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.Test;

import game.Cell;

public class CellTest {

	@Test
	public void testNewCell() {
		Cell cell = new Cell(0, 0);
		assertEquals("Cell value set incorrectly.", 0, cell.getValue());
		assertEquals("Cell index set incorrectly.", 0, cell.getIndex());
	}
	
	@Test
	public void testCellSetValue() {
		Cell cell = new Cell(0, 0);
		cell.setValue(5);
		assertEquals("Cell value set incorrectly.", 5, cell.getValue());
	}
	
	@Test
	public void testCellBackgroundColor() {
		Cell cell = new Cell(0, 0);
		cell.setValue(5);
		assertEquals("Cell panel color set incorrectly.", Color.MAGENTA, cell.getPanel().getBackground());
	}

}
