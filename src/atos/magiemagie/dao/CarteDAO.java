/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.dao;

import atos.magiemagie.Carte;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author Administrateur
 */
public class CarteDAO {
    
    public void ajouterCarte(Carte c ){
         EntityManager em =Persistence.createEntityManagerFactory("PU").createEntityManager();
         
         em.getTransaction().begin();
         em.persist(c);
         em.getTransaction().commit();
        
    }
}
