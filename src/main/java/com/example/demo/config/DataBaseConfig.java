package com.example.demo.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
public class DataBaseConfig {

    @Bean
    @Profile("prod")
    DataSource dataSourceProd() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:postgresql://localhost:5432/coffee_machine_prod");
        dataSourceBuilder.username("postgres");
        dataSourceBuilder.password("1234567890");
        return dataSourceBuilder.build();
    }
}
