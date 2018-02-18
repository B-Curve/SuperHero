/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bk.controller;

import com.bk.dao.LocationDao;
import com.bk.models.Locations;
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
@RequestMapping({"/locations"})
public class LocationController {
    
    @Autowired
    LocationDao locations;
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity getAllLocations(){
        return ResponseEntity.ok(locations.getAllLocations());
    }
    
    @RequestMapping(value = "/location", method = RequestMethod.POST)
    public ResponseEntity addLocation(@RequestBody Locations l){
        l = locations.addLocation(l);
        return ResponseEntity.ok(l);
    }
    
    @RequestMapping(value = "/location", method = RequestMethod.PUT)
    public ResponseEntity updateLocation(@RequestBody Locations l){
        l = locations.updateLocation(l);
        return ResponseEntity.ok(l);
    }
    
    @RequestMapping(value = "/location/{id}", method = RequestMethod.DELETE)
    public ResponseEntity removeLocation(@PathVariable("id") int id){
        locations.deleteLocation(id);
        return ResponseEntity.ok().body("Deleted");
    }
    
}
