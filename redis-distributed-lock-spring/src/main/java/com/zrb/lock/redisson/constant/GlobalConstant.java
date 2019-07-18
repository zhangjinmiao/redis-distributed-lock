package com.zrb.lock.redisson.constant;

/**
 * 〈一句话功能简述〉<br> 〈全局常量枚举〉
 *
 * @author zhangjinmiao
 * @create 2019/7/14 19:14
 */
public enum GlobalConstant {

  REDIS_CONNECTION_PREFIX("redis://", "Redis地址配置前缀");

  private final String constant_value;
  private final String constant_desc;

  private GlobalConstant(String constant_value, String constant_desc) {
    this.constant_value = constant_value;
    this.constant_desc = constant_desc;
  }

  public String getConstant_value() {
    return constant_value;
  }

  public String getConstant_desc() {
    return constant_desc;
  }
}
