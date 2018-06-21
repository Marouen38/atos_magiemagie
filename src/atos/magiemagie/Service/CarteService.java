/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.Service;

import atos.magiemagie.Carte;
import atos.magiemagie.dao.CarteDAO;
import java.util.List;

/**
 *
 * @author Administrateur
 */

public class CarteService {
    private CarteDAO dao= new CarteDAO(); 
    
    public List<Carte> listerCartesParJoueurId(long joueurId){
        
       return dao.listerCartesParJoueurId(joueurId);
        
    }
    
    public void changerProprietaire(long idNouveauProprietaire, long idCarte){
        //on récupère la carte avec l'id
      
        //on change le proprietaire de cette carte
        
        //on met a jour la carte a l'aide du dao
    }
    
    public void carteIdAleatoirechezunJoueur(long idJoueur){
        //on recupere les cartes du joueur
        //on prend une carte aleatoire parmis ses cartes
        //on renvoie son id
        
        
    }
    
    
    
}
