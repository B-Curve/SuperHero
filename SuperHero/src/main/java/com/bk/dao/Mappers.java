/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bk.dao;

import com.bk.models.Locations;
import com.bk.models.Organizations;
import com.bk.models.Powers;
import com.bk.models.Sightings;
import com.bk.models.Super;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author brandonkervin
 */
public abstract class Mappers {
    
    
    public static final class SuperMapper implements RowMapper<Super> {
        
        @Override
        public Super mapRow(ResultSet rs, int i) throws SQLException {
            Super s = new Super();
            s.setSuperId(rs.getInt("superid"));
            s.setName(rs.getString("Name"));
            s.setDescription(rs.getString("Description"));
            s.setType(rs.getInt("typeid") == 1 ? "Hero" : "Villain");
            return s;
        }
        
    }
    
    public static final class SightingsMapper implements RowMapper<Sightings> {

        @Override
        public Sightings mapRow(ResultSet rs, int i) throws SQLException {
            Sightings s = new Sightings();
            s.setDate(rs.getDate("date").toLocalDate());
            s.setDescription(rs.getString("description"));
            s.setHeadline(rs.getString("headline"));
            s.setSightingId(rs.getInt("sightingid"));
            return s;
        }
        
    }
    
    public static final class PowersMapper implements RowMapper<Powers> {

        @Override
        public Powers mapRow(ResultSet rs, int i) throws SQLException {
            Powers p = new Powers();
            p.setTitle(rs.getString("title"));
            p.setDescription(rs.getString("description"));
            p.setSuperPowerId(rs.getInt("superpowerid"));
            return p;
        }
        
    }
    
    public static final class OrgsMapper implements RowMapper<Organizations> {

        @Override
        public Organizations mapRow(ResultSet rs, int i) throws SQLException {
            Organizations o = new Organizations();
            o.setOrganizationId(rs.getInt("organizationid"));
            o.setName(rs.getString("name"));
            o.setDescription(rs.getString("description"));
            o.setAddress(rs.getString("address"));
            o.setPhone(rs.getString("phone"));
            return o;
        }
        
    }
    
    public static final class LocationsMapper implements RowMapper<Locations> {

        @Override
        public Locations mapRow(ResultSet rs, int i) throws SQLException {
            Locations l = new Locations();
            l.setLocationId(rs.getInt("locationid"));
            l.setDescription(rs.getString("description"));
            l.setAddress(rs.getString("address"));
            l.setLatitude(rs.getDouble("latitude"));
            l.setLongitude(rs.getDouble("longitude"));
            return l;
        }
        
    }
    
}
