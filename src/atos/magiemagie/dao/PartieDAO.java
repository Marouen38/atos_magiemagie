/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.dao;

import atos.magiemagie.Joueur;
import static atos.magiemagie.Joueur_.partie;
import atos.magiemagie.Partie;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.eclipse.persistence.exceptions.PersistenceUnitLoadingException;

/**
 *
 * @author Administrateur
 */
public class PartieDAO {

//    public void (Partie p){
//        Persistence em = Persistence.createEntityManagerFactory(("PU").createEntityManager(); 
//                em.getTransaction().begin();
//                em.persist(p);
//                em.getTransaction().commit();
//    }
    public List<Partie>listerPartieNonDemarees(){
       EntityManager em= Persistence.createEntityManagerFactory("PU").createEntityManager();
       Query query = em.createQuery("SELECT p "
               + "FROM Partie p "
               + "EXCEPT "
               + "SELECT p "
               + "FROM Partie p "
               + "JOIN p.joueurs j "
               + "WHERE j.etat=:etatGagnee "
               + "EXCEPT "
               + "SELECT p "
               + "FROM Partie p "
               + "JOIN p.joueurs j "
               + "WHERE j.etat=:etatGagnee "
               + "EXCEPT "
               + "SELECT p "
               + "FROM Partie p "
               + "JOIN p.joueurs j "
               + "WHERE j.etat=:etatALaMain" );
       
       
       query.setParameter("etatGagnee",Joueur.EtatJoueur.GAGNE);
       query.setParameter("etatALaMain",Joueur.EtatJoueur.A_LA_MAIN);
        return query.getResultList();
      
    }

    public void ajouter(Partie p) {
          EntityManager em= Persistence.createEntityManagerFactory("PU").createEntityManager();
          em.getTransaction().begin();
          em.persist(p);
          em.getTransaction().commit();
          
         
    }

    public Partie rechercherParId(long idPartie) {
         EntityManager em= Persistence.createEntityManagerFactory("PU").createEntityManager();
        return em.find(Partie.class, idPartie);
    }
          
    public long ordreSuivant(long idPartie){
        EntityManager em= Persistence.createEntityManagerFactory("PU").createEntityManager();
        Query query = em.createQuery("SELECT MAX(j.odre) FROM Joueur j JOIN j.partie p WHERE p.id=:idPartie");
        query.setParameter("idPartie", idPartie);
        if(query.getResultList().size() == 0)
        {
            return 0;
        } else {
            return (long) query.getSingleResult() + 1;
            //return 1;
        }
    }
    
    public List<Joueur> listerJoueurs(long idPartie){
         EntityManager em= Persistence.createEntityManagerFactory("PU").createEntityManager();
        Query query = em.createQuery("SELECT j FROM Joueur j JOIN j.partie p WHERE p.id=:idPartie");
        query.setParameter("idPartie", idPartie);
        return query.getResultList();
    }

    public boolean determineSiPlusQueUnJoueurDansPartie(long partieId) {
         EntityManager em= Persistence.createEntityManagerFactory("PU").createEntityManager();
         Query query = em.createQuery("SELECT COUNT(j) FROM Joueur j JOIN j.partie p WHERE p.id=:idPartie AND j.etat !=:etat1");
         query.setParameter("idPartie", partieId);
         query.setParameter("etat1", Joueur.EtatJoueur.PERDU);
         if((long)query.getSingleResult()==1L){
             return true;
             
         }
         return false;
    }
    
}
