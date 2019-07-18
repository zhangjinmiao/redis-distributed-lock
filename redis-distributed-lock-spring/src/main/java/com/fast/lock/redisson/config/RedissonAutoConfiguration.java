package com.fast.lock.redisson.config;

import com.fast.lock.redisson.RedissonLock;
import com.fast.lock.redisson.RedissonManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * 〈一句话功能简述〉<br> 〈Redisson自动化配置〉
 *
 * @author zhangjinmiao
 * @create 2019/7/14 18:41
 */
@Configuration
public class RedissonAutoConfiguration {

  private static final Logger LOGGER = LoggerFactory.getLogger(RedissonAutoConfiguration.class);

  @Bean
  @Order(value = 2)
  public RedissonManager redissonManager(
      RedissonProperties redissonProperties) {
    RedissonManager redissonManager = new RedissonManager(redissonProperties);
    LOGGER.info("[RedissonManager]组装完毕,当前连接方式:" + redissonProperties.getType() +
        ",连接地址:" + redissonProperties.getAddress());
    return redissonManager;
  }

  @Bean
  @Order(value = 3)
  public RedissonLock redissonLock(RedissonManager redissonManager){
    RedissonLock redissonLock = new RedissonLock();
    redissonLock.setRedissonManager(redissonManager);
    LOGGER.info("[RedissonLock]组装完毕");
    return redissonLock;
  }
}
