/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.superherosightings.dao;

import com.superherosightings.dto.LocationRequest;

import java.util.List;

import com.superherosightings.model.Location;
import com.superherosightings.model.Org;
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
public class OrgDAOImplTest {

    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    OrgDAO orgDAO;

    @Autowired
    LocationDAO locationDAO;

    public OrgDAOImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        List<Org> orgs = orgDAO.getAllOrgs();
        for (Org org : orgs) {
            orgDAO.deleteOrgById(org.getOrgID());
        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAddAndGetOrg() {
        Location location = new Location();
        location.setLocationName("US Bank");
        location.setLocationDescrip("The bank on the corner");
        location.setAddress("1234 Main Address");
        location.setState("OH");
        location.setZipcode("12345");
        location.setCountry("USA");
        locationDAO.addLocation(location);

        Org org = new Org();
        org.setOrgName("Test Org");
        org.setOrgDescrip("An org");
        org.setOrgPhone("123-123-1234");
        org.setOrgEmail("test@email.com");
        org.setOrgStatus("SH");
        org.setLocation(location);
        orgDAO.addOrg(org);

        Org fromDAO = orgDAO.getOrgByID(org.getOrgID());

        assertEquals(org.getOrgPhone(), fromDAO.getOrgPhone());
    }

    @Test
    public void testGetAllOrgs() {
        Location location = new Location();
        location.setLocationName("US Bank");
        location.setLocationDescrip("The bank on the corner");
        location.setAddress("1234 Main Address");
        location.setState("OH");
        location.setZipcode("12345");
        location.setCountry("USA");
        locationDAO.addLocation(location);

        Org org = new Org();
        org.setOrgName("Test Org");
        org.setOrgDescrip("An org");
        org.setOrgPhone("123-123-1234");
        org.setOrgEmail("test@email.com");
        org.setOrgStatus("SH");
        org.setLocation(location);
        orgDAO.addOrg(org);

        Org org2 = new Org();
        org2.setOrgName("Test Org 2");
        org2.setOrgDescrip("Another org");
        org2.setOrgPhone("231-123-1234");
        org2.setOrgEmail("test2@email.com");
        org2.setOrgStatus("SV");
        org2.setLocation(location);
        orgDAO.addOrg(org2);

        List<Org> orgs = orgDAO.getAllOrgs();

        assertEquals(2, orgs.size());
    }

    @Test
    public void testUpdateOrg() {
        Location location = new Location();
        location.setLocationName("US Bank");
        location.setLocationDescrip("The bank on the corner");
        location.setAddress("1234 Main Address");
        location.setState("OH");
        location.setZipcode("12345");
        location.setCountry("USA");
        locationDAO.addLocation(location);

        Org org = new Org();
        org.setOrgName("Test Org");
        org.setOrgDescrip("An org");
        org.setOrgPhone("123-123-1234");
        org.setOrgEmail("test@email.com");
        org.setOrgStatus("SH");
        org.setLocation(location);
        orgDAO.addOrg(org);

        Org fromDAO = orgDAO.getOrgByID(org.getOrgID());
        assertEquals(org.getOrgName(), fromDAO.getOrgName());

        org.setOrgName("A new name");
        orgDAO.updateOrg(org);

        assertNotEquals(org.getOrgName(), fromDAO.getOrgName());

        fromDAO = orgDAO.getOrgByID(org.getOrgID());
        assertEquals(org.getOrgName(), fromDAO.getOrgName());
    }

    @Test
    public void testDeleteOrgById() {
        Location location = new Location();
        location.setLocationName("US Bank");
        location.setLocationDescrip("The bank on the corner");
        location.setAddress("1234 Main Address");
        location.setState("OH");
        location.setZipcode("12345");
        location.setCountry("USA");
        locationDAO.addLocation(location);

        Org org = new Org();
        org.setOrgName("Test Org");
        org.setOrgDescrip("An org");
        org.setOrgPhone("123-123-1234");
        org.setOrgEmail("test@email.com");
        org.setOrgStatus("SH");
        org.setLocation(location);
        orgDAO.addOrg(org);

        Org fromDAO = orgDAO.getOrgByID(org.getOrgID());
        assertEquals(org.getOrgEmail(), fromDAO.getOrgEmail());

        orgDAO.deleteOrgById(org.getOrgID());
        fromDAO = orgDAO.getOrgByID(org.getOrgID());
        assertNull(fromDAO);

        final String GET_ALL_ORGS = "SELECT COUNT(*) FROM OrgMembers WHERE orgID=?";
        int count = jdbc.queryForObject(GET_ALL_ORGS, new Object[]{org.getOrgID()}, Integer.class);
        assertEquals(count, 0);
    }

}
