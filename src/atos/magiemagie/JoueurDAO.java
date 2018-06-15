/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Administrateur
 */
/**
 * renvoi le joueur dont le pseudo existe en BD, ou envoie null
 *
 * @author Administrateur
 */
public class JoueurDAO {

    public Joueur rechercherParPseudo(String pseudo) {
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        Query query = em.createQuery("SELECT j FROM Joueur j"
                + " WHERE j.pseudo=:lePseudo");
        query.setParameter("lePseudo", pseudo);
        List<Joueur> joueursTrouves = query.getResultList();
        if (joueursTrouves.isEmpty()) {
            return null;
        }

        return joueursTrouves.get(0);
    }

    public long rechercheOrdreNouveauJoueurPourPartie(long idPartie) {
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        Query query = em.createQuery("SELECT MAX(j.odre) FROM Joueur j JOIN j.partie p WHERE p.id=:idPartie"
        );

        query.setParameter("idPartie", idPartie);
        return (long) query.getSingleResult();
       
        
       
    }

    void ajouter(Joueur joueur) {
        
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        
        em.getTransaction().begin();
        em.persist(joueur);
        em.getTransaction().commit();
    }

    void modifier(Joueur joueur) {
        EntityManager em=Persistence.createEntityManagerFactory("PU").createEntityManager();
        
        em.getTransaction().begin();
        em.merge(joueur);
        em.getTransaction().commit();
        
     
    
        
    }

}
