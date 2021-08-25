package com.epam.esm.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


@Configuration
@ComponentScan("com.epam.esm")
public class DbcpDataSourceConfiguration {

    public static String DRIVER_CLASS_NAME_PROPERTY = "dataSource.driverClassName";
    public static String URL_PROPERTY = "dataSource.url";
    public static String USERNAME_PROPERTY = "dataSource.username";
    public static String PASSWORD_PROPERTY = "dataSource.password";
    public static String MIN_IDLE_PROPERTY = "dataSource.minIdle";
    public static String MAX_IDLE_PROPERTY = "dataSource.maxIdle";
    public static String MAX_WAIT_MILLIS_PROPERTY = "dataSource.maxWaitMillis";

    @Bean
    public BasicDataSource postgresqlDataSource() throws ConfigurationException {
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/db.properties");
            properties.load(fileInputStream);

            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName(properties.getProperty(DRIVER_CLASS_NAME_PROPERTY));
            dataSource.setUrl(properties.getProperty(URL_PROPERTY));
            dataSource.setUsername(properties.getProperty(USERNAME_PROPERTY));
            dataSource.setPassword(properties.getProperty(PASSWORD_PROPERTY));
            dataSource.setMinIdle(Integer.parseInt(properties.getProperty(MIN_IDLE_PROPERTY)));
            dataSource.setMaxIdle(Integer.parseInt(properties.getProperty(MAX_IDLE_PROPERTY)));
            dataSource.setMaxWaitMillis(Long.parseLong(properties.getProperty(MAX_WAIT_MILLIS_PROPERTY)));

            return dataSource;
        } catch (IOException e) {
            throw new ConfigurationException(e);
        }
    }

}
