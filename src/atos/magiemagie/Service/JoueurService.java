/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.Service;

import atos.magiemagie.Carte;
import atos.magiemagie.Joueur;
import atos.magiemagie.Partie;
import atos.magiemagie.dao.CarteDAO;
import atos.magiemagie.dao.JoueurDAO;
import atos.magiemagie.dao.PartieDAO;
import java.util.List;
import java.util.Random;



public class JoueurService {

    private JoueurDAO daojoueur = new JoueurDAO();
    private PartieDAO daopartie = new PartieDAO();
    private CarteDAO daocarte = new CarteDAO();

    public Joueur rejoindrePartie(String pseudo, String avatar, long idPartie) {
        //recherche si le joueur existe déjà
        Joueur joueur = daojoueur.rechercherParPseudo(pseudo);

        if (joueur == null) {
            //le joueur n'existe pas 
            joueur = new Joueur();
            joueur.setPseudo(pseudo);

        }
        joueur.setAvatar(avatar);
        joueur.setEtat(Joueur.EtatJoueur.N_A_PAS_LA_MAIN);
        joueur.setOdre( daopartie.ordreSuivant(idPartie));
        //Associe le joueur à la partie et vice versa(JPA relations bidirectionnels
        Partie partie = daopartie.rechercherParId(idPartie);
        joueur.setPartie(partie);
        List<Joueur> listeJoueurs = partie.getJoueurs();
        listeJoueurs.add(joueur);
        if (joueur.getId() == null) {//nouveau
            daojoueur.ajouter(joueur);
        } else { //existe déjà
            daojoueur.modifier(joueur);

        }
        return joueur;
    }

    
    public void demarerPartie(long idPartie) {

        //recherche la partie par son Id
        Partie p = daopartie.rechercherParId(idPartie);

        //declencher un erreur s'il y'a moin de deux joueur
        /*
        if (p.getJoueurs().size() < 2) {
            throw new RuntimeException("la partie ne peut pas etre demmarer");
        }*/
        
        //passer le joueur d'odre 0 a l'etat A_LA_MAIN
        Joueur j = daojoueur.rechercherJOueurOrdre0ParPartieId(idPartie);
        j.setEtat(Joueur.EtatJoueur.A_LA_MAIN);
        daojoueur.modifier(j);
        
        //donner 7 cartes au hazard a chaque joueur
        for(Joueur jboucle:daopartie.listerJoueurs(idPartie)){
            for(int i=0;i<=7;i++){
                 ajouterCarte(jboucle, randomCarte());
            }
           
        }
        
    }
    
  
    private Carte randomCarte() {
        
        Carte.TypeCarte[] tabTypesCartes = Carte.TypeCarte.values();

        Random r = new Random();
        int n = r.nextInt(tabTypesCartes.length);
        Carte c = new Carte();
        c.setTypeCarte(tabTypesCartes[n]);

        return c ;
        
        
    }
    
    private void ajouterCarte(Joueur j,Carte c){
        c.setJoueur(j);

        daocarte.ajouterCarte(c);
        
        

    }
}
