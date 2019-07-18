package com.fast.lock.redisson.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Redisson分布式锁注解
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface DistributedLock {

  /**分布式锁名称 命名建议：应用名:模块名:方法名:版本号*/
  String value() default "distributed-lock-redisson";
  /**锁超时时间,默认十秒*/
  int expireSeconds() default 10;
}
