import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

	private ExecutorService pool;

	public Server() {
		
		this.pool = Executors.newFixedThreadPool(4);
		
		update();
		
	}
	
	public void update() {
		
		while (!Game.gameOver) {
			
			try {
				HumanPlayer player = Game.buffer_HumanPlayers.take();

				if (!player.getCredentials().isValid()) {
					loginPlayer(player);
				}

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		}

	}
	
	/**
	 * Shuts down the executor service
	 */
	public void shutdownExecutorService() {
	
		this.pool.shutdown();
		
	}

	/**
	 * Logs in a player by submitting the credentials to the ExecutorService
	 * @param credentials Player credentials to check
	 */
	public void loginPlayer(HumanPlayer player) {
		
		// Submit the player to the executor service
		pool.submit(player.getCredentials());
		
	}

}