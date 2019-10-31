# 基于redisson 的分布式锁实现

本项目是spring-boot-starter类的类库，springboot 项目可直接使用。

## 使用步骤

1. 引入依赖

```xml
<dependency>
  <groupId>com.fast</groupId>
  <artifactId>redis-distributed-lock</artifactId>
  <version>0.0.1-SNAPSHOT</version>
</dependency>
```
2. 配置文件 (properties同理)
```yml
# redisson 分布式锁配置
redisson:
  lock:
    server:
      address: 127.0.0.1:6379
      password: 12345
      database: 1
      type: standalone
```

3.在启动类添加注解，@EnableRedissonLock 打开Redisson分布式锁自动装配
```java
@EnableScheduling
@EnableRedissonLock
@SpringBootApplication
public class AccountServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountServerApplication.class, args);
	}
}
```

4. 支持注解和 java 编程方式调用

**直接编程方式**
在需要使用的类中，注入实体 RedissonLock，即可进行加锁、解锁等操作。锁自动
释放时间默认 10 秒，可自行定义。
```
  @Autowired
  RedissonLock redissonLock;

  @Scheduled(cron = "${site.redis.lock.cron}")
  public void execute() throws InterruptedException {
    if (redissonLock.lock("redisson", 10)) {
      LOGGER.info("[ExecutorRedisson]--执行定时任务开始，休眠三秒");
      Thread.sleep(3000);
      System.out.println("=======================业务逻辑=============================");
      LOGGER.info("[ExecutorRedisson]--执行定时任务结束，休眠三秒");
      redissonLock.release("redisson");
    } else {
      LOGGER.info("[ExecutorRedisson]获取锁失败");
    }
  }
```


**注解方式**
在需要加锁的定时任务的执行方法头部，添加 @DistributedLock(value = “redis-lock”, expireSeconds = 11) 即可进行加锁、解锁等操作（value表示锁在redis中存放的key值，expireSeconds表示加锁时间）。锁自动释放时间默认为10秒。
```
@Scheduled(cron = "${site.redis.lock.cron}")
    @DistributedLock(value = "redis-lock", expireSeconds = 11)
    public void execute() throws InterruptedException {
        LOGGER.info("[ExecutorRedisson]--执行定时任务开始，休眠三秒");
        Thread.sleep(3000);
        System.out.println("=======================业务逻辑=============================");
        LOGGER.info("[ExecutorRedisson]--执行定时任务结束，休眠三秒");
    }
```
注意：加锁的时间要大于业务执行时间，这个时间需要通过测试算出最合适的值，否则会造成加锁失败或者业务执行效率过慢等问题。

>参考：
[自己写分布式锁--基于redission](http://wuwenliang.net/2018/12/07/%E8%87%AA%E5%B7%B1%E5%86%99%E5%88%86%E5%B8%83%E5%BC%8F%E9%94%81-%E5%9F%BA%E4%BA%8Eredission/)