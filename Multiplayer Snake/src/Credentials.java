import java.util.concurrent.Callable;

public class Credentials implements Callable<Boolean>{

	//private int playerID;
	private String username;
	private String password;
	
	public Credentials(String username, String password) {
		this.username = username;
		this.password = password;
	}

	@Override
	public Boolean call() throws Exception {
		
		// Check the credentials
		if (Game.db.getUsers().get(username) != null && Game.db.getUsers().get(username).equals(password)) {
			
			System.out.println(username);
			
			return true;
		}

		return false;
		
	}

}
