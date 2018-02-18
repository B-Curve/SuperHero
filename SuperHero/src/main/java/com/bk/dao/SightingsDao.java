/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bk.dao;

import com.bk.models.Sightings;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author brandonkervin
 */
@Component
public interface SightingsDao {
    
    public List<Sightings> getAllSightings();
    
    public List<Sightings> getSightingsByLocationId(int id);
    
    public List<Sightings> getSightingsByDate(LocalDate date);
    
    public Sightings getSightingById(int id);
    
    public Sightings addSighting(Sightings s);
    
    public Sightings updateSighting(Sightings s);
    
    public void deleteSighting(int id);
    
}
