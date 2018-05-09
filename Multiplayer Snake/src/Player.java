
public abstract class Player implements Runnable{

	private int playerID;
	private String name;
	private int score;
	
	
	
	public Player(int id, String name) {
		this.playerID = id;
		this.name = name;
		this.score = 0;
	}
	
	public abstract void makeMove();
	
	public String getName() {
		return this.name;
	}

	public int getPlayerID() {
		return playerID;
	}

	public void scored(int score) {
		this.score += score;
	}
	
	@Override
	public boolean equals (Object o) {
		
		if (o.getClass().equals(Player.class) && ((Player)o).getPlayerID() == this.playerID) {
			return true;
		}
		return false;
		
	}
	
	@Override
	public String toString() {
		return this.name + ": " + this.score;
	}
	
}
