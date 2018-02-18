/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bk.dao;

import com.bk.App.AppConfig;
import com.bk.models.Organizations;
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
public class OrganizationsDaoImpTest {
//    
//    @Autowired
//    OrganizationsDao dao;
//    
//    @Test
//    public void hello(){
//        System.out.println("Hello");
//    }
    
//    @Test
//    public void getAllOrganizations(){
//        assertNotNull(dao.getAllOrganizations());
//    }
//    
//    @Test
//    public void getOrganizationsByName(){
//        List<Organizations> orgs = dao.getAllOrganizations();
//        String name = orgs.get(0).getName();
//        assertNotNull(dao.getOrganizationsByName(name));
//    }
//    
//    @Test
//    public void getOrganizationsBySuperName(){
//        assertNotNull(dao.getOrganizationsBySuperId(3));
//    }
//    
//    @Test
//    public void getOrganizationById(){
//        List<Organizations> orgs = dao.getAllOrganizations();
//        int id = orgs.get(0).getOrganizationId();
//        assertNotNull(dao.getOrganizationById(id));
//    }
//    
//    @Test
//    public void addOrganization(){
//        int size1 = dao.getAllOrganizations().size();
//        Organizations o = new Organizations();
//        o.setName("TEST");
//        o.setDescription("TEST");
//        o.setAddress("TEST");
//        o.setPhone("TEST");
//        dao.addOrganization(o);
//        int size2 = dao.getAllOrganizations().size();
//        assertTrue(size2 > size1);
//    }
//    
//    @Test
//    public void updateOrganization(){
//        List<Organizations> orgs = dao.getOrganizationsByName("TEST");
//        Organizations o = orgs.get(0);
//        o.setName("UPDATE");
//        dao.updateOrganization(o);
//        assertNull(dao.getOrganizationsByName("TEST"));
//        assertNotNull(dao.getOrganizationsByName("UPDATE"));
//    }
//    
//    @Test
//    public void deleteOrganization(){
//        Organizations o = dao.getOrganizationsByName("UPDATE").get(0);
//        int id = o.getOrganizationId();
//        dao.deleteOrganization(id);
//        assertNull(dao.getOrganizationById(id));
//    }
    
}
