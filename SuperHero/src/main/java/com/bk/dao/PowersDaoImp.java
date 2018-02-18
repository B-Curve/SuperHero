/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bk.dao;

import com.bk.models.Powers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author brandonkervin
 */
@Component
public class PowersDaoImp implements PowersDao {

    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    public List<Powers> getAllPowers() {
        List<Powers> p = jdbc.query(Sql.ALL_POWERS, new Mappers.PowersMapper());
        return p.isEmpty() ? null : p;
    }

    @Override
    public List<Powers> getPowersByName(String name) {
        List<Powers> p = jdbc.query(Sql.POWERS_BY_NAME, new Mappers.PowersMapper(), "%"+name+"%");
        return p.isEmpty() ? null : p;
    }

    @Override
    public Powers getPowerById(int id) {
        try{
            return jdbc.queryForObject(Sql.POWERS_BY_ID, new Mappers.PowersMapper(), id);
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public Powers addPower(Powers p) {
        jdbc.update(Sql.ADD_POWER,
                p.getTitle(),
                p.getDescription());
        int id = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        p.setSuperPowerId(id);
        return p;
    }

    @Override
    public Powers updatePower(Powers p) {
        jdbc.update(Sql.UPDATE_POWER,
                p.getTitle(),
                p.getDescription(),
                p.getSuperPowerId());
        return p;
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public void deletePower(int id) {
        jdbc.update(Sql.DELETE_SUPER_SUPER_POWER_BY_P_ID, id);
        jdbc.update(Sql.DELETE_POWER, id);
    }
    
}
