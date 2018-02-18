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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author brandonkervin
 */
@RestController
@CrossOrigin
@RequestMapping("/supers")
public class SuperController {
    
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
    
    @RequestMapping(value = "/supers/{type}", method = RequestMethod.GET)
    public List<Super> getAllSupers(@PathVariable("type") String type){
        switch(type){
            case "all":
                return supers.getAllSupers();
            case "heroes":
                return supers.getAllSuperHeroes();
            case "villains":
                return supers.getAllSuperVillains();
            default:
                return null;
        }
    }
    
    @RequestMapping(value = "/super/{value}", method = RequestMethod.GET)
    public ResponseEntity getSupersByName(@PathVariable("value") String value){
        List<Super> s = supers.getSupersByName(value);
        if(s == null){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("No supers found by given name.");
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(s);
    }
    
    @RequestMapping(value = "/super/{superId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteSuper(@PathVariable("superId") int superId){
        supers.deleteSuper(superId);
        return ResponseEntity.status(HttpStatus.OK).body("Super Deleted.");
    }
    
    @RequestMapping(value = "/super", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
    public @ResponseBody ResponseEntity addSuper(@RequestBody Super s){
        s = supers.addSuper(s);
        return ResponseEntity.ok().body(s);
    }
    
    @RequestMapping(value = "/super/power", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity addPowerToSuper(@RequestBody int[] message){
        int superId = message[0];
        int powerId = message[1];
        Super s = supers.getSuperById(superId);
        s.getPowers().add(powers.getPowerById(powerId));
        s = supers.updateSuper(s);
        return ResponseEntity.ok(null);
    }
    
    @RequestMapping(value = "/super/{superId}/power/{powerId}", method = RequestMethod.DELETE)
    public ResponseEntity removePowerFromSuper(@PathVariable("superId") int superId, 
            @PathVariable("powerId") int powerId){
        Super s = supers.removeSuperPower(superId, powerId);
        return ResponseEntity.ok(s.getPowers());
    }
    
    @RequestMapping(value = "/powers/all", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity getPowers(){
        return ResponseEntity.ok(powers.getAllPowers());
    }
    
    @RequestMapping(value = "/organizations/all", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity getOrganizations(){
        return ResponseEntity.ok(orgs.getAllOrganizations());
    }
    
    @RequestMapping(value = "/sightings/all", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity getSightings(){
        return ResponseEntity.ok(sightings.getAllSightings());
    }
    
    @RequestMapping(value = "/super/organization", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity addOrganizationToSuper(@RequestBody int[] message){
        int superId = message[0];
        int orgId = message[1];
        Super s = supers.getSuperById(superId);
        Organizations o = new Organizations();
        o.setOrganizationId(orgId);
        s.getOrganizations().add(o);
        s = supers.updateSuper(s);
        for(Organizations org : s.getOrganizations()){
            if(org.getOrganizationId() == orgId) return ResponseEntity.ok(org);
        }
        return ResponseEntity.status(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS).body("Unable to process request.");
    }
    
    @RequestMapping(value = "/super/{superId}/organization/{organizationId}", method = RequestMethod.DELETE)
    public ResponseEntity removeOrganizationFromSuper(@PathVariable("superId") int superId,
            @PathVariable("organizationId") int organizationId){
        Super s = supers.removeSuperOrganization(superId, organizationId);
        return ResponseEntity.ok(s.getOrganizations());
    }
    
    @RequestMapping(value = "/super/sighting", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity addSightingToSuper(@RequestBody int[] message){
        int superId = message[0];
        int sightingId = message[1];
        Super s = supers.getSuperById(superId);
        Sightings si = new Sightings();
        si.setSightingId(sightingId);
        s.getSightings().add(si);
        s = supers.updateSuper(s);
        for(Sightings sight : s.getSightings()){
            if(sight.getSightingId() == sightingId) return ResponseEntity.ok(sightings.getSightingById(sightingId));
        }
        return ResponseEntity.status(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS).body("Unable to process request.");
    }
    
    @RequestMapping(value = "/super/{superId}/sighting/{sightingId}", method = RequestMethod.DELETE)
    public ResponseEntity removeSightingFromSuper(@PathVariable("superId") int superId,
            @PathVariable("sightingId") int sightingId){
        Super s = supers.removeSuperSighting(superId, sightingId);
        return ResponseEntity.ok(s.getSightings());
    }
    
}
