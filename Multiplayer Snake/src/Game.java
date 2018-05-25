import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.Random;

public class Game {
	
	public static int numberOfPlayers;
	public static GameBoard board;
	public static MapDB db;
	private static UIController ui;
	
	private static Server server;
	
	public static ArrayList<HumanPlayer> humanPlayers;
	
	public static ArrayBlockingQueue<HumanPlayer> buffer_HumanPlayers; 
	
	public static boolean gameStarted = false;
	
	public static boolean gameOver = false;
	
	public static void main(String[] args) {

		board = new GameBoard(100, 100);
		
		ui = new UIController();
		
		humanPlayers = new ArrayList<HumanPlayer>();
		
		buffer_HumanPlayers = new ArrayBlockingQueue<HumanPlayer>(4);
		
		db = new MapDB();
		
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
	}
	
	public static UIController getUI() {
		return ui;
	}
	
	public static void initGame() {
		for (HumanPlayer humanPlayer : humanPlayers) {
			Snake playerSnake = new Snake();
			playerSnake.setColour(randomColor());
			humanPlayer.setSnake(playerSnake);
		}

		
	}
	
    private static Color randomColor() {
        Random random = new Random();
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        return new Color(r,g,b);
    }
	
	
}
