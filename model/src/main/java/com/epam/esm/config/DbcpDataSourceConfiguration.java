package com.epam.esm.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.epam.esm")
public class DbcpDataSourceConfiguration {

    @Bean
    public BasicDataSource postgresqlDataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.postgresql.jdbc.driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/GiftCertificates");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");
        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(10);
        return dataSource;
    }
}
