
public class HumanPlayer extends Player {
	
	private Credentials credentials;
	
	public HumanPlayer(int id, Credentials credentials) {
		super(id, credentials.getUsername());
		this.credentials = credentials;
	}
	
	public void getInput() {
		
	}

	@Override
	public void makeMove() {
		
	}

	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
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
