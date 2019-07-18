package com.zrb.lock.redisson.config.strategy;

import com.zrb.lock.redisson.config.RedissonProperties;
import com.zrb.lock.redisson.constant.GlobalConstant;
import org.apache.commons.lang3.StringUtils;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 〈一句话功能简述〉<br> 〈哨兵集群方式Redis连接配置〉
 *
 * @author zhangjinmiao
 * @create 2019/7/15 17:36
 */
public class SentinelRedissonConfigStrategyImpl implements RedissonConfigStrategy{

  private static final Logger LOGGER = LoggerFactory.getLogger(SentinelRedissonConfigStrategyImpl.class);


  @Override
  public Config createRedissonConfig(RedissonProperties redissonProperties) {
    Config config = new Config();
    try {
      String address = redissonProperties.getAddress();
      String password = redissonProperties.getPassword();
      int database = redissonProperties.getDatabase();
      String[] addrTokens = address.split(",");
      String sentinelAliasName = addrTokens[0];
      /**设置redis配置文件sentinel.conf配置的sentinel别名*/
      config.useSentinelServers().setMasterName(sentinelAliasName);
      config.useSentinelServers().setDatabase(database);
      if (StringUtils.isNotBlank(password)) {
        config.useSentinelServers().setPassword(password);
      }
      /**设置sentinel节点的服务IP和端口*/
      for (int i = 1; i < addrTokens.length; i++) {
        config.useSentinelServers().addSentinelAddress(
            GlobalConstant.REDIS_CONNECTION_PREFIX.getConstant_value() + addrTokens[i]);
      }
      LOGGER.info("初始化[sentinel]方式Config,redisAddress:[{}]", address);
    }catch (Exception e) {
      LOGGER.error("sentinel Redisson init error", e);
    }
    return config;
  }
}
