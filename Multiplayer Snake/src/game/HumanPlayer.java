package game;

public class HumanPlayer extends Player {
	
	private Client client;
	
	public HumanPlayer(int id, Client client) {
		
		super(id, client.getUsername());
		this.client = client;
		
	}
	
	public Client getClient() {
		return this.client;
	}
	
	@Override
	public void run() {
		
		this.getSnake().move();
		
	}
	
	@Override
	public boolean equals (Object o) {
		
		if (o.getClass().equals(HumanPlayer.class) && ((HumanPlayer)o).getPlayerID() == this.getPlayerID()) {
			return true;
		}
		return false;
		
	}

}
