import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable{

	private ExecutorService pool;
	
	private Thread serverThread;
	
	public Queue<Snake.Direction> moveQueue; // stores all moves to be processed

	public Server() {
		
		this.pool = Executors.newFixedThreadPool(4);
		this.serverThread = new Thread(this);
		this.serverThread.start();
		moveQueue = new LinkedList<Snake.Direction>();
	}
	
	public void update() {
		
	}
	
	public Queue<Snake.Direction> getMoveQueue() {
		return moveQueue;
	}

	public void setMoveQueue(Queue<Snake.Direction> moveQueue) {
		this.moveQueue = moveQueue;
	}

	private void loginPlayers() {
			try {
				HumanPlayer player = Game.buffer_HumanPlayers.poll();

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