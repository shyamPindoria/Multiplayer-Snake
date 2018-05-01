import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

public class Game {
	
	public static int numberOfPlayers;
	private static GameBoard board;
	public static MapDB db;
	private static UIController ui;
	
	private static Server server;
	private static ArrayList<Player> players;
	
	public static void main(String[] args) {
		
		ui = new UIController();
		
		board = new GameBoard(100, 100);
		
		server = new Server();
		
		players = new ArrayList<Player>();
		
		db = new MapDB();
	}
	
	/**
	 * Logs in the players
	 * @param credentials Credentials of the players
	 * @return boolean[] containing the results of the login process
	 */
	public static boolean[] loginPlayers (Credentials[] credentials) {
		
		boolean[] loggedIn = new boolean[Game.numberOfPlayers];
		
		server.clearFutures();
		
		for (int i = 0; i < Game.numberOfPlayers; i++) {
			
			server.loginPlayer(credentials[i]);

		}
		
		loggedIn = server.getLoginResults();
		
		//server.shutdownExecutorService();
		
		return loggedIn;
		
	}
	
}
