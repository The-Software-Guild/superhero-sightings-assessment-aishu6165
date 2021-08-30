/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.superherosightings.dao;

import com.superherosightings.model.Location;

import java.util.List;


public interface LocationDAO {
    
    Location getLocationByID(int locationID);
    
    List<Location> getAllLocations();
    
    Location addLocation(Location location);
    
    void updateLocation(Location location);
    
    void deleteLocationByID(int locationID);
    
}
