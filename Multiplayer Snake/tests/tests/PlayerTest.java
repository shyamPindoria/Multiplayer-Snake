package tests;

import org.junit.*;

import game.Client;
import game.Credentials;
import game.HumanPlayer;
import game.SimulatedPlayer;

import static org.junit.Assert.assertEquals;

public class PlayerTest {

	
	@Test
	public void testCreateCllient() {
		Client client = new Client(1, "user", "password");
		assertEquals("ID set incorrectly.", 1, client.getID());
		assertEquals("Username set incorrectly.", "user", client.getUsername());
		assertEquals("Username set incorrectly.", "password", client.getPassword());
	}
	
	@Test
	public void testCreateHumanPlayer() {
		Client client = new Client(1, "user", "password");
		HumanPlayer player = new HumanPlayer(1, client);
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
