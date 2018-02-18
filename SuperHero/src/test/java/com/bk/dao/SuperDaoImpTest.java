/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bk.dao;

import com.bk.App.AppConfig;
import com.bk.models.Locations;
import com.bk.models.Organizations;
import com.bk.models.Powers;
import com.bk.models.Sightings;
import com.bk.models.Super;
import java.time.LocalDate;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
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
public class SuperDaoImpTest {
    
    @Autowired
    SuperDao dao;
    @Autowired
    JdbcTemplate jdbc;
    
    @Before
    public void setUp(){
        Setup.doSetup(jdbc);
    }

    @Test
    public void getAllSupers(){
        List<Super> supers = dao.getAllSupers();
        assertTrue(supers.size() == 2);
    }
    
    @Test
    public void getAllSuperHeroes() {
        List<Super> heroes = dao.getAllSuperHeroes();
        if(!heroes.isEmpty()){
            for(Super s : heroes){
                if(!s.getType().equals("Hero")){
                    fail("Should only include heroes");
                }
            }
        }
    }

    @Test
    public void getAllSuperVillains() {
        List<Super> villains = dao.getAllSuperVillains();
        if(!villains.isEmpty()){
            for(Super s : villains){
                if(!s.getType().equals("Villain")){
                    fail("Should only include villains");
                }
            }
        }
    }
    
    @Test
    public void getSuperById(){
        Super s1 = dao.getSuperById(1);
        Super s2 = dao.getSuperById(2);
        assertTrue(s1.getName().equals("Hero 1"));
        assertTrue(s2.getName().equals("Villain 1"));
        assertTrue(s1.getOrganizations().size() == 1);
        assertTrue(s2.getOrganizations().size() == 1);
        assertTrue(s1.getPowers().get(0).getTitle().equals("Power 1"));
        assertTrue(s2.getPowers().get(0).getTitle().equals("Power 2"));
        assertTrue(s1.getSightings().size() == 1);
        assertTrue(s2.getSightings().size() == 1);
    }
    
    @Test
    public void getSupersByName(){
        List<Super> list1 = dao.getSupersByName("Hero 1");
        List<Super> list2 = dao.getSupersByName("Villain 1");
        assertTrue(list1.size() == 1);
        assertTrue(list2.size() == 1);
        assertTrue(list1.get(0).getName().equals("Hero 1"));
        assertTrue(list2.get(0).getName().equals("Villain 1"));
    }
    
    @Test
    public void getSupersByOrganizationId(){
        List<Super> list1 = dao.getSupersByOrganizationId(1);
        List<Super> list2 = dao.getSupersByOrganizationId(2);
        assertTrue(list1.size() == 1);
        assertTrue(list2.size() == 1);
        assertTrue(list1.get(0).getOrganizations().get(0).getOrganizationId() == 1);
        assertTrue(list2.get(0).getOrganizations().get(0).getOrganizationId() == 2);
    }
    
    @Test
    public void addSuper(){
        Super s = new Super();
        s.setName("Test Super Hero");
        s.setDescription("um");
        s.setType("Hero");
        Organizations o = new Organizations();
        o.setOrganizationId(1);
        s.getOrganizations().add(o);
        Powers p = new Powers();
        p.setSuperPowerId(1);
        s.getPowers().add(p);
        Sightings si = new Sightings();
        si.setSightingId(1);
        s.getSightings().add(si);
        s = dao.addSuper(s);
        assertTrue(s.equals(dao.getSuperById(3)));
        assertTrue(s.getOrganizations().get(0).getName().equals("Group 1"));
        assertTrue(s.getPowers().get(0).getTitle().equals("Power 1"));
        assertTrue(s.getSightings().get(0).getDate().equals(LocalDate.parse("2012-01-01")));
    }
    
    @Test
    public void updateSuper(){
        Super s = dao.getSuperById(1);
        String name1 = s.getName();
        s.setName("Spider-Man");
        s = dao.updateSuper(s);
        assertTrue(s.equals(dao.getSuperById(1)));
        assertTrue(!s.getName().equals(name1));
    }
    
    @Test
    public void deleteSuper(){
        Super s = dao.getSuperById(1);
        assertNotNull(s);
        dao.deleteSuper(s.getSuperId());
        assertNull(dao.getSuperById(1));
    }
    
}
