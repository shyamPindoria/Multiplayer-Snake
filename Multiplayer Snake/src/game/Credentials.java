package game;

public class Credentials implements Runnable{

	private int playerID;
	private String username;
	private String password;
	
	public Credentials(int playerID, String username, String password) {
		this.playerID = playerID;
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return this.username;
	}

	@Override
	public void run() {
		boolean valid = false;
		// Check the credentials
		if (Game.db.getUsers().get(this.username) != null && Game.db.getUsers().get(this.username).equals(this.password)) {
			
			System.out.println(this.username + " logged in");
			
			valid = true;
		} else {
			valid = false;
		}
		
		Game.getUI().setInvalidLoginDetails(this.playerID, valid);
		Game.humanPlayers.get(this.playerID).setValid(valid);
		
	}

}
