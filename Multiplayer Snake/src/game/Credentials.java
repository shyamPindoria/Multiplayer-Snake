package game;

public class Credentials implements Runnable{

	private Client client;
	
	public Credentials(Client client) {
		this.client = client;
	}

	@Override
	public void run() {
		boolean valid = false;
		// Check the credentials
		if (Game.db.getUsers().get(client.getUsername()) != null && Game.db.getUsers().get(client.getUsername()).equals(client.getPassword())) {
			
			System.out.println(client.getUsername() + " logged in");
			
			valid = true;
			client.createPlayer();
		} else {
			valid = false;
		}
		
		Game.getUI().setInvalidLoginDetails(client.getID(), valid);
		client.setAuthenticated(valid);
		
	}

}
