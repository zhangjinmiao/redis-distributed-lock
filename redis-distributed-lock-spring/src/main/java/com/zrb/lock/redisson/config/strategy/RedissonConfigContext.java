package com.zrb.lock.redisson.config.strategy;

import com.zrb.lock.redisson.config.RedissonProperties;
import org.redisson.config.Config;

/**
 * 〈一句话功能简述〉<br> 〈Redisson配置上下文，产出真正的Redisson的Config〉
 *
 * @author zhangjinmiao
 * @create 2019/7/14 19:01
 */
public class RedissonConfigContext {

  private RedissonConfigStrategy redissonConfigStrategy = null;

  public RedissonConfigContext(RedissonConfigStrategy redissonConfigStrategy) {
    this.redissonConfigStrategy = redissonConfigStrategy;
  }

  /**
   * 上下文根据构造中传入的具体策略产出真实的Redisson的Config
   * @param redissonProperties
   * @return
   */
  public Config createRedissonConfig(RedissonProperties redissonProperties) {
    return this.redissonConfigStrategy.createRedissonConfig(redissonProperties);
  }
}
