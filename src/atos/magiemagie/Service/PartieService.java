/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.Service;

import atos.magiemagie.Joueur;
import atos.magiemagie.Partie;
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
    
    
    
    public Partie creerNouvellePartie(String nom){
        Partie p=new Partie();
        p.setNom(nom);
        dao.ajouter(p);
        return p;
        /**
         * LISTE DES PARTIE DONT AUCUN JOUEUR N'EST à L'ETAT A_LA_MAIN
         * 
         */
           
    }
    
    private PartieDAO dao = new PartieDAO();
    public List<Partie>listerPartieNonDemarees(){
        
      
        return dao.listerPartieNonDemarees();

    }
    /**
     *
     * @return
     */
   
    
}
