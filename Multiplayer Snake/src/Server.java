import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable{

	private ExecutorService pool;
	
	private Thread serverThread;

	public Server() {
		
		this.pool = Executors.newFixedThreadPool(4);
		this.serverThread = new Thread(this);
		this.serverThread.start();
		
	}
	
	public void update() {
		
			try {
				HumanPlayer player = Game.buffer_HumanPlayers.poll();

				if (player != null && !player.getCredentials().isValid()) {
					loginPlayer(player);
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
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

	/**
	 * Logs in a player by submitting the credentials to the ExecutorService
	 * @param credentials Player credentials to check
	 */
	public void loginPlayer(HumanPlayer player) {
		
		// Submit the player to the executor service
		pool.submit(player.getCredentials());
		
	}

	
	@Override
	public void run() {
		while (!Game.gameOver) {
			update();
			System.out.println(Game.getUI().getSize().getWidth() + " by " + Game.getUI().getSize().getHeight());
			
		}
		
	}

}