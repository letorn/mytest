package profiler;

public class Main {

	static int a = 0;
	
	public static void main(String[] args) {
		
		for (int i = 0; i < 100000; i ++) {
			for (int j = 0; j < 100000; j ++) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		test();
	}
	
	public static void test() {
		for (int i = 0; i < 100000; i ++) {
			for (int j = 0; j < 100000; j ++) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				a ++;
			}
		}
		testRun();
	}
	
	public static void testRun() {
		for (int i = 0; i < 100000; i ++) {
			for (int j = 0; j < 100000; j ++) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				a --;
			}
		}
	}
	
}
