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
		
		for (HumanPlayer player : Game.humanPlayers) {
			pool.submit(player);
		}
		
		for (SimulatedPlayer player : Game.simulatedPlayers) {
			pool.submit(player);
		}
		
		Game.getUI().update();
		
		
		
	}

	private void loginPlayers() {
		try {
			HumanPlayer player = Game.loginBuffer.poll();

			if (player != null && !player.getCredentials().isValid()) {
				pool.submit(player.getCredentials());
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
		
		while (!Game.gameOver) {
			if (!Game.gameStarted) {
				this.loginPlayers();
			}
			
			//this.update();
			
		}

	}


}