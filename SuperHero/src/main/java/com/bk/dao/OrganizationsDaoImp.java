/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bk.dao;

import com.bk.models.Organizations;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author brandonkervin
 */
@Component
public class OrganizationsDaoImp implements OrganizationsDao {
    
    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public List<Organizations> getAllOrganizations() {
        List<Organizations> orgs = jdbc.query(Sql.ALL_ORGANIZATIONS, new Mappers.OrgsMapper());
        return orgs.isEmpty() ? null : orgs;
    }

    @Override
    public List<Organizations> getOrganizationsByName(String name) {
        List<Organizations> orgs = jdbc.query(Sql.ORGANIZATIONS_BY_NAME, new Mappers.OrgsMapper(), "%"+name+"%");
        return orgs.isEmpty() ? null : orgs;
    }

    @Override
    public List<Organizations> getOrganizationsBySuperId(int id) {
        List<Organizations> orgs = jdbc.query(Sql.ORGANIZATIONS_BY_SUPER_ID, new Mappers.OrgsMapper(), id);
        return orgs.isEmpty() ? null : orgs;
    }

    @Override
    public Organizations getOrganizationById(int id) {
        try{
            return jdbc.queryForObject(Sql.ORGANIZATIONS_BY_ID, new Mappers.OrgsMapper(), id);
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public Organizations addOrganization(Organizations o) {
        jdbc.update(Sql.ADD_ORGANIZATION,
                o.getName(),
                o.getDescription(),
                o.getAddress(),
                o.getPhone());
        int id = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        o.setOrganizationId(id);
        return o;
    }

    @Override
    public Organizations updateOrganization(Organizations o) {
        jdbc.update(Sql.UPDATE_ORGANIZATION,
                o.getName(),
                o.getDescription(),
                o.getAddress(),
                o.getPhone(),
                o.getOrganizationId());
        return o;
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public void deleteOrganization(int id) {
        //SuperOrganization -> Organization
        jdbc.update(Sql.DELETE_SUPER_ORGANIZATION_BY_ORGANIZATION_ID, id);
        jdbc.update(Sql.DELETE_ORGANIZATION, id);
    }
    
}
