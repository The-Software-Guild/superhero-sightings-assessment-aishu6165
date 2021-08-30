/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.superherosightings.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class Location {

    private int locationID;
    
    @NotBlank(message = "Location name must not be empty.")
    @Size(max = 50, message="Location name must be less than 50 characters.")
    private String locationName;
    
    @NotBlank(message = "Location description must not be empty.")
    @Size(max = 200, message="Location description must be less than 200 characters.")
    private String locationDescrip;
    
    @NotBlank(message = "Address address must not be empty.")
    @Size(max = 100, message="Address address must be less than 100 characters.")
    private String address;
    
    @NotBlank(message = "State must not be empty.")
    @Size(max = 2, message="State must be 2 characters.")
    private String state;
    
    @NotBlank(message = "Zip code must not be empty.")
    @Size(max = 5, message="Zip code must be 5 characters.")
    private String zipcode;
    
    @NotBlank(message = "Country must not be empty.")
    @Size(max = 80, message="Country must be less than 80 characters.")
    private String country;

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationDescrip() {
        return locationDescrip;
    }

    public void setLocationDescrip(String locationDescrip) {
        this.locationDescrip = locationDescrip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


}
