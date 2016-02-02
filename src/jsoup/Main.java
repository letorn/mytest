package jsoup;

import java.io.ByteArrayInputStream;

import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.junit.Test;

public class Main {

	public static void main(String[] args) throws Exception {
		String url = "http://localhost:8080/springmvc/dasyn.do";
		Response response = Jsoup.connect(url).method(Method.POST).data("username", "admin", "password", "admin").ignoreContentType(true).execute();
		String status = response.header("DaSyn-Status");
		System.out.println(status);
	}
	
	@Test
	public void dasyn() throws Exception {
		String url = "http://localhost:8885/zcdh-dasyn-web/ent/findEntInfo";
		String body = "aaa";
		Response response = Jsoup.connect(url).method(Method.POST).data("dasyn", "get", new ByteArrayInputStream(body.getBytes("UTF-8"))).ignoreContentType(true).execute();
		// Response response = Jsoup.connect(url).method(Method.GET).ignoreContentType(true).execute();
		String res = response.body();
		System.out.println(res);
	}
	
	@Test
	public void testCarte() throws Exception {
		String url = "http://localhost:8088/kettle/hb";
		Response response = Jsoup.connect(url).method(Method.POST).ignoreContentType(true).execute();
		// Response response = Jsoup.connect(url).method(Method.GET).ignoreContentType(true).execute();
		String res = response.body();
		System.out.println(res);
	}
	
}
