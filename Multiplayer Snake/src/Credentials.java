
public class Credentials implements Runnable{

	private String username;
	private String password;
	
	private boolean valid;
	
	public Credentials(String username, String password) {
		this.username = username;
		this.password = password;
		this.valid = false;
	}
	
	public String getUsername() {
		return this.username;
	}

	@Override
	public void run() {
		
		// Check the credentials
		if (Game.db.getUsers().get(this.username) != null && Game.db.getUsers().get(this.username).equals(this.password)) {
			
			System.out.println(this.username);
			
			this.valid = true;
		} else {
			this.valid = false;
		}
		
	}

	public boolean isValid() {
		return valid;
	}

}