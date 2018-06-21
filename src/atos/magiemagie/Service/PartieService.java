/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.Service;

import atos.magiemagie.Carte;
import atos.magiemagie.Joueur;
import atos.magiemagie.Partie;
import atos.magiemagie.dao.JoueurDAO;
import atos.magiemagie.dao.PartieDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Administrateur
 */
public class PartieService {
    
    private PartieDAO partieDAO = new PartieDAO();
    private JoueurDAO joueurDAO= new JoueurDAO();
    
    public void passeJoueurSuivant(long partieId) {

        //1- recuperer joueur qui a la main = joueurQuiALaMain
        Joueur joueurQuiALaMain = joueurDAO.recupereJoueurALaMAin(partieId);

        //2- Determine si tous autres joueurs ont perdu
        //et passe le joueur a l'etat gagné si c'est le cas 
        //puis quitte la fonction
        //une autre solution  
        //if (servPartie.finPartie(partieId))
        if (partieDAO.determineSiPlusQueUnJoueurDansPartie(partieId)) {
            joueurQuiALaMain.setEtat(Joueur.EtatJoueur.GAGNE);
            joueurDAO.modifier(joueurQuiALaMain);
            return;
        }
        //  else {
        //la partie n'est pas fini
        //3-recuperer ordre Max des joueurs de la partie
        long ordreMax = joueurDAO.rechercheOrdreMaxJoueurPourPartieId(partieId);
        //joueurEvalue= joueurQuiAlaMain
        Joueur joueurEvalue = joueurQuiALaMain;
        //long ordreProchain;
        // boolean prochainNonTrouve = true;
        while (true) {

            //si joueurEvalue est le dernier joueur alors on evalue
            if (joueurEvalue.getOdre()>= ordreMax) {
                //ordreProchain = 1;
                joueurEvalue = joueurDAO.rechercheJoueurParOrdreEtParId(partieId, 1L);
            } else {
                //ordreProchain = joueurQuiALaMain.getOrdre() + 1;
                joueurEvalue = joueurDAO.rechercheJoueurParOrdreEtParId(partieId, joueurEvalue.getOdre()+ 1);
            }
            //Joueur prochain = joueurDAO.recupererJoueurProchain(partieId, ordreProchain);

            //Return si tout les joueurs non étiminés etaient en sommeil profond et q'on la  a juste réveillés
            if (joueurEvalue.getId() == joueurQuiALaMain.getId()) {
                return;
            }
            if (joueurEvalue.getEtat() == Joueur.EtatJoueur.SOMMEIL_PROFOND) {
                joueurEvalue.setEtat(Joueur.EtatJoueur.N_A_PAS_LA_MAIN);
                joueurDAO.modifier(joueurEvalue);
                //prochainNonTrouve = true;
                //si joueurEvalue pas la main alors c'est lui qui prend la main
            } else if (joueurEvalue.getEtat() == Joueur.EtatJoueur.N_A_PAS_LA_MAIN) {
                joueurEvalue.setEtat(Joueur.EtatJoueur.A_LA_MAIN);
                joueurDAO.modifier(joueurEvalue);
                joueurQuiALaMain.setEtat(Joueur.EtatJoueur.N_A_PAS_LA_MAIN);
                joueurDAO.modifier(joueurQuiALaMain);
                // prochainNonTrouve = false;
                return;
            }
        }
        // }
    }
    /*public void passeJoueurSuivant(long idPartie){
        Joueur joueurQuiALaMain = joueurDAO.rechercherJOueurOrdre0ParPartieId(idPartie);
        
   
    
    if(joueurDAO.determineSiPlusQueJoueurDansPartie(idPartie)){
        joueurQuiALaMain.setEtat(Joueur.EtatJoueur.GAGNE);
        
        joueurDAO.modifier(joueurQuiALaMain);
        
        return;
    }
        long ordreMax=joueurDAO.rechercheOrdreMaxJoueurPourPartieId(idPartie);
        Joueur joueurEvalue = joueurQuiALaMain;
        
        while (true){//boucle qui permet de determiner le joueur qui 'attrape' la main
            if (joueurEvalue.getOdre()>=ordreMax){
                //si joueur evalue est le dernier joueur alors on evaluera le premier
                joueurEvalue=joueurDAO.rechercheJoueurParOrdreEtParId(idPartie,1L);
            }
            
            else{joueurEvalue=joueurDAO.rechercheJoueurParOrdreEtParId(idPartie, joueurEvalue.getOdre()+1);
                
            }
            //Si joueur evalue en sommeil Profond son etat passe a pas la main
            
            if(joueurEvalue.getEtat()==Joueur.EtatJoueur.SOMMEIL_PROFOND){
                joueurEvalue.setEtat(Joueur.EtatJoueur.N_A_PAS_LA_MAIN);
                joueurDAO.modifier(joueurEvalue);
            }
           else{
            if(joueurEvalue.getEtat()==Joueur.EtatJoueur.N_A_PAS_LA_MAIN);
            {joueurQuiALaMain.setEtat(Joueur.EtatJoueur.N_A_PAS_LA_MAIN);
            joueurDAO.modifier(joueurQuiALaMain);
            
            joueurEvalue.setEtat(Joueur.EtatJoueur.A_LA_MAIN);
        
            
             
            }
                        
                        }
            }
        }*/
    
    
     
    public Partie creerNouvellePartie(String nom){
        Partie p=new Partie();
        p.setNom(nom);
        partieDAO.ajouter(p);
        return p;
        
        /**
         * LISTE DES PARTIE DONT AUCUN JOUEUR N'EST à L'ETAT A_LA_MAIN
         * 
         */
           
    }
    
  
    public List<Partie>listerPartieNonDemarees(){
        
      
        return partieDAO.listerPartieNonDemarees();

    }
    /**
     *
     * @return
     */
   
    
    
    public List<Joueur> autresJoueurs(long joueurId){
        Joueur joueur =joueurDAO.rechercherParID(joueurId);
        Partie partie = joueur.getPartie();
        List<Joueur> joueurs=partieDAO.listerJoueurs(partie.getId());
        for (Joueur joueur1 : joueurs) {
            if (joueur==joueur1){
                joueurs.remove(joueur1);
            }
        }
        return joueurs;
    }
}
