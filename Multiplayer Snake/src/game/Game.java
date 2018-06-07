package game;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.Timer;

public class Game {

	public static final Color[] COLORS = { 
			Color.LIGHT_GRAY, // Empty Cell
			Color.RED, // Player 1
			Color.GREEN, // Player 2
			Color.BLUE, // Player 3
			Color.ORANGE, // player 4
			Color.MAGENTA, // Food
			Color.BLACK, // Snake's Head
			Color.GRAY // Simulated Player
	};

	public static int numberOfPlayers;
	public static GameBoard board;
	public static MapDB db;
	private static UIController ui;

	public static Server server;

	public static ConcurrentHashMap<Integer, HumanPlayer> humanPlayers;

	public static ConcurrentHashMap<Integer, SimulatedPlayer> simulatedPlayers;

	public static Stack<Integer> playersToDie;

	public static ArrayBlockingQueue<Client> loginBuffer;

	public static boolean gameStarted = false;

	public static boolean gameOver = false;

	private static Timer timer;

	public static void main(String[] args) {

		board = new GameBoard(100, 100);

		ui = new UIController();

		humanPlayers = new ConcurrentHashMap<Integer, HumanPlayer>();

		simulatedPlayers = new ConcurrentHashMap<Integer, SimulatedPlayer>();

		playersToDie = new Stack<Integer>();

		loginBuffer = new ArrayBlockingQueue<Client>(4);

		db = new MapDB();

		server = new Server();

		timer = new Timer(5, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (gameStarted && !gameOver) {
					server.update();
				}
			}

		});

	}

	public synchronized static void createClient(int id, String username, String password) {

		Client client = new Client(id, username, password);
		Thread t = new Thread(client);
		t.start();

	}
	
	public synchronized static void addHumanPlayer(HumanPlayer player) {
		humanPlayers.put(player.getPlayerID(), player);
	}
	
	public synchronized static void addToLoginBuffer(Client client) {
		try {
			loginBuffer.put(client);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static UIController getUI() {
		return ui;
	}

	public static void initGame() {

		for (HumanPlayer player : humanPlayers.values()) {
			board.placePlayerOnBoard(player);
		}
		
		createSimulatedPlayers();

		// Start timer
		timer.start();

		board.spawnApple();

	}
	
	private static void createSimulatedPlayers() {
		
		for(int i = 7; i <= 107; i++) {

			SimulatedPlayer sim = new SimulatedPlayer(i, "Bot " + (i - 6));
			board.placePlayerOnBoard(sim);
			simulatedPlayers.put(i, sim);

		}
		
	}

	public synchronized static void removePlayer(int id) {
		playersToDie.push(id);
	}
	
	public static void gameOver() {
		gameOver = true;
		server.shutdownExecutorService();
		ui.gameOver();
	}

}
