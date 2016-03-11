package list;

import java.util.LinkedList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		// final List list = new ArrayList();
		// final List list = new Vector();
		final List list = new LinkedList();
		for (int i = 0; i < 10000; i++) {
			list.add(i);
		}
		for (int i = 0; i < 10; i++)
			new Thread() {
				@Override
				public void run() {
					for (int i = 0; i < 1000; i++)
						list.remove(0);
				}
			}.start();
		for (int i = 0; i < 10; i++)
			new Thread() {
				@Override
				public void run() {
					for (int i = 0; i < 1000; i++)
						list.add(10000 * i + i);
				}
			}.start();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
		}
		System.out.println(list.size());
	}

}
