package thread;

public class Main2 {

	public static Integer num = -1;

	public static void main(String[] args) throws Exception {
		System.out.println("start");
		new Thread(new Runnable() {
			public void run() {
				synchronized (num) {
					System.out.println("num1:" + num);
					System.out.println("thread1: start");
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				synchronized (num) {
					System.out.println("num2:" + num);
					System.out.println("thread2: start");
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				num = 2;
				System.out.println("num3:" + num);
				System.out.println("thread3: start");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}).start();
		System.out.println("stop");
	}

}
