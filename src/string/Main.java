package string;

public class Main {

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder(); // new StringBuffer();
		// StringBuffer sb = new StringBuffer();

        new Thread() {    //A
            public void run() {
                int i = 0;
                while (i++ < 10) {
                    sb.append("aaaaa");
                    System.out.println(sb);
                }
            }
        }.start();
        new Thread() {    //B
            public void run() {
                int i = 0;
                while (i++ < 10) {
                    sb.append("bbbbb");
                    System.out.println(sb);
                }
            }
        }.start();
	}
	
}
