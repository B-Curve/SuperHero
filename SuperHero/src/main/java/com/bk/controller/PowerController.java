/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bk.controller;

import com.bk.dao.PowersDao;
import com.bk.models.Powers;
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
@RequestMapping({"/powers"})
public class PowerController {
    
    @Autowired
    PowersDao powers;
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity getAllPowers(){
        return ResponseEntity.ok(powers.getAllPowers());
    }
    
    @RequestMapping(value = "/power", method = RequestMethod.POST)
    public ResponseEntity addPower(@RequestBody Powers p){
        p = powers.addPower(p);
        return ResponseEntity.ok(p);
    }
    
    @RequestMapping(value = "/power", method = RequestMethod.PUT)
    public ResponseEntity updatePower(@RequestBody Powers p){
        p = powers.updatePower(p);
        return ResponseEntity.ok(p);
    }
    
    @RequestMapping(value = "/power/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deletePower(@PathVariable("id") int id){
        powers.deletePower(id);
        return ResponseEntity.ok("Deleted.");
    }
}
