/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bk.dao;

import com.bk.models.Super;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author brandonkervin
 */
@Component
public interface SuperDao {
    
    public List<Super> getAllSupers();
    
    public List<Super> getAllSuperHeroes();
    
    public List<Super> getAllSuperVillains();
    
    public Super getSuperById(int id);
    
    public List<Super> getSupersByName(String name);
    
    public List<Super> getSupersByLocationId(int id);
    
    public List<Super> getSupersByOrganizationId(int id);
    
    public Super addSuper(Super s);
    
    public void deleteSuper(int id);
    
    public Super updateSuper(Super s);
    
    public Super removeSuperPower(int superId, int powerId);
    
    public Super removeSuperSighting(int superId, int sightingId);
    
    public Super removeSuperOrganization(int superId, int organizationId);
    
}
