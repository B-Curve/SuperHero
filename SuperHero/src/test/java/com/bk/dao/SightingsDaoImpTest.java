/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bk.dao;

import com.bk.App.AppConfig;
import com.bk.models.Locations;
import com.bk.models.Sightings;
import com.bk.models.Super;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author brandonkervin
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = {TestConfig.class})
public class SightingsDaoImpTest {
    
    @Autowired
    SightingsDao dao;
    @Autowired
    JdbcTemplate jdbc;
    
    @Before
    public void setUp(){
        Setup.doSetup(jdbc);
    }
    
    @Test
    public void getAllSightings(){
        List<Sightings> sightings = dao.getAllSightings();
        assertTrue(sightings.size() == 2);
    }
    
    @Test
    public void getSightingsByLocation(){
        List<Sightings> sightings = dao.getAllSightings();
        Sightings s1 = sightings.get(0);
        Sightings s2 = sightings.get(1);
        Sightings match1 = dao.getSightingById(s1.getSightingId());
        Sightings match2 = dao.getSightingById(s2.getSightingId());
        assertTrue(s1.equals(match1));
        assertTrue(s2.equals(match2));
    }
    
    @Test
    public void getSightingsByDate(){
        List<Sightings> sightings = dao.getAllSightings();
        Sightings date1 = sightings.get(0);
        Sightings date2 = sightings.get(1);
        Sightings match1 = dao.getSightingsByDate(date1.getDate()).get(0);
        Sightings match2 = dao.getSightingsByDate(date2.getDate()).get(0);
        assertTrue(date1.equals(match1));
        assertTrue(date2.equals(match2));
        assertTrue(dao.getSightingsByDate(match1.getDate()).size() == 1);
        assertTrue(dao.getSightingsByDate(match2.getDate()).size() == 1);
    }
    
    @Test
    public void getSightingById(){
        Sightings s1 = dao.getSightingById(1);
        Sightings s2 = dao.getSightingById(2);
        assertTrue(s2.getDescription().equals("Sighting Description 2"));
        assertTrue(s2.getSupers().size() == 1);
        assertTrue(s1.getHeadline().equals("Sighting Headline 1"));
        assertTrue(s1.getSupers().size() == 1);
    }
    
    @Test
    public void addSighting(){
        Sightings s = new Sightings();
        s.setDate(LocalDate.parse("2017-09-09"));
        s.setDescription("Sighting Description 3");
        s.setHeadline("Sighting Headline 3");
        s.getLocation().setLocationId(1);
        Super su = new Super();
        su.setSuperId(1);
        s.getSupers().add(su);
        s = dao.addSighting(s);
        assertTrue(s.getDescription().equals("Sighting Description 3"));
        assertTrue(s.getHeadline().equals("Sighting Headline 3"));
        assertTrue(s.getDate().equals(LocalDate.parse("2017-09-09")));
        assertTrue(s.getLocation().getAddress().equals("1234 Somewhere Ave"));
        assertTrue(s.getSupers().get(0).getName().equals("Hero 1"));
    }
    
    @Test
    public void updateSighting(){
        Sightings s = dao.getSightingById(1);
        String h1 = s.getHeadline();
        s.setHeadline("Newly Updated Headline");
        s = dao.updateSighting(s);
        assertTrue(s.equals(dao.getSightingById(s.getSightingId())));
        assertTrue(!h1.equals(s.getHeadline()));
    }
    
    @Test
    public void deleteSighting(){
        Sightings s = dao.getSightingById(1);
        assertNotNull(s);
        dao.deleteSighting(s.getSightingId());
        assertNull(dao.getSightingById(s.getSightingId()));
    }
    
}
