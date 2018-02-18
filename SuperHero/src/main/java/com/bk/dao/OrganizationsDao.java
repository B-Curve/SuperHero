/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bk.dao;

import com.bk.models.Organizations;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author brandonkervin
 */
@Component
public interface OrganizationsDao {
    
    public List<Organizations> getAllOrganizations();
    
    public List<Organizations> getOrganizationsByName(String name);
    
    public List<Organizations> getOrganizationsBySuperId(int id);
    
    public Organizations getOrganizationById(int id);
    
    public Organizations addOrganization(Organizations o);
    
    public Organizations updateOrganization(Organizations o);
    
    public void deleteOrganization(int id);
    
}
