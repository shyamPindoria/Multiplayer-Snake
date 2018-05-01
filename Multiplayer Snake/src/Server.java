import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Server {

	private ExecutorService pool;
	
	// Stores the results of the login process
	private ArrayList<Future<Boolean>> futures;

	public Server() {
		
		this.pool = Executors.newFixedThreadPool(4);
		
		this.futures = new ArrayList<Future<Boolean>>();
		
	}
	
	/**
	 * Shuts down the executor service
	 */
	public void shutdownExecutorService() {
	
		this.pool.shutdown();
		
	}
	
	/**
	 * Clears the future array
	 */
	public void clearFutures() {
		
		this.futures.clear();
		
	}

	/**
	 * Logs in a player by submitting the credentials to the ExecutorService
	 * @param credentials Player credentials to check
	 */
	public void loginPlayer(Credentials credentials) {
		
		// Create a future and add it to the ArrayList of futures
		Future<Boolean> future = pool.submit(credentials);
		
		this.futures.add(future);
		
	}
	
	/**
	 * This will return the futures
	 * @return A boolean[] containing the values of the futures
	 */
	public boolean[] getLoginResults() {
		
		boolean[] results = new boolean[Game.numberOfPlayers];
		
		for (int i = 0; i < Game.numberOfPlayers; i++) {
			try {
				// Get the future value and add it to the array
				results[i] = this.futures.get(i).get();
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		
		return results;
	}

}