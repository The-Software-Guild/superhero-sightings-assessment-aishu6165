/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.superherosightings.controllers;

import com.superherosightings.dao.LocationDAO;
import com.superherosightings.dto.LocationRequest;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.superherosightings.model.Location;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class LocationController {

    @Autowired
    LocationDAO locationDAO;

    @GetMapping("locations")
    public String displayLocations(Model model) {
        List<Location> locations = locationDAO.getAllLocations();
        model.addAttribute("locations", locations);
        return "locations";
    }

    @GetMapping("addLocation")
    public String addLocationRedirect() {
        return "redirect:/locations";
    }

    @PostMapping("addLocation")
    public String addLocation(@Valid LocationRequest location, BindingResult result, HttpServletRequest request, Model model) {
        if (result.hasErrors()) {
            List<Location> locations = locationDAO.getAllLocations();
            model.addAttribute("locations", locations);
            return "addLocation";
        }
        Location location1= new Location();
        String locationName = request.getParameter("locationName");
        String locationDescrip = request.getParameter("locationDescrip");
        String address = request.getParameter("address");
        String state = request.getParameter("state");
        String zipcode = request.getParameter("zipcode");
        String country = request.getParameter("country");

        location1.setLocationName(locationName);
        location1.setLocationDescrip(locationDescrip);
        location1.setAddress(address);
        location1.setState(state);
        location1.setZipcode(zipcode);
        location1.setCountry(country);

        locationDAO.addLocation(location1);

        return "redirect:/locations";
    }

    @PostMapping("deleteLocation")
    public String deleteLocation(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("locationID"));
        locationDAO.deleteLocationByID(id);

        return "redirect:/locations";
    }

    @GetMapping("editLocation")
    public String editLocation(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("locationID"));
        Location location = locationDAO.getLocationByID(id);

        model.addAttribute("location", location);
        return "editLocation";
    }

    @PostMapping("editLocation")
    public String performEditLocation(@Valid LocationRequest location, BindingResult result) {
        if(result.hasErrors()) {
            return "editLocation";
        }
        Location location1 = new Location();
        BeanUtils.copyProperties(location,location1);
        locationDAO.updateLocation(location1);

        return "redirect:/locations";
    }
}
