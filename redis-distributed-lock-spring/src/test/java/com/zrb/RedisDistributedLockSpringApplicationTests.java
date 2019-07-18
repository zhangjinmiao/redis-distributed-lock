package com.zrb;

import com.zrb.lock.redisson.config.RedissonProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisDistributedLockSpringApplicationTests {

	@Autowired
	private RedissonProperties redissonProperties;

	@Test
	public void contextLoads() {

		System.out.println(redissonProperties.getAddress());
	}

}
