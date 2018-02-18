/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bk.dao;

import com.bk.models.Locations;
import com.bk.models.Sightings;
import com.bk.models.Super;
import java.time.LocalDate;
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
public class SightingsDaoImp implements SightingsDao {

    @Autowired
    JdbcTemplate jdbc;
    
    private Sightings addSupersToSighting(Sightings s){
        s.setSupers(jdbc.query(Sql.SUPERS_BY_SIGHTING_ID, new Mappers.SuperMapper(), s.getSightingId()));
        return s;
    }
    
    private Locations addLocationToSighting(Sightings s){
        return jdbc.queryForObject(Sql.LOCATION_BY_SIGHTING_ID, new Mappers.LocationsMapper(), s.getSightingId());
    }
    
    private void superUpdates(Sightings s){
        Sightings original = getSightingById(s.getSightingId());
        for(Super su : s.getSupers()){
            if(!original.getSupers().contains(su)){
                jdbc.update(Sql.ADD_SUPER_SIGHTINGS, su.getSuperId(), s.getSightingId());
            }
        }
        for(Super su : original.getSupers()){
            if(!s.getSupers().contains(su)){
                jdbc.update(Sql.DELETE_SUPER_SIGHTING_BY_BOTH, su.getSuperId(), s.getSightingId());
            }
        }
    }
    
    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public List<Sightings> getAllSightings() {
        List<Sightings> si = jdbc.query(Sql.ALL_SIGHTINGS, new Mappers.SightingsMapper());
        if(si.isEmpty()) return null;
        for(Sightings s : si){
            s = addSupersToSighting(s);
            s.setLocation(addLocationToSighting(s));
        }
        return si;
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public List<Sightings> getSightingsByLocationId(int id) {
        List<Sightings> si = jdbc.query(Sql.SIGHTINGS_BY_LOCATION, new Mappers.SightingsMapper(), id);
        if(si.isEmpty()) return null;
        for(Sightings s : si){
            s = addSupersToSighting(s);
            s.setLocation(addLocationToSighting(s));
        }
        return si;
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public List<Sightings> getSightingsByDate(LocalDate date) {
        List<Sightings> si = jdbc.query(Sql.SIGHTINGS_BY_DATE, new Mappers.SightingsMapper(), date.toString());
        if(si.isEmpty()) return null;
        for(Sightings s : si){
            s = addSupersToSighting(s);
            s.setLocation(addLocationToSighting(s));
        }
        return si;
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public Sightings getSightingById(int id) {
        try{
            Sightings s = jdbc.queryForObject(Sql.SIGHTINGS_BY_ID, new Mappers.SightingsMapper(), id);
            s.setLocation(addLocationToSighting(s));
            s = addSupersToSighting(s);
            return s;
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public Sightings addSighting(Sightings s) {
        jdbc.update(Sql.ADD_SIGHTING,
                s.getLocation().getLocationId(),
                s.getDescription(),
                s.getHeadline(),
                s.getDate().toString());
        int id = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        s.setSightingId(id);
        for(Super su : s.getSupers()){
            jdbc.update(Sql.ADD_SUPER_SIGHTINGS, su.getSuperId(), s.getSightingId());
        }
        addSupersToSighting(s);
        s.setLocation(addLocationToSighting(s));
        return s;
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public Sightings updateSighting(Sightings s) {
        superUpdates(s);
        jdbc.update(Sql.UPDATE_SIGHTING,
                s.getLocation().getLocationId(),
                s.getDescription(),
                s.getHeadline(),
                s.getDate().toString(),
                s.getSightingId());
        addSupersToSighting(s);
        s.setLocation(addLocationToSighting(s));
        return s;
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public void deleteSighting(int id) {
        //super sighting -> sighting
        jdbc.update(Sql.DELETE_SUPER_SIGHTINGS_BY_S_ID, id);
        jdbc.update(Sql.DELETE_SIGHTING, id);
    }
    
}
