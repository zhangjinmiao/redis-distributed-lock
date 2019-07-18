package com.fast.lock.redisson;

import java.util.concurrent.TimeUnit;
import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 〈一句话功能简述〉<br> 〈分布式锁实现基于Redisson〉
 *
 * @author zhangjinmiao
 * @create 2019/7/14 19:34
 */
public class RedissonLock {

  private static final Logger LOGGER = LoggerFactory.getLogger(RedissonLock.class);

  RedissonManager redissonManager;

  public RedissonLock() {
  }

  public RedissonLock(RedissonManager redissonManager) {
    this.redissonManager = redissonManager;
  }

  public boolean lock(String lockName, long expireSeconds) {
    RLock rLock = redissonManager.getRedisson().getLock(lockName);
    boolean getLock = false;
    try {
      getLock = rLock.tryLock(0, expireSeconds, TimeUnit.SECONDS);
      if (getLock) {
        LOGGER.info("获取Redisson分布式锁[成功],lockName={}", lockName);
      }else {
        LOGGER.info("获取Redisson分布式锁[失败],lockName={}", lockName);
      }
    } catch (InterruptedException e) {
      LOGGER.error("获取Redisson分布式锁[异常]，lockName=" + lockName, e);
      return false;
    }
    return getLock;
  }

  /**
   * 解锁
   * @param lockName
   */
  public void release(String lockName) {
    redissonManager.getRedisson().getLock(lockName).unlock();
  }

  public void setRedissonManager(RedissonManager redissonManager) {
    this.redissonManager = redissonManager;
  }
}
