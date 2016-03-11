package map;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		Map<String, String> map2 = new Hashtable<String, String>();
		Map<String, String> map3 = new ConcurrentHashMap<String, String>();
		map.put(null, null);
		
		Lock lock = new ReentrantLock();
		lock.lock();
		lock.unlock();
	}
	
}
