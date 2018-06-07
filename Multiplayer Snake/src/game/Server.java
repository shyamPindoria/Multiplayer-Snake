package game;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable {

	private ExecutorService pool;

	private Thread serverThread;

	public Server() {

		this.pool = Executors.newFixedThreadPool(4);
		this.serverThread = new Thread(this);
		this.serverThread.start();
		
	}

	public void update() {
		//System.out.println("beep");
		
		for (HumanPlayer player : Game.humanPlayers.values()) {
			pool.submit(player);
		}
		
		for (SimulatedPlayer player : Game.simulatedPlayers.values()) {
			pool.submit(player);
		}
		
		while (!Game.playersToDie.isEmpty()) {
			int playerID = Game.playersToDie.pop();
			if (playerID <= 4) {
				Game.humanPlayers.remove(playerID);
				System.out.println("Player " + playerID + " died");
			} else {
				Game.simulatedPlayers.remove(playerID);
			}
				
		}
		
		if (Game.simulatedPlayers.size() == 0 && Game.humanPlayers.size() == 0) {
			Game.gameOver();
		}
		
		Game.getUI().update();
		
	}

	private void loginPlayers() {
		try {
			Client client = Game.loginBuffer.poll();

			if (client != null && !client.isAuthenticated()) {
				pool.submit(new Credentials(client));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		Game.getUI().showGameBoard();
	}

	/**
	 * Shuts down the executor service
	 */
	public void shutdownExecutorService() {

		this.pool.shutdown();

	}

	@Override
	public void run() {
		
		while (!Game.gameStarted) {
			this.loginPlayers();
		}

	}


}