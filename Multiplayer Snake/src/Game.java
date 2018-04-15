
public class Game {
	
	public static int numberOfPlayers;

	public static void main(String[] args) {
		
		UIController ui = new UIController();
		
	}
	
	public static boolean[] loginPlayers (String[] usernames, String[] passwords) {
		
		boolean[] loggedIn = new boolean[Game.numberOfPlayers];
		
		for (int i = 0; i < Game.numberOfPlayers; i++) {
			// TODO add the player to a login queue, a thread will then retrieve a player from the queue
			// The thread will check if the details are correct
			System.out.println(usernames[i] + " logged in. Password: " + passwords[i]);
			loggedIn[i] = true;
		}
		
		return loggedIn;
		
	}
	
}
