package game;

public class HumanPlayer extends Player {
	
	private String username;
	private String password;
	private boolean isValid;
	
	public HumanPlayer(int id, String username, String password) {
		
		super(id, username);
		this.username = username;
		this.password = password;
		this.isValid = false;
	}
	
	@Override
	public void run() {
		
		this.getSnake().move();
		
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public boolean isValid() {
		return this.isValid;
	}
	
	public void setValid(boolean value) {
		this.isValid = value;
	}
	
	@Override
	public boolean equals (Object o) {
		
		if (o.getClass().equals(HumanPlayer.class) && ((HumanPlayer)o).getPlayerID() == this.getPlayerID()) {
			return true;
		}
		return false;
		
	}

}
