package com.dk.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @Deacription TODO
 * @Author ASUS
 * @Date 2020/12/4 19:50
 * @Version 1.0
 **/
@Configuration
public class DataSourceConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource getDruidDataSource(){
        return new DruidDataSource();
    }
}
