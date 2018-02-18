/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bk.controller;

import com.bk.dao.LocationDao;
import com.bk.dao.OrganizationsDao;
import com.bk.dao.PowersDao;
import com.bk.dao.SightingsDao;
import com.bk.dao.SuperDao;
import com.bk.models.Organizations;
import com.bk.models.Sightings;
import com.bk.models.Super;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author brandonkervin
 */
@RestController
@CrossOrigin
@RequestMapping("/sightings")
public class SightingController {
    
    @Autowired
    SuperDao supers;
    @Autowired
    LocationDao locations;
    @Autowired
    OrganizationsDao orgs;
    @Autowired
    PowersDao powers;
    @Autowired
    SightingsDao sightings;
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity getSightings(){
        return ResponseEntity.ok(sightings.getAllSightings());
    }
    
    @RequestMapping(value = "/sighting", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity addSighting(@RequestBody Sightings s){
        s = sightings.addSighting(s);
        return ResponseEntity.ok(s);
    }
    
    @RequestMapping(value = "/sighting", method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity editSighting(@RequestBody Sightings s){
        s = sightings.updateSighting(s);
        return ResponseEntity.ok(s);
    }
    
    @RequestMapping(value = "/sighting/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteSighting(@PathVariable("id") int id){
        sightings.deleteSighting(id);
        return ResponseEntity.ok("Sighting Deleted!");
    }
    
}
