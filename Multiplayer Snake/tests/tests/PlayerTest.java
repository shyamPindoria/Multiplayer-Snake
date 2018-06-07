package tests;

import org.junit.*;
import game.Credentials;
import game.HumanPlayer;
import game.SimulatedPlayer;

import static org.junit.Assert.assertEquals;

public class PlayerTest {

	
	@Test
	public void testCreateCredentials() {
		Credentials cred = new Credentials(1, "user", "password");
		assertEquals("Username set incorrectly.", "user", cred.getUsername());
	}
	
	@Test
	public void testCreateHumanPlayer() {
		HumanPlayer player = new HumanPlayer(1, "user", "password");
		assertEquals("Username set incorrectly.", "user", player.getName());
		assertEquals("Player ID set incorrectly.", 1, player.getPlayerID());
	}
	
	@Test
	public void testCreateSimulatedPlayer() {
		SimulatedPlayer player = new SimulatedPlayer(1, "user");
		assertEquals("Username set incorrectly.", "user", player.getName());
		assertEquals("Player ID set incorrectly.", 1, player.getPlayerID());
	}
	
	
	
	
	

}
