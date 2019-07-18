package com.zrb.lock.redisson.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

/**
 * 〈一句话功能简述〉<br> 〈Redisson配置映射类〉
 *
 * @author zhangjinmiao
 * @create 2019/7/14 18:44
 */
@Component
@Order(value = 1)
public class RedissonProperties {

  private static final Logger LOGGER = LoggerFactory.getLogger(RedissonProperties.class);

  /**redis主机地址，ip：port，有多个用半角逗号分隔*/
  private String address;
  /**连接类型，支持standalone-单机节点，sentinel-哨兵，cluster-集群，masterslave-主从*/
  private String type;
  /**redis连接密码*/
  private String password;
  /**选取那个数据库*/
  private int database;

  @Autowired
  private Environment evn;

  private Properties  pro = new Properties() ;

  @PostConstruct
  public void  init(){
    try {
      pro.load(new FileInputStream(ResourceUtils.getFile("classpath:redis-config.properties")));
      address = pro.getProperty("redisson.lock.server.address");
      type = pro.getProperty("redisson.lock.server.type");
      password = pro.getProperty("redisson.lock.server.password");
      database = pro.getProperty("redisson.lock.server.database") == null ? 0 : Integer.parseInt(pro.getProperty("redisson.lock.server.database"));
    } catch (IOException e) {
      LOGGER.error("加载redisson配置文件异常", e);
    }
  }

  public String get(String key){
    if (pro.containsKey(key)){
      return pro.getProperty(key);
    }else if(evn.containsProperty(key)){
      return  evn.getProperty(key);
    }else {
      return  "";
    }
  }


  public String getAddress() {
    return address;
  }

  public String getType() {
    return type;
  }

  public String getPassword() {
    return password;
  }

  public int getDatabase() {
    return database;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setDatabase(int database) {
    this.database = database;
  }
}
