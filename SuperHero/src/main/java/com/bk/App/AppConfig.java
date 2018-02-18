/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bk.App;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 *
 * @author brandonkervin
 */
@Configuration
@ComponentScan("com.bk")
public class AppConfig {

    @Bean
    @Profile("production")
    public DataSource dataSource() {
        return DataSourceBuilder
                .create()
                .username("root")
                .password("root")
                .url("jdbc:mysql://localhost:3306/SuperHero")
                .driverClassName("com.mysql.jdbc.Driver")
                .build();
    }
    
    @Bean
    @Profile("production")
    public ObjectMapper objectMapper(){
        System.out.println("ASDFASDFASDFASDFASDF");
        System.out.println("ASDFASDFASDFASDFASDF");
        System.out.println("ASDFASDFASDFASDFASDF");
        System.out.println("ASDFASDFASDFASDFASDF");
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper();
    }

}
