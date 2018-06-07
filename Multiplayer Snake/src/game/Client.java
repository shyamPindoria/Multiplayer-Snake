package game;

public class Client implements Runnable{

	private int id;
	private String username;
	private String password;
	private boolean isAuthenticated;
	
	public Client(int id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.isAuthenticated = false;
	}
	
	public int getID() {
		return this.id;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isAuthenticated() {
		return this.isAuthenticated;
	}
	
	public void setAuthenticated(boolean value) {
		this.isAuthenticated = value;
	}
	
	public void createPlayer() {
		HumanPlayer player = new HumanPlayer(id, this);
		Game.addHumanPlayer(player);
		Game.board.placePlayerOnBoard(player);
	}

	@Override
	public void run() {
		if (!this.isAuthenticated()) {
			Game.addToLoginBuffer(this);
		}
		
	}

}
