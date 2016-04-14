package zookeeper;

import java.util.List;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;

public class Main {

	public static void main(String[] args) throws Exception {
		ZooKeeper zk = new ZooKeeper("127.0.0.1:2181", 10 * 10000, new Watcher() {
			public void process(WatchedEvent watchedevent) {

			}

		});

		List<String> nodes = zk.getChildren(lockRoot, true);

	}

	@Test
	public void test() {
		// ReentrantLock lock = new ReentrantLock();
		DistributedLock lock = new DistributedLock("/lock");
		lock.lock();

		System.out.println("do something...");

		lock.unlock();
	}

	@Test
	public void testThread() {
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {
				public void run() {

					DistributedLock lock = new DistributedLock("/lock");
					lock.lock();

					System.out.println("lock: " + Thread.currentThread());
					System.out.println("do something...");
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("unlock: " + Thread.currentThread());

					lock.unlock();
				}
			}).start();
		}
	}

}
