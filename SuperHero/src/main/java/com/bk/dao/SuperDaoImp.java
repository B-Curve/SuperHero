/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bk.dao;

import com.bk.models.Locations;
import com.bk.models.Organizations;
import com.bk.models.Powers;
import com.bk.models.Sightings;
import com.bk.models.Super;
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
public class SuperDaoImp implements SuperDao {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public void buildBridges(Super s){
        for(Powers p : s.getPowers()){
            jdbcTemplate.update(Sql.ADD_SUPER_SUPER_POWERS, s.getSuperId(), p.getSuperPowerId());
        }
        for(Organizations o : s.getOrganizations()){
            jdbcTemplate.update(Sql.ADD_SUPER_ORGANIZATION, s.getSuperId(), o.getOrganizationId());
        }
        for(Sightings si : s.getSightings()){
            jdbcTemplate.update(Sql.ADD_SUPER_SIGHTINGS, s.getSuperId(), si.getSightingId());
        }
    }
    
    public Super buildSuper(Super s){
        List<Organizations> orgs = 
                jdbcTemplate.query(Sql.ALL_ORGANIZATIONS_BY_SUPER, new Mappers.OrgsMapper(), s.getSuperId());
        List<Sightings> sightings = 
                jdbcTemplate.query(Sql.ALL_SIGHTINGS_BY_SUPER, new Mappers.SightingsMapper(), s.getSuperId());
        List<Powers> p =
                jdbcTemplate.query(Sql.ALL_POWERS_BY_SUPER, new Mappers.PowersMapper(), s.getSuperId());
        s.setOrganizations(orgs);
        s.setSightings(sightings);
        s.setPowers(p);
        return s;
    }
    
    @Override
    public List<Super> getAllSupers(){
        List<Super> supers = jdbcTemplate.query(Sql.ALL_SUPERS, new Mappers.SuperMapper());
        for(Super s : supers) s = buildSuper(s);
        return supers;
    }
    
    @Override
    public List<Super> getAllSuperHeroes(){
        List<Super> supers = jdbcTemplate.query(Sql.ALL_HEROES, new Mappers.SuperMapper());
        for(Super s : supers) s = buildSuper(s);
        return supers;
    }
    
    @Override
    public List<Super> getAllSuperVillains(){
        List<Super> supers = jdbcTemplate.query(Sql.ALL_VILLAINS, new Mappers.SuperMapper());
        for(Super s : supers) s = buildSuper(s);
        return supers;
    }
    
    @Override
    public Super getSuperById(int id){
        Super s;
        try{
            s = jdbcTemplate.queryForObject(Sql.SUPER_BY_ID, new Mappers.SuperMapper(), id);
        }catch(EmptyResultDataAccessException e){
            return null;
        }
        s = buildSuper(s);
        return s;
    }
    
    @Override
    public List<Super> getSupersByName(String name){
        List<Super> supers = jdbcTemplate.query(Sql.SUPERS_BY_NAME, new Mappers.SuperMapper(), "%"+name+"%");
        for(Super s : supers) s = buildSuper(s);
        return supers;
    }
    
    @Override
    public List<Super> getSupersByLocationId(int id){
        List<Super> supers = jdbcTemplate.query(Sql.SUPERS_BY_SIGHTING, new Mappers.SuperMapper(), id);
        for(Super s : supers) s = buildSuper(s);
        return supers;
    }
    
    @Override
    public List<Super> getSupersByOrganizationId(int id){
        List<Super> supers = jdbcTemplate.query(Sql.SUPERS_BY_ORGANIZATION_ID, new Mappers.SuperMapper(), id);
        for(Super s : supers) s = buildSuper(s);
        return supers;
    }
    
    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public Super addSuper(Super s){
        jdbcTemplate.update(Sql.NEW_SUPER,
                s.getName(),
                s.getDescription(),
                s.getType().equals("Hero") ? 1 : 2);
        int id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        s.setSuperId(id);
        buildBridges(s);
        return s;
    }
    
    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public Super updateSuper(Super s){
        jdbcTemplate.update(Sql.UPDATE_SUPER,
                s.getName(),
                s.getDescription(),
                s.getType().equals("Hero") ? 1 : 2,
                s.getSuperId());
        buildBridges(s);
        return s;
    }
    
    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public void deleteSuper(int id){
        jdbcTemplate.update(Sql.DELETE_SUPER_SUPER_POWERS, id);
        jdbcTemplate.update(Sql.DELETE_SUPER_SIGHTINGS, id);
        jdbcTemplate.update(Sql.DELETE_SUPER_ORGANIZATION, id);
        jdbcTemplate.update(Sql.DELETE_SUPER, id);
    }
    
    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public Super removeSuperPower(int superId, int powerId){
        jdbcTemplate.update(Sql.DELETE_SUPER_SUPER_POWER_BY_BOTH, superId, powerId);
        return getSuperById(superId);
    }
    
    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public Super removeSuperOrganization(int superId, int orgId){
        jdbcTemplate.update(Sql.DELETE_SUPER_ORGANIZATION_BY_BOTH, superId, orgId);
        return getSuperById(superId);
    }
    
    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public Super removeSuperSighting(int superId, int sightingId){
        jdbcTemplate.update(Sql.DELETE_SUPER_SIGHTING_BY_BOTH, superId, sightingId);
        return getSuperById(superId);
    }
    
}
