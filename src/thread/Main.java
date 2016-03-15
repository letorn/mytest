package thread;

import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) throws Exception {
		System.out.println("start");
		Map<String, String> dataMap = new HashMap<String, String>();
		new Thread(new Runnable() {
			public void run() {
				System.out.println("thread1: start");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				System.out.println("thread1: setting");
				dataMap.put("v1", "v1");
				System.out.println("thread1: setted");
				System.out.println("dataMap: " + dataMap);
				if (dataMap.size() >= 3) {
					synchronized (dataMap) {
						dataMap.notifyAll();
					}
				}
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				System.out.println("thread2: start");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				System.out.println("thread2: setting");
				dataMap.put("v2", "v2");
				System.out.println("thread2: setted");
				System.out.println("dataMap: " + dataMap);
				if (dataMap.size() >= 3) {
					synchronized (dataMap) {
						dataMap.notifyAll();
					}
				}
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				System.out.println("thread3: start");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				System.out.println("thread3: setting");
				dataMap.put("v3", "v3");
				System.out.println("thread3: setted");
				System.out.println("dataMap: " + dataMap);
				if (dataMap.size() >= 3) {
					synchronized (dataMap) {
						dataMap.notifyAll();
					}
				}
			}
		}).start();
		synchronized (dataMap) {
			dataMap.wait();
		}
		System.out.println("dataMap: " + dataMap);
		System.out.println("stop");
	}

}
