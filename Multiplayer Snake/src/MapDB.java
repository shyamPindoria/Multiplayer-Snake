import org.mapdb.*;
import java.io.File;
import java.util.concurrent.ConcurrentNavigableMap;

public class MapDB {

	private DB db;
	private ConcurrentNavigableMap<String, String> users;

	public MapDB() {

		// configure and open database using builder pattern. // all options are
		// available with code auto-completion.
		db = DBMaker.newFileDB(new File("usersdb")).closeOnJvmShutdown().encryptionEnable("password").make();

		// open existing an collection (or create new)
		users = db.getTreeMap("users");
	}

	public ConcurrentNavigableMap<String, String> getUsers() {
		return users;
	}

	public void setUsers(ConcurrentNavigableMap<String, String> users) {
		this.users = users;
	}
}
