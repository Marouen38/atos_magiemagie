/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie;

import atos.magiemagie.Joueur;
import atos.magiemagie.dao.PartieDAO;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class JoueurService {

    private JoueurDAO dao = new JoueurDAO();
    private PartieDAO daopartie = new PartieDAO();

    public void rejoindrePartie(String pseudo, String avatar, long idPartie) {
        //recherche si le joueur existe déjà
        Joueur joueur = dao.rechercherParPseudo(pseudo);

        if (joueur == null) {
            //le joueur n'existe pas 
            joueur = new Joueur();
            joueur.setPseudo(pseudo);

        }
        joueur.setAvatar(avatar);
        joueur.setEtat(Joueur.EtatJoueur.N_A_PAS_LA_MAIN);
        Long ordre = dao.rechercheOrdreNouveauJoueurPourPartie(idPartie);
        joueur.setEtat(Joueur.EtatJoueur.N_A_PAS_LA_MAIN);
        //Associe le joueur à la partie et vice versa(JPA relations bidirectionnels
        Partie partie = daopartie.rechercherParId(idPartie);
        joueur.setPartie(partie);
        List<Joueur> listeJoueurs = partie.getJoueurs();
        listeJoueurs.add(joueur);
        if (joueur.getId() == null) {//nouveau
            dao.ajouter(joueur);
        } else { //existe déjà
            dao.modifier(joueur);

        }

    }

}
