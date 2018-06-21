/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.dao;

import atos.magiemagie.Joueur;
import atos.magiemagie.Partie;
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
    
    public long rechercheOrdreMaxJoueurPourPartieId(long partieId){
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        Query query = em.createQuery("SELECT MAX(j.odre) FROM Joueur j JOIN j.partie p WHERE p.id = :id");
        query.setParameter("id",partieId);
        
        return (long) query.getSingleResult();
    }

    public Joueur recupereJoueurALaMAin(long partieId) {
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        Query query = em.createQuery("SELECT j FROM Joueur j JOIN j.partie p WHERE j.etat=:etat  AND p.id=:idParametre");
        query.setParameter("idParametre", partieId);
        query.setParameter("etat", Joueur.EtatJoueur.A_LA_MAIN);
        Joueur j = (Joueur) query.getSingleResult();
        return j;

    }

    public boolean determineSiPlusQueJoueurDansPartie(long partieId) {
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        Query q = em.createQuery("select j"
                + "from Joueur j"
                + "     join j.partie p "
                + "where  p.id=:idPartie"
                + "exept"
                + "select j"
                + "from Joueur j"
                + "       join j.partie p"
                + "where  p.id=:idPartie"
                + "       and j.etat=:eataPerdu");
        
         q.setParameter("idPartie",partieId);
         q.setParameter("etaPerdu", Joueur.EtatJoueur.PERDU);
         
         List res = q.getResultList();
         if (res.size()!=1)
            return false;
         return true;
    }

    public Joueur rechercherJOueurOrdre0ParPartieId(long partieId) {
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        Query query = em.createQuery("SELECT j FROM Joueur j JOIN j.partie p WHERE j.odre = 1 AND p.id=:idParameter");
        query.setParameter("idParameter", partieId);

        Joueur joueur = (Joueur) query.getSingleResult();
        return joueur;
    }
    
    public Joueur rechercherParID(long id) {
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        Query query = em.createQuery("SELECT j FROM Joueur j"
                + " WHERE j.id=:idj");
        query.setParameter("idj", id);
        return (Joueur) query.getSingleResult();
    }

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

    public long rechercheOrdreNouveauJoueurParPartie(long idPartie) {
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        Query query = em.createQuery("SELECT MAX(j.odre) FROM Joueur j JOIN j.partie p WHERE p.id=:idPartie"
        );

        query.setParameter("idPartie", idPartie);
        return (long) query.getSingleResult();

    }

    public void ajouter(Joueur joueur) {

        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();

        em.getTransaction().begin();
        em.persist(joueur);
        em.getTransaction().commit();
    }

    public void modifier(Joueur joueur) {
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();

        em.getTransaction().begin();
        em.merge(joueur);
        em.getTransaction().commit();

    }

    public Joueur RechercherOrdreJoueur(Long position, Long idPartie) {
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();

        String requete = " SELECT j"
                + "FROM Joueur j"
                + "WHERE j.position= :pos"
                + "      j.partie.id = :part";

        Query query = em.createNamedQuery(requete);
        query.setParameter("pos", position);
        query.setParameter("part", idPartie);

        return (Joueur) query.getSingleResult();

    }

    public Joueur rechercheJoueurParOrdreEtParId(long idPartie, long order) {
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        Query query = em.createQuery("select j "
                + "from Joueur j "
                + "join j.partie p "
                + "where p.id=:partieId "
                + "and j.odre=:ordre");
        query.setParameter("partieId", idPartie);
        query.setParameter("ordre", order);    
        
        return (Joueur) query.getSingleResult();
    }
    

}
