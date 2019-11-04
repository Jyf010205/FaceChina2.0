package com.mysonandme.configuration;

import com.mysonandme.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: jianyufeng
 * @description: redis配置类
 */
@Configuration
@EnableTransactionManagement
@Slf4j
public class RedisConfiguation {
    @Value("${redis.runMode}")
    private String runMode;

    @Value("${redis.clusterName}")
    private String clusterName;

    @Value("${redis.addr}")
    private String addr;

    @Value("${redis.auth}")
    private String auth;

    @Value("${redis.maxIdle}")
    private Integer maxIdle;

    @Value("${redis.minIdle}")
    private Integer minIdle;

    @Value("${redis.maxTotal}")
    private Integer maxTotal;

    @Value("${redis.maxWaitMillis}")
    private Integer maxWaitMillis;

    @Value("${redis.minEvictableIdleTimeMillis}")
    private Integer minEvictableIdleTimeMillis;

    @Value("${redis.numTestsPerEvictionRun}")
    private Integer numTestsPerEvictionRun;

    @Value("${redis.timeBetweenEvictionRunsMillis}")
    private long timeBetweenEvictionRunsMillis;

    @Value("${redis.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${redis.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${redis.timeout}")
    private Integer timeout;

    /**
     * 配置连接池
     * @return
     */
    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 最大空闲数
        jedisPoolConfig.setMaxIdle(maxIdle);
        // 最小空闲数
        jedisPoolConfig.setMinIdle(minIdle);
        // 连接池的最大数据库连接数
        jedisPoolConfig.setMaxTotal(maxTotal);
        // 最大建立连接等待时间
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        // 逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
        jedisPoolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        // 每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
        jedisPoolConfig.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
        // 逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        // 是否在从池中取出连接前进行检验,如果检验失败,则从池中去除连接并尝试取出另一个
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        // 在空闲时检查有效性, 默认false
        jedisPoolConfig.setTestWhileIdle(testWhileIdle);
        return jedisPoolConfig;
    }

    /**
     * 配置工厂
     */
    @Bean
    public JedisConnectionFactory JedisConnectionFactory(JedisPoolConfig jedisPoolConfig) {
        JedisClientConfiguration clientConfig = JedisClientConfiguration.builder().usePooling().poolConfig(jedisPoolConfig).and().readTimeout(Duration.ofMillis(timeout)).build();

        switch (runMode) {
            case "standard-alone":
            case "master-slave":
                // 单节点模式 & 主从模式
                log.info("Redis以单节点方式部署");
                RedisStandaloneConfiguration standaloneConfig = new RedisStandaloneConfiguration();
                String host = addr.split(":")[0];
                Integer port = Integer.parseInt(addr.split(":")[1]);
                standaloneConfig.setHostName(host);
                standaloneConfig.setPort(port);
                standaloneConfig.setPassword(auth);
                return new JedisConnectionFactory(standaloneConfig,clientConfig);
            case "sentinel":
                log.info("Redis以主从+哨兵模式部署");
                Set<String> sentinelNodeSet = new HashSet<String>(Arrays.asList(addr.split(",")));
                RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration(clusterName, sentinelNodeSet);
                sentinelConfig.setPassword(auth);
                JedisConnectionFactory sentinelFactory = new JedisConnectionFactory(sentinelConfig, clientConfig);
                sentinelFactory.afterPropertiesSet();
                return sentinelFactory;
            case "cluster":
                log.info("Redis以集群方式部署");
                Set<String> clusterNodeSet = new HashSet<String>(Arrays.asList(addr.split(",")));
                RedisClusterConfiguration clusterConfiguration = new RedisClusterConfiguration(clusterNodeSet);
                clusterConfiguration.setPassword(auth);
                JedisConnectionFactory clusterFactory = new JedisConnectionFactory(clusterConfiguration, clientConfig);
                clusterFactory.afterPropertiesSet();
                return clusterFactory;
            default:
                log.error("Redis部署方式配置错误！");
                System.exit(0);
                return null;
        }
    }

    /**
     * 实例化 RedisTemplate 对象
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> functionDomainRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        initDomainRedisTemplate(redisTemplate, redisConnectionFactory);
        return redisTemplate;
    }

    /**
     * 设置数据存入 redis 的序列化方式,并开启事务
     *
     * @param redisTemplate
     * @param factory
     */
    private void initDomainRedisTemplate(RedisTemplate<String, Object> redisTemplate, RedisConnectionFactory factory) {
        /*
         * 设置 序列化器 .
         * 如果不设置，那么在用实体类(未序列化)进行存储的时候，会提示错误: Failed to serialize object using DefaultSerializer;
         */
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        // 开启事务
        redisTemplate.setEnableTransactionSupport(true);
        // 将连接工厂设置到模板类中
        redisTemplate.setConnectionFactory(factory);
    }

    /**
     * 注入封装RedisTemplate
     */
    @Bean(name = "redisUtil")
    public RedisUtil redisUtil(RedisTemplate<String, Object> redisTemplate) {
        RedisUtil redisUtil = new RedisUtil();
        redisUtil.setRedisTemplate(redisTemplate);
        return redisUtil;
    }

}
