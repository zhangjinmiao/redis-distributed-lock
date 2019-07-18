package com.fast.lock.redisson.config.strategy;

import com.fast.lock.redisson.config.RedissonProperties;
import org.redisson.config.Config;

/**
 * 〈一句话功能简述〉<br> 〈Redisson配置构建接口〉
 *
 * @author zhangjinmiao
 * @create 2019/7/14 19:00
 */
public interface RedissonConfigStrategy {

  /**
   * 根据不同的Redis配置策略创建对应的Config
   * @param redissonProperties
   * @return Config
   */
  Config createRedissonConfig(RedissonProperties redissonProperties);
}
