/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.superherosightings.dao;

import java.util.Date;
import java.util.List;

import com.superherosightings.model.Hero;
import com.superherosightings.model.Location;
import com.superherosightings.model.Org;
import com.superherosightings.model.Sighting;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class LocationDAOImplTest {

    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    OrgDAO orgDAO;

    @Autowired
    LocationDAO locationDAO;

    @Autowired
    SightingDAO sightingDAO;

    @Autowired
    HeroDAO heroDAO;

    public LocationDAOImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        List<Location> locations = locationDAO.getAllLocations();
        for (Location location : locations) {
            locationDAO.deleteLocationByID(location.getLocationID());
        }
    }



    @Test
    public void testAddAndGetLocation() {
        Location location = new Location();
        location.setLocationName("US Bank");
        location.setLocationDescrip("The bank on the corner");
        location.setAddress("1234 Main Address");
        location.setState("OH");
        location.setZipcode("12345");
        location.setCountry("USA");
        locationDAO.addLocation(location);

        Location fromDAO = locationDAO.getLocationByID(location.getLocationID());

        assertEquals(location.getState(), fromDAO.getState());
    }

    @Test
    public void testGetAllLocations() {
        Location location = new Location();
        location.setLocationName("US Bank");
        location.setLocationDescrip("The bank on the corner");
        location.setAddress("1234 Main Address");
        location.setState("OH");
        location.setZipcode("12345");
        location.setCountry("USA");
        locationDAO.addLocation(location);

        Location location2 = new Location();
        location2.setLocationName("PNC Bank");
        location2.setLocationDescrip("The bank on the other corner");
        location2.setAddress("1234 Market Address");
        location2.setState("OH");
        location2.setZipcode("54321");
        location2.setCountry("USA");
        locationDAO.addLocation(location2);

        List<Location> locations = locationDAO.getAllLocations();

        assertEquals(2, locations.size());
    }

    @Test
    public void testUpdateLocation() {
        Location location = new Location();
        location.setLocationName("US Bank");
        location.setLocationDescrip("The bank on the corner");
        location.setAddress("1234 Main Address");
        location.setState("OH");
        location.setZipcode("12345");
        location.setCountry("USA");
        locationDAO.addLocation(location);

        Location fromDAO = locationDAO.getLocationByID(location.getLocationID());
        assertEquals(location.getLocationName(), fromDAO.getLocationName());

        location.setLocationName("A new name");
        locationDAO.updateLocation(location);

        assertNotEquals(location.getLocationName(), fromDAO.getLocationName());

        fromDAO = locationDAO.getLocationByID(location.getLocationID());
        assertEquals(location.getLocationDescrip(), fromDAO.getLocationDescrip());
    }

    @Test
    public void testDeleteLocationById() {
        Location location = new Location();
        location.setLocationName("US Bank");
        location.setLocationDescrip("The bank on the corner");
        location.setAddress("1234 Main Address");
        location.setState("OH");
        location.setZipcode("12345");
        location.setCountry("USA");
        locationDAO.addLocation(location);

        int testLocID = location.getLocationID();

        Org org = new Org();
        org.setOrgName("Test Org");
        org.setOrgDescrip("An org");
        org.setOrgPhone("123-123-1234");
        org.setOrgEmail("test@email.com");
        org.setOrgStatus("SH");
        org.setLocation(location);
        orgDAO.addOrg(org);

        Hero hero = new Hero();
        hero.setHeroName("Test Spiderman");
        hero.setHeroDescription("A man that is a spider");
        hero.setSuperPower("Can climb");
        hero.setHeroStatus("SH");
        hero = heroDAO.addHero(hero);

        Sighting sighting = new Sighting();
        sighting.setSightingDate(new Date());
        sighting.setHero(hero);
        sighting.setLocation(location);
        sightingDAO.addSighting(sighting);

        Org orgFromDAO = orgDAO.getOrgByID(org.getOrgID());
        int testLocID2 = orgFromDAO.getLocation().getLocationID();
        assertEquals(testLocID, testLocID2);

        Sighting sightingFromDAO = sightingDAO.getSightingByID(sighting.getSightingID());
        Location locFromSighting = sightingFromDAO.getLocation();
        assertEquals(location.getCountry(), locFromSighting.getCountry());

        Location fromDAO = locationDAO.getLocationByID(location.getLocationID());
        assertEquals(location.getState(), fromDAO.getState());

        locationDAO.deleteLocationByID(location.getLocationID());

        sightingFromDAO = sightingDAO.getSightingByID(sighting.getSightingID());
        assertNull(sightingFromDAO);

        fromDAO = locationDAO.getLocationByID(location.getLocationID());
        assertNull(fromDAO);

        orgFromDAO = orgDAO.getOrgByID(org.getOrgID());
        assertNull(orgFromDAO);
    }

}
