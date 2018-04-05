import org.mapdb.*;
import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.concurrent.ConcurrentNavigableMap;

public class MapDB {

	public static void main(String[] args) {
		
		// configure and open database using builder pattern. // all options are available with code auto-completion. 
		DB db = DBMaker.newFileDB(new File("testdb"))
				.closeOnJvmShutdown()
				.encryptionEnable("password")
				.make();
		
		// open existing an collection (or create new)
		ConcurrentNavigableMap<Integer, String> map = db.getTreeMap("collectionName"); // To simplfy access to the classes stored as an infostore use generics
		ConcurrentNavigableMap<String, InfoStor> mymap = db.getTreeMap("myInfoStoreCollection");
		// example of ConcurrentNavigableMap taken from
		// http://examples.javacodegeeks.com/core-java/util/concurrent/concurrentnavigablemap/java-util- // concurrent-concurrentnavigablemap-example/
		ConcurrentNavigableMap<Integer, String> navmap = db.getTreeMap("myNavmap"); navmap.put(1, "Sunday");
		navmap.put(2, "Monday");
		navmap.put(3, "Tuesday");
		navmap.put(4, "Wednesday");
		navmap.put(5, "Thursday");
		navmap.put(6, "Friday");
		navmap.put(7, "Saturday");
		System.out.println("1. descendingKeySet(): " + navmap.descendingKeySet() + "\n"); System.out.println("2. descendingMap(): " + navmap.descendingMap() + "\n"); System.out.println("3. headMap(K toKey): " + navmap.headMap(3) + "\n");
		System.out.println("4. headMap(K toKey, boolean inclusive): " + navmap.headMap(3, true) + "\n"); System.out.println("5. keySet(): " + navmap.keySet() + "\n");
		System.out.println("6. navigableKeySet(): " + navmap.navigableKeySet() + "\n"); System.out.println("7. subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive): "
				+ navmap.subMap(3, true, 6, true) + "\n");
		System.out.println("8. subMap(K fromKey, K toKey): " + navmap.subMap(3, 6) + "\n"); System.out.println("9. tailMap(K fromKey): " + navmap.tailMap(3) + "\n");
		System.out.println("10. tailMap(K fromKey, boolean inclusive): " + navmap.tailMap(3, true) + "\n");
		map.put(1, "one");
		map.put(2, "two");
		// map.keySet() is now [1,2] System.out.println(map.get(2)); db.commit(); //persist changes into disk
		map.put(3, "three");
		// map.keySet() is now [1,2,3] db.rollback(); //revert recent changes // map.keySet() is now [1,2]
		// This was taken from
		// http://stackoverflow.com/questions/12099843/storing-a-new-object-as-the-value-of-a-hashmap
		HashMap<String, InfoStor> mapper = new HashMap<String, InfoStor>(); //HashMap<String, Object> mapper = new HashMap();
		//InfoStor myInfoStore = new InfoStor("NS02");
		System.out.println("Trying");
		mymap.put("NS02", new InfoStor("NS02")); System.out.println(mymap.get("NS02").getName());
		db.close();
	}
}


// this class must be serializable
class InfoStor implements Serializable { 
	
	private static final long serialVersionUID = 1L;
	private String vmName;
	private String platform;
	private Integer memory;

	public InfoStor (String name) {
		vmName = name;
	}
	
	String getName() { 
		return vmName;
	}
	
	void setPlatform(String p) {
		platform = p;
	}
	
	String getPlatform() { 
		return platform;
	}
	
	void setMemory(Integer m) { 
		memory = m;
	}
	
	Integer getMemory() { 
		return memory;
	} 
	
}
