/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bk.dao;

import com.bk.App.AppConfig;
import com.bk.models.Locations;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author brandonkervin
 */
//@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = {TestConfig.class})
public class LocationDaoImpTest {
    
//    @Autowired
//    LocationDao dao;
//    
//    @Test
//    public void hello(){
//        System.out.println("Hello");
//    }
    
//    @Test
//    public void getAllLocations(){
//        assertNotNull(dao.getAllLocations());
//    }
//    
//    @Test
//    public void getLocationById(){
//        List<Locations> l = dao.getAllLocations();
//        int id = l.get(0).getLocationId();
//        assertNotNull(dao.getLocationById(id));
//    }
//    
//    @Test
//    public void getLocationsByAddress(){
//        List<Locations> l = dao.getAllLocations();
//        String address = l.get(0).getAddress();
//        assertTrue(!dao.getLocationsByAddress(address).isEmpty());
//    }
//    
//    @Test
//    public void getLocationsByLatAndLong(){
//        List<Locations> l = dao.getAllLocations();
//        //Approximates lat and long by +-5
//        double lati = l.get(0).getLatitude() - 3;
//        double longi = l.get(0).getLongitude() + 3;
//        assertTrue(!dao.getLocationsByLatAndLong(lati, longi).isEmpty());
//    }
//    
//    @Test
//    public void getLocationsBySuperId(){
//        List<Locations> l = dao.getLocationsBySuperId(1);
//        assertNotNull(l);
//    }
//    
//    @Test
//    public void addLocation(){
//        Locations l = new Locations();
//        l.setAddress("TEST ADDRESS");
//        l.setDescription("TEST DESCRIPTION");
//        l.setLatitude(12.345654);
//        l.setLongitude(54.234623);
//        dao.addLocation(l);
//        assertNotNull(dao.getLocationsByAddress("TEST ADDRESS"));
//    }
//    
//    @Test
//    public void updateLocation(){
//        List<Locations> l = dao.getLocationsByAddress("TEST ADDRESS");
//        Locations loc = l.get(0);
//        final String d1 = loc.getAddress();
//        loc.setAddress("UPDATED ADDRESS");
//        dao.updateLocation(loc);
//        assertTrue(!d1.equals(dao.getLocationsByAddress("UPDATED ADDRESS").get(0).getAddress()));
//    }
//    
//    @Test
//    public void deleteLocation(){
//        List<Locations> l = dao.getAllLocations();
//        Locations l1 = l.get(1);
//        int id = l1.getLocationId();
//        dao.deleteLocation(id);
//        assertNull(dao.getLocationById(id));
//    }
    
}
