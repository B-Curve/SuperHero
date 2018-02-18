/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bk.dao;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author brandonkervin
 */
public abstract class Setup {
    
    public static void doSetup(JdbcTemplate jdbc){
        jdbc.update(Sql.REMOVE_ALL_SUPER_SIGHTINGS);
        jdbc.update(Sql.REMOVE_ALL_SUPER_SUPER_POWERS);
        jdbc.update(Sql.REMOVE_ALL_SUPER_ORGS);
        jdbc.update(Sql.REMOVE_ALL_POWERS);
        jdbc.update(Sql.REMOVE_ALL_SUPERS);
        jdbc.update(Sql.REMOVE_ALL_SIGHTINGS);
        jdbc.update(Sql.REMOVE_ALL_LOCATIONS);
        jdbc.update(Sql.REMOVE_ALL_ORGS);
        jdbc.update(Sql.RESET_SUPER_POWERS);
        jdbc.update(Sql.RESET_SUPERS);
        jdbc.update(Sql.RESET_SIGHTINGS);
        jdbc.update(Sql.RESET_ORGANIZATIONS);
        jdbc.update(Sql.RESET_LOCATIONS);
        jdbc.update(Sql.PREPARE_LOCATIONS_1);
        jdbc.update(Sql.PREPARE_LOCATIONS_2);
        jdbc.update(Sql.PREPARE_ORG_1);
        jdbc.update(Sql.PREPARE_ORG_2);
        jdbc.update(Sql.PREPARE_POWER_1);
        jdbc.update(Sql.PREPARE_POWER_2);
        jdbc.update(Sql.PREPARE_SUPER_1);
        jdbc.update(Sql.PREPARE_SUPER_2);
        jdbc.update(Sql.PREPARE_SUPER_SUPER_POWER_1);
        jdbc.update(Sql.PREPARE_SUPER_SUPER_POWER_2);
        jdbc.update(Sql.PREPARE_SIGHTINGS_1);
        jdbc.update(Sql.PREPARE_SIGHTINGS_2);
        jdbc.update(Sql.PREPARE_SUPER_SIGHT_1);
        jdbc.update(Sql.PREPARE_SUPER_SIGHT_2);
        jdbc.update(Sql.PREPARE_SUPER_ORG_1);
        jdbc.update(Sql.PREPARE_SUPER_ORG_2);
    }
    
}
