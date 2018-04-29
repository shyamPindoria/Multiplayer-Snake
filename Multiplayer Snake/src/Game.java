import java.util.ArrayList;

public class Game {
	
	public static int numberOfPlayers;
	private static GameBoard board;
//	public static MapDB db;
	private static UIController ui;
	
	private static Server server;
	private static ArrayList<Player> players;
	

	public static void main(String[] args) {
		
		ui = new UIController();
		
		board = new GameBoard(100, 100);
		
		server = new Server();
		
		players = new ArrayList<Player>();
		
	}
	
	public static boolean[] loginPlayers (String[] usernames, String[] passwords) {
		
		boolean[] loggedIn = new boolean[Game.numberOfPlayers];
		
		for (int i = 0; i < Game.numberOfPlayers; i++) {
			
			loggedIn[i] = server.loginPlayer(usernames[i], passwords[i]);
		}
		
		return loggedIn;
		
	}
	
}
