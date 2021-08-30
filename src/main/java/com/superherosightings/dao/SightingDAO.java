/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.superherosightings.dao;

import com.superherosightings.model.Hero;
import com.superherosightings.model.Location;
import com.superherosightings.model.Sighting;

import java.util.List;


public interface SightingDAO {
    
    Sighting getSightingByID(int sightingID);
    
    List<Sighting> getMostRecentSightings();
    
    List<Sighting> getAllSightings();
    
    Sighting addSighting(Sighting sighting);
    
    void updateSighting(Sighting sighting);
    
    void deleteSightingById(int sightingID);
    
    List<Sighting> getSightingsForHero(Hero hero);
    
    List<Sighting> getSightingsForLocation(Location location);
    
}
