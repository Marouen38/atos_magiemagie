/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magie.test;

import atos.magiemagie.JoueurService;
import atos.magiemagie.Partie;
import atos.magiemagie.Service.PartieService;
import org.junit.Test;

/**
 *
 * @author Administrateur
 */
public class JoueurServiceTest {
    private JoueurService service =new JoueurService();
    private PartieService partieService= new PartieService();
    
    @Test
public void rejoindrePartieOK(){
    Partie partieCreee = partieService.creerNouvellePartie("partie 1");

    service.rejoindrePartie("thomas", "blabl", partieCreee.getId());
    
    
    
}
    
}
