package com.sriharilabs;

import java.nio.charset.Charset;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableCaching
@Slf4j
public class CacheConfig extends CachingConfigurerSupport {

//    @Bean
//    JedisConnectionFactory jedisConnectionFactory() {
//        JedisConnectionFactory jedisConFactory
//                = new JedisConnectionFactory();
//        jedisConFactory.setHostName("192.168.64.2");
//        jedisConFactory.setPort(9001);
//        return jedisConFactory;
//    }
    
	JedisConnectionFactory jedisConnectionFactory() {
	    RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration("localhost", 6379);
	    //redisStandaloneConfiguration.setPassword(RedisPassword.of("yourRedisPasswordIfAny"));
	    
	    return new JedisConnectionFactory(redisStandaloneConfiguration);
	}

//	@Bean(name = "redisTemplate")
//    public RedisTemplate<String, String> redisTemplate() {
//        final RedisTemplate<String, String> template = new RedisTemplate<String, String>();
//        template.setConnectionFactory(jedisConnectionFactory());
//        
//        template.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
//        return template;
//    }
//    @Bean
//    public CacheManager cacheManager(RedisTemplate redisTemplate) {
//    	final RedisTemplate<String, String> template = new RedisTemplate<String, String>();
//    	//RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);
//       RedisCacheManager cacheManager = RedisCacheManager.create(template);
//        return cacheManager;
//    }
    
	

//	
//    @Bean
//    public CacheManager cacheManager() {
//        RedisCacheManager cacheManager = RedisCacheManager.create(jedisConnectionFactory());
//        
//        org.springframework.data.redis.cache.RedisCacheConfiguration config = org.springframework.data.redis.cache.RedisCacheConfiguration
//    			.defaultCacheConfig();
//
//        RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
//        config.disableCachingNullValues();
//        config = config.serializeValuesWith(SerializationPair
//    			.fromSerializer(serializer));
//        
//        return cacheManager;
//    }
//    
//    @Bean
//    public RedisCacheConfiguration redisCacheConfiguration() {
//    org.springframework.data.redis.cache.RedisCacheConfiguration config = org.springframework.data.redis.cache.RedisCacheConfiguration
//			.defaultCacheConfig();
//    RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
//	config = config.serializeValuesWith(SerializationPair
//			.fromSerializer(serializer));
//    
//    return config;
//    
//    }
//    @Bean
//    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory,
//        ResourceLoader resourceLoader) {
//      RedisCacheManagerBuilder builder = RedisCacheManager
//          .builder(redisConnectionFactory)
//          .cacheDefaults(redisCacheConfiguration());
//     
//      List<String> cacheNames = this.cacheProperties.getCacheNames();
//      if (!cacheNames.isEmpty()) {
//        builder.initialCacheNames(new LinkedHashSet<>(cacheNames));
//      }
//      return this.customizerInvoker.customize(builder.build());
//      
//    }
    
    
    ///IMP
//    @Bean 
//    public StringRedisSerializer stringRedisSerializer() { 
//        return new StringRedisSerializer(Charset.forName("UTF-8")); 
//    } 
//    @Bean 
//    public RedisTemplate redisTemplate() { 
//        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>(); 
//        template.setConnectionFactory(jedisConnectionFactory()); 
//        template.setKeySerializer(stringRedisSerializer()); 
//        template.setHashKeySerializer(stringRedisSerializer()); 
//        template.afterPropertiesSet(); 
//        log.info("create RedisTemplate!!!"); 
//        return template; 
//    } 
 
//    @Bean
//    public CacheManager cacheManager(RedisTemplate redisTemplate) {
//      RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
//      cacheManager.setDefaultExpiration(3000);
//      return cacheManager;
//    }
    
//    @Bean 
//    public RedisCacheManager redisCacheManager() { 
//        log.info("create RedisCacheManager..."); 
//        return new RedisCacheManager(redisTemplate()); 
//    } 
    
    
   
	public RedisCacheManager getRedisCacheManager(RedisConnectionFactory connectionFactory){
		RedisCacheWriter cacheWriter = RedisCacheWriter.lockingRedisCacheWriter(connectionFactory);
		ClassLoader loader = this.getClass().getClassLoader();
		JdkSerializationRedisSerializer jdkSerializer = new JdkSerializationRedisSerializer(loader);
		SerializationPair<Object> pair = SerializationPair.fromSerializer(jdkSerializer);
		
		RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair);
		cacheConfig = cacheConfig.entryTtl(Duration.ofSeconds(3600));



		Map<String, RedisCacheConfiguration> initialCacheConfigurations = new HashMap<>();
		initialCacheConfigurations.put("user",cacheConfig.entryTtl(Duration.ofSeconds(60)));
		
		
		RedisCacheManager cacheManager = new RedisCacheManager(cacheWriter, cacheConfig,initialCacheConfigurations);
		
		return cacheManager;
	}
	 @Bean
    public CacheManager cacheManager() {
    	RedisCacheManager cacheManager=getRedisCacheManager(jedisConnectionFactory());
    	return cacheManager;
    }
}
