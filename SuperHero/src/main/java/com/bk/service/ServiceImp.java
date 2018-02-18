/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bk.service;

import com.bk.dao.LocationDao;
import com.bk.dao.OrganizationsDao;
import com.bk.dao.PowersDao;
import com.bk.dao.SightingsDao;
import com.bk.dao.SuperDao;
import com.bk.models.Locations;
import com.bk.models.Sightings;
import com.bk.models.Super;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author brandonkervin
 */
@Service
public class ServiceImp implements ServiceLayer {
    
    @Autowired
    SuperDao sDao;

    @Override
    public List<Super> getAllSupers() {
        return sDao.getAllSupers();
    }

    @Override
    public List<Super> getAllSuperHeroes() {
        return sDao.getAllSuperHeroes();
    }

    @Override
    public List<Super> getAllSuperVillains() {
        return sDao.getAllSuperVillains();
    }

    @Override
    public Super getSuperById(int id) {
        return sDao.getSuperById(id);
    }

    @Override
    public List<Super> getSupersByName(String name) {
        return sDao.getSupersByName(name);
    }

    @Override
    public List<Super> getSuperByLocationId(int id) {
        return sDao.getSupersByLocationId(id);
    }

    @Override
    public List<Super> getSuperByOranizationId(int id) {
        return sDao.getSupersByOrganizationId(id);
    }
    
}
