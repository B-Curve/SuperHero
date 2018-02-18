/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bk.dao;

/**
 *
 * @author brandonkervin
 */
public abstract class Sql {
    
    //MISC COMMANDS
    static final String DELETE_SUPER_ORGANIZATION = "DELETE FROM SuperOrganization WHERE SuperID = ?";
    static final String DELETE_SUPER_SIGHTINGS = "DELETE FROM SuperSightings WHERE SuperID = ?";
    static final String DELETE_SUPER_SIGHTINGS_BY_S_ID = "DELETE From SuperSightings WHERE SightingID = ?";
    static final String DELETE_SUPER_SUPER_POWERS = "DELETE FROM SuperSuperPowers WHERE SuperID = ?";
    static final String DELETE_LOCATION_SIGHTING = "DELETE FROM Sightings WHERE LocationID = ?";
    static final String DELETE_SUPER_SIGHTING_BY_LOCATION = "DELETE FROM SuperSightings join Sightings on"
            + " Sightings.SightingID = SuperSightings.SightingID WHERE SightingID = ?";
    static final String DELETE_SUPER_ORGANIZATION_BY_ORGANIZATION_ID = "DELETE FROM SuperOrganization WHERE OrganizationID = ?";
    static final String DELETE_SUPER_SUPER_POWER_BY_P_ID = "DELETE FROM SuperSuperPowers WHERE SuperPowerID = ?";
    static final String ADD_SUPER_ORGANIZATION = "REPLACE INTO SuperOrganization (SuperID, OrganizationID) values (?,?)";
    static final String ADD_SUPER_SIGHTINGS = "REPLACE INTO SuperSightings (SuperID, SightingID) values (?,?)";
    static final String ADD_SUPER_SUPER_POWERS = "REPLACE INTO SuperSuperPowers (SuperID, SuperPowerID) values (?,?)";
    static final String DELETE_SUPER_SUPER_POWER_BY_BOTH = "DELETE FROM SuperSuperPowers WHERE SuperID = ? AND SuperPowerID = ?";
    static final String DELETE_SUPER_SIGHTING_BY_BOTH = "DELETE FROM SuperSightings WHERE SuperID = ? AND SightingID = ?";
    static final String DELETE_SUPER_ORGANIZATION_BY_BOTH = "DELETE FROM SuperOrganization WHERE SuperID = ? AND OrganizationID = ?";
    
    //SUPER COMMANDS
    static final String ALL_SUPERS = "SELECT * FROM Super";
    static final String ALL_HEROES = "SELECT * FROM Super WHERE TypeID = 1";
    static final String ALL_VILLAINS = "SELECT * FROM Super WHERE TypeID = 2";
    static final String NEW_SUPER = "INSERT INTO Super (Name, Description, TypeID) values (?,?,?)";
    static final String UPDATE_SUPER = "UPDATE Super SET Name = ?, Description = ?, TypeID = ? WHERE SuperID = ?";
    static final String DELETE_SUPER = "DELETE FROM Super WHERE SuperID = ?";
    static final String SUPER_BY_ID = "SELECT * FROM Super WHERE SuperID = ?";
    static final String SUPERS_BY_NAME = "SELECT * FROM Super WHERE Name Like ?";
    static final String SUPERS_BY_SIGHTING = "SELECT Super.* FROM Super join SuperSightings on Super.SuperID ="
            + " SuperSightings.SuperID join Sightings on SuperSightings.SightingID = Sightings.SightingID WHERE Super.SuperID = ?";
    static final String SUPERS_BY_ORGANIZATION_ID = "SELECT Super.* FROM Super join SuperOrganization on Super.SuperID ="
            + " SuperOrganization.SuperID join Organization on SuperOrganization.OrganizationID = Organization.OrganizationID"
            + " WHERE Super.SuperID = ?";
    static final String SUPERS_BY_SIGHTING_ID = "SELECT Super.* FROM Sightings join SuperSightings on Sightings.SightingID ="
            + " SuperSightings.SightingID join Super on Super.SuperID = SuperSightings.SuperID WHERE Sightings.SightingID = ?";
    
