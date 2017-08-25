package com.amwell.config;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {
	
	private static Logger logger = Logger.getLogger(RedisConfig.class);

	@Value("${spring.redis.database}")
	private int database;

	@Value("${spring.redis.host}")
	private String host;

	@Value("${spring.redis.port}")
	private int port;

	@Value("${spring.redis.password}")
	private String password;

	@Value("${spring.redis.timeout}")
	private int timeout;

	@Value("${spring.redis.pool.min-idle}")
	private int minIdle;

	@Value("${spring.redis.pool.max-idle}")
	private int maxIdle;

	@Value("${spring.redis.pool.max-wait}")
	private long maxWaitMillis;
	
	@Value("${spring.redis.clientname}")
	private String clientName;

	@Bean
	public JedisPool redisPoolFactory() {
		logger.info("JedisPool注入成功！！");
		logger.info("redis地址：" + host);
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMinIdle(minIdle);
		jedisPoolConfig.setMaxIdle(maxIdle);
		jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
		JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, timeout, password, database, clientName,
				false, null, null, null);
		return jedisPool;
	}
	
	@Bean  
    public JedisPoolConfig getRedisConfig(){  
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);
        config.setMaxWaitMillis(maxWaitMillis);
        return config;  
    } 
	
	@Bean  
    public JedisConnectionFactory getConnectionFactory(){  
        JedisConnectionFactory factory = new JedisConnectionFactory();  
        JedisPoolConfig jedisPoolConfig = getRedisConfig();
        factory.setPoolConfig(jedisPoolConfig);
        factory.setPort(port);
        factory.setHostName(host);
        factory.setTimeout(timeout);
        factory.setTimeout(timeout);
        factory.setPassword(password);
        factory.setDatabase(database);
        factory.setClientName(clientName);
        factory.setUseSsl(false);
        logger.info("JedisConnectionFactory bean init success.");  
        return factory;  
    }
	
	@Bean  
    public RedisTemplate<?, ?> getRedisTemplate(){  
        RedisTemplate<?,?> template = new StringRedisTemplate(getConnectionFactory());
        return template;  
    }
	

}
