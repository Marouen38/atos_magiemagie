/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.dao;

import atos.magiemagie.Carte;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Administrateur
 */
public class CarteDAO {
    
    public List<Carte> listerCartesParJoueurId(Long joueurid){
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        Query query = em.createQuery("SELECT c from Carte c join c.joueur j where j.id =:coucou");
        query.setParameter("coucou", joueurid);
        return query.getResultList();
    }
    
    public void ajouterCarte(Carte c ){
         EntityManager em =Persistence.createEntityManagerFactory("PU").createEntityManager();
         
         em.getTransaction().begin();
         em.persist(c);
         em.getTransaction().commit();
        
    }
    public void rechercherCarteParSOnId(long idCarte){
        
         EntityManager em =Persistence.createEntityManagerFactory("PU").createEntityManager();
        Query query=em.createQuery("SELECT c FROM Carte c JOIN c.id WHERE c.id=:idCarte ");
        query.setParameter("idCarte", idCarte);
        
        
        
    }
    
    public boolean joueurAlesCartes(Long idjoueur, Carte.TypeCarte type1, Carte.TypeCarte type2){
        EntityManager em =Persistence.createEntityManagerFactory("PU").createEntityManager();
        Query query=em.createQuery("SELECT COUNT(c) FROM Carte c JOIN c.joueur j WHERE j.id=:idJoueur and c.typeCarte=:type");
        query.setParameter("idJoueur", idjoueur);
        query.setParameter("type",type1);
        if((long)query.getSingleResult()==0){
             return false;   
        }
        query=em.createQuery("SELECT COUNT(c) FROM Carte c JOIN c.joueur j WHERE j.id=:idJoueur and c.typeCarte=:type");
        query.setParameter("idJoueur", idjoueur);
        query.setParameter("type",type2);
        if((long)query.getSingleResult()==0){
             return false;   
        }
        return true;
    }
     
            
    
}
