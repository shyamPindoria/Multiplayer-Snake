package game;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
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

	public static ArrayBlockingQueue<HumanPlayer> loginBuffer;

	public static boolean gameStarted = false;

	public static boolean gameOver = false;

	private static Timer timer;

	public static void main(String[] args) {

		board = new GameBoard(100, 100);

		ui = new UIController();

		humanPlayers = new ConcurrentHashMap<Integer, HumanPlayer>();

		simulatedPlayers = new ConcurrentHashMap<Integer, SimulatedPlayer>();

		playersToDie = new Stack<Integer>();

		loginBuffer = new ArrayBlockingQueue<HumanPlayer>(4);

		db = new MapDB();

		server = new Server();

		timer = new Timer(5, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				server.update();
			}

		});

	}

	/**
	 * Logs in the players
	 * 
	 * @param credentials
	 *            Credentials of the players
	 * @return
	 * @return boolean[] containing the results of the login process
	 */
	public static void createPlayer(int id, String username, String password) {

		HumanPlayer player = new HumanPlayer(id, username, password);
		humanPlayers.put(id, player);
		try {
			loginBuffer.put(player);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public static UIController getUI() {
		return ui;
	}

	public static void initGame() {

		for(int i = 7; i <= 107; i++) {

			SimulatedPlayer sim = new SimulatedPlayer(i, "Bot " + (i - 6));
			Random r = new Random();
			int randomHeadX = r.nextInt(96);
			int randomHeadY = r.nextInt(96);
			if ( board.getCell(randomHeadX, randomHeadY).getValue() == 0 &&
					board.getCell(randomHeadX+1, randomHeadY).getValue() == 0 &&
					board.getCell(randomHeadX+1, randomHeadY).getValue() == 0 ) {

				sim.getSnake().addBodyPart(randomHeadX, randomHeadY);
				sim.getSnake().addBodyPart(randomHeadX+1, randomHeadY);
				sim.getSnake().addBodyPart(randomHeadX+2, randomHeadY);

			}

			sim.getSnake().setCurrentDirection(Snake.Direction.LEFT);

			simulatedPlayers.put(i, sim);

		}

		for (int i = 1; i <= Game.numberOfPlayers; i++) {

			Snake playerSnake = humanPlayers.get(i).getSnake();
			if (i == 1) {
				// First part added is a head by default i.e cell has index 7
				playerSnake.addBodyPart(88, 10);
				playerSnake.addBodyPart(89, 10);
				playerSnake.addBodyPart(90, 10);
				playerSnake.setCurrentDirection(Snake.Direction.LEFT);
			} else if (i == 2) {
				playerSnake.addBodyPart(10, 13);
				playerSnake.addBodyPart(10, 12);
				playerSnake.addBodyPart(10, 11);
				playerSnake.setCurrentDirection(Snake.Direction.DOWN);
			} else if (i == 3) {
				playerSnake.addBodyPart(12, 90);
				playerSnake.addBodyPart(11, 90);
				playerSnake.addBodyPart(10, 90);
				playerSnake.setCurrentDirection(Snake.Direction.RIGHT);
			} else if (i == 4) {
				playerSnake.addBodyPart(90, 91);
				playerSnake.addBodyPart(90, 92);
				playerSnake.addBodyPart(90, 93);
				playerSnake.setCurrentDirection(Snake.Direction.UP);
			}
		}

		// Start timer
		timer.start();

		// place apple in a random cell
		int randomIndex = new Random().nextInt(10000) + 1;
		Cell food = new Cell(5, randomIndex);
		board.swapCell(food, board.getCell(randomIndex));
		board.setAppleIndex(randomIndex);


		// note:
		// food isn't placed and values for each cell are not set

	}

	public static synchronized void removePlayer(int id) {
		playersToDie.push(id);
	}
	
	public static void gameOver() {
		gameOver = true;
		server.shutdownExecutorService();
		ui.gameOver();
	}

}
