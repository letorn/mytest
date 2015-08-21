package redis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class Main {

	@Test
	public void stringTest() {
		Jedis jedis = Factory.getJedis();

		jedis.set("name", "Wito");
		System.out.println("name: " + jedis.get("name"));

		jedis.append("name", " Lee");
		System.out.println("name: " + jedis.get("name"));

		jedis.del("name");
		System.out.println("name: " + jedis.get("name"));

		jedis.mset("name", "Wito", "age", "25", "qq", "476301172");
		jedis.incr("age");
		System.out.println("name: " + jedis.get("name"));
		System.out.println("age: " + jedis.get("age"));
		System.out.println("qq: " + jedis.get("qq"));
	}

	@Test
	public void mapTest() {
		Jedis jedis = Factory.getJedis();

		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "Wito");
		map.put("age", "25");
		map.put("qq", "476301172");
		jedis.hmset("user", map);

		List<String> usgs = jedis.hmget("user", "name", "age", "qq");
		System.out.println("user: " + usgs);

		jedis.hdel("user", "qq");
		System.out.println("user.age: " + jedis.hmget("user", "age"));
		System.out.println("user.len: " + jedis.hlen("user"));
		System.out.println("user.isExist: " + jedis.exists("user"));
		System.out.println("user.keys: " + jedis.hkeys("user"));
		System.out.println("user.values: " + jedis.hvals("user"));

		Iterator<String> iterator = jedis.hkeys("user").iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			System.out.println(key + ": " + jedis.hmget("user", key));
		}
	}

	@Test
	public void listTest() {
		Jedis jedis = Factory.getJedis();

		jedis.del("ssh");
		System.out.println("ssh: " + jedis.lrange("ssh", 0, -1));

		jedis.lpush("ssh", "spring");
		jedis.lpush("ssh", "struts");
		jedis.lpush("ssh", "hibernate");
		System.out.println("ssh: " + jedis.lrange("ssh", 0, -1));

		jedis.del("ssh");
		jedis.rpush("ssh", "spring");
		jedis.rpush("ssh", "struts");
		jedis.rpush("ssh", "hibernate");
		System.out.println("ssh: " + jedis.lrange("ssh", 0, -1));
	}

	@Test
	public void setTest() {
		Jedis jedis = Factory.getJedis();

		jedis.sadd("users", "liuling");
		jedis.sadd("users", "xinxin");
		jedis.sadd("users", "ling");
		jedis.sadd("users", "zhangxinxin");
		jedis.sadd("users", "who");

		jedis.srem("users", "who");
		System.out.println("users: " + jedis.smembers("users"));
		System.out.println("who.isMenber: " + jedis.sismember("users", "who"));

		System.out.println("users: " + jedis.srandmember("users"));
		System.out.println("users.len: " + jedis.scard("users"));
	}

	@Test
	public void sortTest() {
		Jedis jedis = Factory.getJedis();

		jedis.del("ids");
		jedis.rpush("ids", "1");
		jedis.lpush("ids", "6");
		jedis.lpush("ids", "3");
		jedis.lpush("ids", "9");

		System.out.println("ids: " + jedis.lrange("ids", 0, -1));
		System.out.println("ids: " + jedis.sort("ids"));
		System.out.println("ids: " + jedis.lrange("ids", 0, -1));
	}

	@Test
	public void poolTest() {
		Jedis jedis = Factory.getPooledJedis();

		System.out.println("iedis: " + jedis);
	}

}
