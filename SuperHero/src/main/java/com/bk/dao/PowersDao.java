/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bk.dao;

import com.bk.models.Powers;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author brandonkervin
 */
@Component
public interface PowersDao {
    
    public List<Powers> getAllPowers();
    
    public List<Powers> getPowersByName(String name);
    
    public Powers getPowerById(int id);
    
    public Powers addPower(Powers p);
    
    public Powers updatePower(Powers p);
    
    public void deletePower(int id);
    
}
