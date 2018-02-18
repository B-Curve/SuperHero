/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bk.service;

import com.bk.models.Locations;
import com.bk.models.Sightings;
import com.bk.models.Super;
import java.util.List;

/**
 *
 * @author brandonkervin
 */
public interface ServiceLayer {
    
    public List<Super> getAllSupers();
    public List<Super> getAllSuperHeroes();
    public List<Super> getAllSuperVillains();
    public Super getSuperById(int id);
    public List<Super> getSupersByName(String name);
    public List<Super> getSuperByLocationId(int id);
    public List<Super> getSuperByOranizationId(int id);
    
    
    
}
