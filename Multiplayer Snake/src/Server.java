
public class Server {

	private static MapDB db;

	public Server() {
		db = new MapDB();
	}

	public boolean loginPlayer(String username, String password) {

		if (db.getUsers().get(username) != null && db.getUsers().get(username).equals(password)) {
			return true;
		}

		return false;

	}

}