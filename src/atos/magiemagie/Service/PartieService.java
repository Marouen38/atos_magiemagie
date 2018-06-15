/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.Service;

import atos.magiemagie.Partie;
import atos.magiemagie.dao.PartieDAO;
import java.util.List;

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
        
    }
    
    private PartieDAO dao = new PartieDAO();
    public List<Partie>listerPartieNonDemarees(){
        return dao.listerPartieNonDemarees();
    }
}
