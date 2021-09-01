package com.epam.esm.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@ActiveProfiles("dev")
@ComponentScan("com.epam.esm")
@PropertySource("classpath:db_postgres.properties")
public class DevDataSourceConfiguration {

    @Value("${dataSource.driverClassName}")
    private String DRIVER_CLASS_NAME_PROPERTY;
    @Value("${dataSource.url}")
    private String URL_PROPERTY;
    @Value("${dataSource.username}")
    private String USERNAME_PROPERTY;
    @Value("${dataSource.password}")
    private String PASSWORD_PROPERTY;
    @Value("${dataSource.minIdle}")
    private String MIN_IDLE_PROPERTY;
    @Value("${dataSource.maxIdle}")
    private String MAX_IDLE_PROPERTY;
    @Value("${dataSource.maxWaitMillis}")
    private String MAX_WAIT_MILLIS_PROPERTY;

    @Bean
    public BasicDataSource postgresqlDataSource() {
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName(DRIVER_CLASS_NAME_PROPERTY);
        dataSource.setUrl(URL_PROPERTY);
        dataSource.setUsername(USERNAME_PROPERTY);
        dataSource.setPassword(PASSWORD_PROPERTY);
        dataSource.setMinIdle(Integer.parseInt(MIN_IDLE_PROPERTY));
        dataSource.setMaxIdle(Integer.parseInt(MAX_IDLE_PROPERTY));
        dataSource.setMaxWaitMillis(Long.parseLong(MAX_WAIT_MILLIS_PROPERTY));

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
