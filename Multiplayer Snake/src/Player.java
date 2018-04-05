
public abstract class Player {

	private String name;
	private int score;
	
	
	public Player(String name) {
		this.name = name;
		this.score = 0;
	}
	
	public abstract void makeMove();
	
	public String getName() {
		return this.name;
	}
	
	public void scored(int score) {
		this.score += score;
	}
	
	public String toString() {
		return this.name + ": " + this.score;
	}
	
}
