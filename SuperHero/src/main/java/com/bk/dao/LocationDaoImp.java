/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bk.dao;

import com.bk.models.Locations;
import com.bk.models.Sightings;
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
public class LocationDaoImp implements LocationDao {
    
    @Autowired
    private JdbcTemplate jdbc;
    
    @Override
    public List<Locations> getAllLocations(){
        return jdbc.query(Sql.ALL_LOCATIONS, new Mappers.LocationsMapper());
    }
    
    @Override
    public Locations getLocationById(int id){
        Locations l = null;
        try{
            return jdbc.queryForObject(Sql.LOCATION_BY_ID, new Mappers.LocationsMapper(), id);
        }catch(EmptyResultDataAccessException e){
            return l;
        }
    }
    
    @Override
    public List<Locations> getLocationsByAddress(String address){
        List<Locations> l = null;
        try{
            return jdbc.query(Sql.LOCATIONS_BY_ADDRESS, new Mappers.LocationsMapper(), "%"+address+"%");
        }catch(EmptyResultDataAccessException e){
            return l;
        }
    }
    
    @Override
    public List<Locations> getLocationsByLatAndLong(double latitude, double longitude){
        List<Locations> l = null;
        try{
            return jdbc.query(Sql.LOCATIONS_BY_COORDINATES, new Mappers.LocationsMapper(), 
                    latitude,
                    latitude,
                    longitude,
                    longitude);
        }catch(EmptyResultDataAccessException e){
            return l;
        }
    }

    @Override
    public List<Locations> getLocationsBySuperId(int id) {
        try{
            return jdbc.query(Sql.LOCATIONS_BY_SUPER_ID, new Mappers.LocationsMapper(), id);
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }
    
    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public Locations addLocation(Locations l){
        jdbc.update(Sql.ADD_LOCATION,
                l.getDescription(),
                l.getAddress(),
                l.getLatitude(),
                l.getLongitude());
        int id = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        l.setLocationId(id);
        return l;
    }
    
    @Override
    public Locations updateLocation(Locations l){
        jdbc.update(Sql.UPDATE_LOCATION,
                l.getDescription(),
                l.getAddress(),
                l.getLatitude(),
                l.getLongitude(),
                l.getLocationId());
        return l;
    }
    
    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public void deleteLocation(int id){
        List<Sightings> s = jdbc.query(Sql.SIGHTINGS_BY_LOCATION, new Mappers.SightingsMapper(), id);
        for(Sightings si : s){
            jdbc.update(Sql.DELETE_SUPER_SIGHTINGS_BY_S_ID, si.getSightingId());
        }
        jdbc.update(Sql.DELETE_LOCATION_SIGHTING, id);
        jdbc.update(Sql.DELETE_LOCATION, id);
    }
    
}
