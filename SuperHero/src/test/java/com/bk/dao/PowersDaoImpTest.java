/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bk.dao;

import com.bk.App.AppConfig;
import com.bk.models.Powers;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author brandonkervin
 */
//@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = {TestConfig.class})
//@FixMethodOrder(MethodSorters.JVM)
public class PowersDaoImpTest {
    
//    @Autowired
//    PowersDao dao;
//    
//    @Test
//    public void hello(){
//        System.out.println("Hello");
//    }
    
    
//    @Test
//    public void getAllPowers(){
//        assertNotNull(dao.getAllPowers());
//    }
//    
//    @Test
//    public void getPowersByName(){
//        List<Powers> p = dao.getAllPowers();
//        String title = p.get(0).getTitle();
//        assertNotNull(dao.getPowersByName(title));
//    }
//    
//    @Test
//    public void getPowerById(){
//        List<Powers> p = dao.getAllPowers();
//        int id = p.get(0).getSuperPowerId();
//        assertNotNull(dao.getPowerById(id));
//    }
//    
//    @Test
//    public void addPower(){
//        Powers p = new Powers();
//        p.setTitle("TEST");
//        p.setDescription("TEST");
//        dao.addPower(p);
//        assertTrue(dao.getPowersByName("TEST").size() > 0);
//    }
//    
//    @Test
//    public void updatePower(){
//        List<Powers> p = dao.getPowersByName("TEST");
//        Powers pow = p.get(0);
//        pow.setTitle("UPDATE");
//        dao.updatePower(pow);
//        assertNull(dao.getPowersByName("TEST"));
//        assertNotNull(dao.getPowersByName("UPDATE"));
//    }
//    
//    @Test
//    public void deletePower(){
//        int id = dao.getPowersByName("UPDATE").get(0).getSuperPowerId();
//        dao.deletePower(id);
//        assertNull(dao.getPowerById(id));
//    }
    
}
