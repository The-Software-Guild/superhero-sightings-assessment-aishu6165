/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.superherosightings.dao;

import com.superherosightings.model.Hero;

import java.util.List;

public interface HeroDAO {
    
    Hero getHeroByID(int heroID);
    
    List<Hero> getAllHeroes();
    
    Hero addHero(Hero hero);
    
    void updateHero(Hero hero);
    
    void deleteHeroById(int heroID);
    
}
