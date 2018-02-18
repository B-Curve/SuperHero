/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bk.dao;

import com.bk.models.Locations;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author brandonkervin
 */
@Component
public interface LocationDao {
    
    public List<Locations> getAllLocations();
    
    public Locations getLocationById(int id);
    
    public List<Locations> getLocationsByAddress(String address);
    
    public List<Locations> getLocationsByLatAndLong(double latitude, double longitude);
    
    public List<Locations> getLocationsBySuperId(int id);
    
    public Locations addLocation(Locations l);
    
    public Locations updateLocation(Locations l);
    
    public void deleteLocation(int id);
    
}
