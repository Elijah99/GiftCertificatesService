package com.epam.esm.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("com.epam.esm")
@EnableTransactionManagement
@Profile("none")
@PropertySource("classpath:db_hsqldb.properties")
public class TestDataSourceConfiguration {


    @Bean
    public BasicDataSource hsqldbDataSource() {
        BasicDataSource dataSource = new BasicDataSource();

        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(BasicDataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public PlatformTransactionManager txManager(BasicDataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}

