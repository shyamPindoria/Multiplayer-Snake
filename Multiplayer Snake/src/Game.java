import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Game {
	
	public static final Color[] COLORS = {
			Color.LIGHT_GRAY, 	// Empty Cell
			Color.RED, 			// Player 1
			Color.GREEN, 		// Player 2
			Color.BLUE,  		// Player 3
			Color.ORANGE, 		// player 4
			Color.MAGENTA, 		// Food
			Color.BLACK,		// Simulated Player
			Color.DARK_GRAY 	// Snake's Head
			};
	
	public static int numberOfPlayers;
	public static GameBoard board;
	public static MapDB db;
	private static UIController ui;
	
	public static Server server;
	
	public static ArrayList<HumanPlayer> humanPlayers;
	
	public static ArrayBlockingQueue<HumanPlayer> loginBuffer; 
	
	public static boolean gameStarted = false;
	
	public static boolean gameOver = false;
	
	private static Timer timer;
	
	public static void main(String[] args) {

		board = new GameBoard(100, 100);
		
		ui = new UIController();
		
		humanPlayers = new ArrayList<HumanPlayer>();
		
		loginBuffer = new ArrayBlockingQueue<HumanPlayer>(4);
		
		db = new MapDB();
		
		server = new Server();
		
		timer = new Timer(500, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				server.update();
			}
			
		});
		
	}
	
	/**
	 * Logs in the players
	 * @param credentials Credentials of the players
	 * @return 
	 * @return boolean[] containing the results of the login process
	 */
	public static void createPlayers (Credentials[] credentials) {
		
		for (int i = 0; i < Game.numberOfPlayers; i++) {
			
			HumanPlayer player = new HumanPlayer(i + 1, credentials[i]);
			// *** CAN'T SYSOUT HERE FOR SOME REASON. GET AN OUTOFBOUNDSEXCEPTION **************** 
			int playerAlreadyAdded = humanPlayers.indexOf(player);
			
			if (playerAlreadyAdded == -1) {
				humanPlayers.add(player);
			} else {
				humanPlayers.set(playerAlreadyAdded, player);
			}
			
			try {
				loginBuffer.put(player);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
	
	public static UIController getUI() {
		return ui;
	}
	
	public static void initGame() {

		for (int i = 0; i < humanPlayers.size(); i++) {

			Snake playerSnake = humanPlayers.get(i).getSnake(); 
			if (i == 0) {
				// First part added is a head by default i.e cell has index 7
				playerSnake.addBodyPart(88, 10);
				playerSnake.addBodyPart(89, 10);
				playerSnake.addBodyPart(90, 10);
				playerSnake.setCurrentDirection(Snake.Direction.LEFT);
			} else if (i == 1) {
				playerSnake.addBodyPart(10, 13);
				playerSnake.addBodyPart(10, 12);
				playerSnake.addBodyPart(10, 11);
				playerSnake.setCurrentDirection(Snake.Direction.DOWN);
			} else if (i == 2) {
				playerSnake.addBodyPart(12, 90);
				playerSnake.addBodyPart(11, 90);
				playerSnake.addBodyPart(10, 90);
				playerSnake.setCurrentDirection(Snake.Direction.RIGHT);
			} else if (i == 3) {
				playerSnake.addBodyPart(90, 91);
				playerSnake.addBodyPart(90, 92);
				playerSnake.addBodyPart(90, 93);
				playerSnake.setCurrentDirection(Snake.Direction.UP);
			}
		}
		
		// Start timer
		timer.start();
		
		// place apple in a random cell
		int randomCell = new Random().nextInt(10000) + 1;
		board.getCell(randomCell).getPanel().setBackground(COLORS[5]);
		board.setApple(board.getCell(randomCell).getPanel());
		
		
		// note: 
		// food isn't placed and values for each cell are not set
		
	}
	

	
	
}
