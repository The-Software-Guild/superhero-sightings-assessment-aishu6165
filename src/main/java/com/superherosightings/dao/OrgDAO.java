/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.superherosightings.dao;

import com.superherosightings.model.Org;

import java.util.List;


public interface OrgDAO {
    
    Org getOrgByID(int orgID);
    
    List<Org> getAllOrgs();
    
    Org addOrg(Org org);
    
    void updateOrg(Org org);
    
    void deleteOrgById(int orgID);
       
}