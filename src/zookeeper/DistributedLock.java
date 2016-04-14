package zookeeper;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class DistributedLock implements Watcher {

	private final String zookeeperAddr = "127.0.0.1:2181";
	private final String lockNode = "lock";
	private final String lockRoot;
	private final String lockPath;
	private ZooKeeper zookeeper;
	private String currentPath;
	private Boolean mutex = false;
	private CountDownLatch latch;// 计数器

	/**
	 * 
	 * @param root 锁路径
	 */
	public DistributedLock(String lockRoot) {
		this.lockRoot = lockRoot;
		this.lockPath = lockRoot + "/lock";
		try {
			zookeeper = new ZooKeeper(zookeeperAddr, 10 * 10000, this);
			Stat rootStat = zookeeper.exists(lockRoot, false);
			if (rootStat == null)
				zookeeper.create(lockRoot, lockRoot.getBytes("UTF-8"), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void lock() {
		try {
			currentPath = zookeeper.create(lockPath, lockPath.getBytes("UTF-8"), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
			String currentNode = currentPath.substring(lockNode.length() + 2);
			while (true) {
				List<String> nodes = zookeeper.getChildren(lockRoot, true);
				Collections.sort(nodes);
				if (currentNode.equals(nodes.get(0))) {
					break;
				} else {
					synchronized (mutex) {
						mutex.wait();
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void unlock() {
		try {
			zookeeper.delete(currentPath, -1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void process(WatchedEvent watchedEvent) {
		synchronized (mutex) {
			mutex.notify();
		}
	}

}