    //SIGHTINGS COMMANDS
    static final String ALL_SIGHTINGS_BY_SUPER = "SELECT Sightings.* FROM Sightings join SuperSightings on"
            + " Sightings.SightingID = SuperSightings.SightingID join Super on Super.SuperID = SuperSightings.SuperID"
            + " WHERE Super.SuperID = ?";
    static final String SIGHTINGS_BY_LOCATION = "SELECT Sightings.* FROM Sightings WHERE Sightings.LocationID = ?";
    static final String ALL_SIGHTINGS = "SELECT * FROM Sightings";
    static final String SIGHTINGS_BY_DATE = "SELECT * FROM Sightings WHERE Date = ?";
    static final String SIGHTINGS_BY_ID = "SELECT * FROM Sightings WHERE SightingID = ?";
    static final String ADD_SIGHTING = "INSERT INTO Sightings (LocationID, Description, Headline, Date) values (?,?,?,?)";
    static final String UPDATE_SIGHTING = "UPDATE Sightings SET LocationID = ?, Description = ?, Headline = ?,"
            + " Date = ? WHERE SightingID = ?";
    static final String DELETE_SIGHTING = "DELETE FROM Sightings WHERE SightingID = ?";
    
    //POWERS COMMANDS
    static final String ALL_POWERS_BY_SUPER = "SELECT SuperPowers.* FROM SuperPowers join SuperSuperPowers on"
            + " SuperSuperPowers.SuperPowerID = SuperPowers.SuperPowerID join Super on Super.SuperID ="
            + " SuperSuperPowers.SuperID WHERE Super.SuperID = ?";
    static final String ALL_POWERS = "SELECT * FROM SuperPowers";
    static final String POWERS_BY_NAME = "SELECT * FROM SuperPowers WHERE Title LIKE ?";
    static final String POWERS_BY_ID = "SELECT * FROM SuperPowers WHERE SuperPowerID = ?";
    static final String ADD_POWER = "INSERT INTO SuperPowers (Title, Description) values (?,?)";
    static final String UPDATE_POWER = "UPDATE SuperPowers SET Title = ?, Description = ? WHERE SuperPowerID = ?";
    static final String DELETE_POWER = "DELETE FROM SuperPowers WHERE SuperPowerID = ?";
    
    //ORGANIZATIONS COMMANDS
    static final String ALL_ORGANIZATIONS_BY_SUPER = "SELECT Organization.* FROM Organization join"
            + " SuperOrganization on SuperOrganization.OrganizationID = Organization.OrganizationID join Super on"
            + " Super.SuperID = SuperOrganization.SuperID WHERE Super.SuperID = ?";
    static final String ALL_ORGANIZATIONS = "SELECT * FROM Organization";
    static final String ORGANIZATIONS_BY_NAME = "SELECT * FROM Organization WHERE Name LIKE ?";
    static final String ORGANIZATIONS_BY_SUPER_ID = "SELECT Organization.* FROM Super join SuperOrganization on"
            + " Super.SuperID = SuperOrganization.SuperID join Organization on SuperOrganization.OrganizationID ="
            + " Organization.OrganizationID WHERE Super.SuperID = ?";
    static final String ORGANIZATIONS_BY_ID = "SELECT * FROM Organization WHERE OrganizationID = ?";
    static final String ADD_ORGANIZATION = "INSERT INTO Organization (Name, Description, Address, Phone) values"
            + " (?,?,?,?)";
    static final String UPDATE_ORGANIZATION = "UPDATE Organization SET Name = ?, Description = ?, Address = ?,"
            + " Phone = ? WHERE OrganizationID = ?";
    static final String DELETE_ORGANIZATION = "DELETE FROM Organization WHERE OrganizationID = ?";
    
    //LOCATION COMMANDS
    static final String LOCATION_BY_SIGHTING_ID = "SELECT Locations.* From Sightings join Locations"
            + " on Locations.LocationID = Sightings.LocationID WHERE SightingID = ?";
    static final String ALL_LOCATIONS = "SELECT * FROM Locations";
    static final String LOCATION_BY_ID = "SELECT * FROM Locations WHERE LocationID = ?";
    static final String LOCATIONS_BY_ADDRESS = "SELECT * FROM Locations WHERE Address LIKE ?";
    static final String LOCATIONS_BY_COORDINATES = "SELECT * FROM Locations WHERE Latitude BETWEEN"
            + " (?-5) AND (?+5) AND Longitude BETWEEN (?-5) AND (?+5)";
    static final String ADD_LOCATION = "INSERT INTO Locations (Description, Address, Latitude, Longitude) values (?,?,?,?)";
    static final String UPDATE_LOCATION = "UPDATE Locations SET Description = ?, Address = ?, Latitude = ?, Longitude = ?"
            + " WHERE LocationID = ?";
    static final String DELETE_LOCATION = "DELETE FROM Locations WHERE LocationID = ?";
    static final String LOCATIONS_BY_SUPER_ID = "SELECT Locations.* FROM Super join SuperSightings on Super.SuperID ="
            + " SuperSightings.SuperID join Sightings on SuperSightings.SightingID = Sightings.SightingID join Locations on"
            + " Locations.LocationID = Sightings.LocationID WHERE Super.SuperID = ?";
    
