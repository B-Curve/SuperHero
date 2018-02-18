/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bk.controller;

import com.bk.dao.OrganizationsDao;
import com.bk.models.Organizations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author brandonkervin
 */
@RestController
@CrossOrigin
@RequestMapping({"/organizations"})
public class OrganizationController {
    
    @Autowired
    OrganizationsDao orgs;
    
    @RequestMapping(value = "/org", method = RequestMethod.POST)
    public ResponseEntity addOrganization(@RequestBody Organizations o){
        orgs.addOrganization(o);
        return ResponseEntity.ok(o);
    }
    
    @RequestMapping(value = "/org", method = RequestMethod.PUT)
    public ResponseEntity updateOrganization(@RequestBody Organizations o){
        orgs.updateOrganization(o);
        return ResponseEntity.ok(o);
    }
    
    @RequestMapping(value = "/org/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteOrganization(@PathVariable("id") int id){
        orgs.deleteOrganization(id);
        return ResponseEntity.ok("Deleted.");
    }
    
}
