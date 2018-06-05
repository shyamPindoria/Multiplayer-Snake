package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import game.Snake;
import game.Snake.Direction;

public class SnakeTest {
	
	@Test
	public void testSnakeDirection() {
		Snake snake = new Snake(1);
		snake.setCurrentDirection(Direction.LEFT);
		assertEquals("Snake direction set incorrectly.", Direction.LEFT, snake.getCurrentDirection());
	}
	
	@Test
	public void testSnakeCollision() {
		Snake snake = new Snake(1);
		assertTrue("Snake should collide with another player.", snake.testSnakeCollision(1));
		assertFalse("Snake should not collide with a blank cell.", snake.testSnakeCollision(0));
	}
	

}
