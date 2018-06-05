package game;

public class HumanPlayer extends Player {
	
	private Credentials credentials;
	
	public HumanPlayer(int id, Credentials credentials) {
		
		super(id, credentials.getUsername());
		this.credentials = credentials;
	}
	
	@Override
	public void run() {
		
		this.getSnake().move();
		
	}
	
	public Credentials getCredentials() {
		return this.credentials;
	}
	
	@Override
	public boolean equals (Object o) {
		
		if (o.getClass().equals(HumanPlayer.class) && ((HumanPlayer)o).getPlayerID() == this.getPlayerID()) {
			return true;
		}
		return false;
		
	}

}
