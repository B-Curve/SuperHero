/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bk.dao;

import com.bk.App.*;
import com.bk.dao.SuperDaoImp;
import com.bk.dao.SuperDao;
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
public class TestConfig {

    @Bean
    @Profile("test")
    public DataSource dataSource() {
        return DataSourceBuilder
                .create()
                .username("root")
                .password("root")
                .url("jdbc:mysql://localhost:3306/SuperHeroTest")
                .driverClassName("com.mysql.jdbc.Driver")
                .build();
    }

}
