import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

public class Game {
	
	public static int numberOfPlayers;
	private static GameBoard board;
	public static MapDB db;
	private static UIController ui;
	
	private static Server server;
	public static ArrayList<HumanPlayer> humanPlayers;
	
	public static ArrayBlockingQueue<HumanPlayer> buffer_HumanPlayers;
	
	public static boolean gameOver;
	
	public static void main(String[] args) {
		
		ui = new UIController();
		
		board = new GameBoard(100, 100);
		
		humanPlayers = new ArrayList<HumanPlayer>();
		
		db = new MapDB();
		
		buffer_HumanPlayers = new ArrayBlockingQueue<HumanPlayer>(4);
		
		gameOver = false;
		
		server = new Server();
		
	}
	
	/**
	 * Logs in the players
	 * @param credentials Credentials of the players
	 * @return 
	 * @return boolean[] containing the results of the login process
	 */
	public static void createPlayers (Credentials[] credentials) {
		
		for (int i = 0; i < Game.numberOfPlayers; i++) {
			
			HumanPlayer player = new HumanPlayer(i, credentials[i]);
			
			int playerAlreadyAdded = humanPlayers.indexOf(player);
			
			if (playerAlreadyAdded == -1) {
				humanPlayers.add(player);
			} else {
				humanPlayers.set(playerAlreadyAdded, player);
			}
			
			try {
				buffer_HumanPlayers.put(player);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		
		
		try {
			System.out.println("Thread Sleeping");
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		ui.showGameBoard();
		
		
	}
	
	public static UIController getUI() {
		return ui;
	}
	
	
}