    //TEST QUERIES
    static final String RESET_LOCATIONS = "ALTER TABLE Locations AUTO_INCREMENT = 1";
    static final String PREPARE_LOCATIONS_1 = "INSERT INTO Locations (Description, Address, Latitude, Longitude)"
            + " values ('Test Description','1234 Somewhere Ave',12.543253,43.123352)";
    static final String PREPARE_LOCATIONS_2 = "INSERT INTO Locations (Description, Address, Latitude, Longitude)"
            + " values ('Other Description','5783 Overthere Ave',23.543253,71.123352)";
    static final String REMOVE_ALL_LOCATIONS = "DELETE FROM Locations WHERE LocationID > 0";
    static final String RESET_SIGHTINGS = "ALTER TABLE Sightings AUTO_INCREMENT = 1";
    static final String PREPARE_SIGHTINGS_1 = "INSERT INTO Sightings (LocationID, Description, Headline, Date) values"
            + " (1,'Sighting Description 1','Sighting Headline 1','2012-01-01')";
    static final String PREPARE_SIGHTINGS_2 = "INSERT INTO Sightings (LocationID, Description, Headline, Date) values"
            + " (2,'Sighting Description 2','Sighting Headline 2','2012-02-02')";
    static final String REMOVE_ALL_SIGHTINGS = "DELETE FROM Sightings WHERE SightingID > 0";
    static final String RESET_SUPERS = "ALTER TABLE Super AUTO_INCREMENT = 1";
    static final String PREPARE_SUPER_1 = "INSERT INTO Super (Name, Description, TypeID) values"
            + " ('Hero 1','Good guy',1)";
    static final String PREPARE_SUPER_2 = "INSERT INTO Super (Name, Description, TypeID) values"
            + " ('Villain 1','Bad guy',2)";
    static final String REMOVE_ALL_SUPERS = "DELETE FROM Super WHERE SuperID > 0";
    static final String RESET_SUPER_POWERS = "ALTER TABLE SuperPowers AUTO_INCREMENT = 1";
    static final String PREPARE_POWER_1 = "INSERT INTO SuperPowers (Title, Description) values"
            + " ('Power 1','Does one thing')";
    static final String PREPARE_POWER_2 = "INSERT INTO SuperPowers (Title, Description) values"
            + " ('Power 2','Does another thing')";
    static final String REMOVE_ALL_POWERS = "DELETE FROM SuperPowers WHERE SuperPowerID > 0";
    static final String RESET_ORGANIZATIONS = "ALTER TABLE Organization AUTO_INCREMENT = 1";
    static final String PREPARE_ORG_1 = "INSERT INTO Organization (Name, Description, Address, Phone) values"
            + " ('Group 1','Good group','7691 YaBoi Ave','1-800-GOODGUYS')";
    static final String PREPARE_ORG_2 = "INSERT INTO Organization (Name, Description, Address, Phone) values"
            + " ('Group 2','Bad group','7691 NaBoi Ave','1-800-BADGUYS')";
    static final String REMOVE_ALL_ORGS = "DELETE FROM Organization WHERE OrganizationID > 0";
    static final String REMOVE_ALL_SUPER_ORGS = "DELETE FROM SuperOrganization WHERE SuperID > 0";
    static final String PREPARE_SUPER_ORG_1 = "INSERT INTO SuperOrganization (SuperID, OrganizationID) values"
            + " (1,1)";
    static final String PREPARE_SUPER_ORG_2 = "INSERT INTO SuperOrganization (SuperID, OrganizationID) values"
            + " (2,2)";
    static final String PREPARE_SUPER_SIGHT_1 = "INSERT INTO SuperSightings (SuperID, SightingID) values (1,1)";
    static final String PREPARE_SUPER_SIGHT_2 = "INSERT INTO SuperSightings (SuperID, SightingID) values (2,2)";
    static final String REMOVE_ALL_SUPER_SIGHTINGS = "DELETE FROM SuperSightings WHERE SuperID > 0";
    static final String PREPARE_SUPER_SUPER_POWER_1 = "INSERT INTO SuperSuperPowers (SuperID, SuperPowerID) values (1,1)";
    static final String PREPARE_SUPER_SUPER_POWER_2 = "INSERT INTO SuperSuperPowers (SuperID, SuperPowerID) values (2,2)";
    static final String REMOVE_ALL_SUPER_SUPER_POWERS = "DELETE FROM SuperSuperPowers WHERE SuperID > 0";
    
}
