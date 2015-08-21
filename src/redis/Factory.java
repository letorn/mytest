package redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class Factory {

	public static Jedis getJedis() {
		return JedisFactory.instance;
	}

	public static Jedis getPooledJedis() {
		JedisPool jedisPool = JedisPoolFactory.instance;
		return jedisPool.getResource();
	}

	private static class JedisFactory {

		private static String HOST = "redis";
		private static int PORT = 6379;
		private static String PASSWORD = "123456";

		private static Jedis instance = new Jedis(HOST, PORT);

		static {
			// instance.auth(PASSWORD);
		}

	}

	private static class JedisPoolFactory {

		private static String HOST = "redis";
		private static int PORT = 6379;
		private static String PASSWORD = "123456";

		// default: 8, max connection, -1 means unlimited
		private static int MAX_TOTAL = 1024;
		// default: 8, max idle
		private static int MAX_IDLE = 200;
		// default: -1(never time out)
		private static int MAX_WAIT_MILLIS = 10000;
		private static int TIMEOUT = 10000;
		// whether to validate jedis when we borrow it
		private static boolean TEST_ON_BORROW = true;

		private static JedisPool instance = null;

		static {
			try {
				JedisPoolConfig config = new JedisPoolConfig();
				config.setMaxTotal(MAX_TOTAL);
				config.setMaxIdle(MAX_IDLE);
				config.setMaxWaitMillis(MAX_WAIT_MILLIS);
				config.setTestOnBorrow(TEST_ON_BORROW);
				// instance = new JedisPool(config, HOST, PORT, TIMEOUT, PASSWORD);
				instance = new JedisPool(config, HOST, PORT, TIMEOUT);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
