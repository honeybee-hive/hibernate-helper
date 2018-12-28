/*
 * DataSourceConfig  1.0  2018-12-28
 *
 * 沈阳成林健康科技有限公司
 *
 */
package com.github.hibernate.helper.example.configure;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * 多数据源配置
 *
 * @author zhuyan
 * @version 1.0
 * <p>
 * 变更履历：
 * v1.0 2018-12-28 zhuyan 初版
 */
@Configuration
public class DataSourceConfig {

    @Primary
    @Bean
    @Qualifier("primaryDataSource")
    @ConfigurationProperties("spring.datasource.druid")
    public DataSource dataSourceOne() {
        return DruidDataSourceBuilder.create().build();
    }

//    @Bean
//    @ConfigurationProperties("spring.datasource.druid.second")
//    public DataSource dataSourceSecond() {
//        return DruidDataSourceBuilder.create().build();
//    }

}
